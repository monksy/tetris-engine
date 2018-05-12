package com.mrmonksy.tetrisengine

import java.util.logging.Logger

import scala.reflect.ClassTag

class TetrisBoard[T >: Null](width: Int) {
  private var board = List[Array[T]]()
  private val LOG = Logger.getLogger(this.getClass.toString)

  /**
    * This is used for testing and setting an initial board state
    *
    * @param initialBoard The initial board state.
    */
  def this(initialBoard: List[Array[T]]) {
    this(initialBoard.head.length)
    board = initialBoard
  }

  def height(): Int = board.length

  /**
    * This checks to see if the piece can fit within the board at it's current state.
    *
    * @param pieceLine   The line of the piece. (Typically the search goes from the bottom up.)
    * @param boardOffset The offset where the piece will go on the board.
    * @param vHeight     The height of the item on the board
    * @return Returns true if the piece fits, false if it doesn't
    */
  def doesBlockFit(pieceLine: Array[T], boardOffset: Int, vHeight: Int): Boolean = {
    LOG.fine(s"doesBlockFit: boardOffset: $boardOffset, height: $vHeight, piece: [${pieceLine.mkString(",")}]")
    val hasConflictOnBoard = ArrayOperations.hasConflict(boardOffset, board(vHeight), pieceLine)(_)
    pieceLine.indices.forall(i => !hasConflictOnBoard(i))
  }

  override def toString: String = {
    val sb = new StringBuilder("---- Tetris Board State ----")

    sb.append(board.map(c => s"| ${c.map(v => if (v == null) " " else v.toString).mkString(" ")} |").mkString(System.lineSeparator()))
    sb.toString()
  }


  def getBoard: List[Array[T]] = board

  /**
    * This starts at the top and tries to find the best fit for the piece given.
    *
    * @param piece      The piece in question.
    * @param horOffsite The horizontal offset on the line for the piece.
    * @tparam T
    * @return Returns the height at the top of the piece.
    */
  def findBestFit(piece: List[Array[T]], horOffsite: Int): Int = {
    def buildOnStream(baseStream: Stream[(Int, Boolean)], piecePart: Int): Stream[(Int, Boolean)] = {
      val piecePosition = piece.length - 1 - piecePart

      LOG.fine(s"Adding a check for piecePart: $piecePart | piecePosition: $piecePosition")
      //Lets go one higher
      baseStream.map(cur => (cur._1 - 1, cur._2))
        .map(itm => (itm._1, doesBlockFit(piece(piecePosition), horOffsite, itm._1))).takeWhile(_._2)
    }

    val pieceHeight = piece.length - 1

    //Lets start the stream and then build it out with the pieces to go up the piece array
    val fullStream = (pieceHeight until board.length).map(i => (i, false)).toStream
    val startOfStream = fullStream.map(itm => (itm._1, doesBlockFit(piece(pieceHeight), horOffsite, itm._1))).takeWhile(_._2)
    val endOfStream = (1 to pieceHeight).foldLeft(startOfStream)((r, i) => buildOnStream(r, i))

    //The last item on this is the lowest best fit
    endOfStream.last._1
  }

  /**
    * This adds a piece to the board.
    *
    * @param piece   The piece to be added
    * @param hOffset Horizontal offset where the piece is placed.
    * @return Returns the placement of the piece on the board.
    */
  def addPiece(piece: List[Array[T]], hOffset: Int)(implicit m: ClassTag[T]): Int = {
    //Add spacers
    (1 to piece.length).foreach(_ => addLine())

    //Find best placement
    val placement = findBestFit(piece, hOffset)

    //Merge block
    ArrayOperations.mergeBlock(board, piece, (hOffset, placement), ArrayOperations.canMerge)

    //Remove filled lines and empty ones
    board = board.filterNot(_.forall(_ == null))

    //Remove all lines that are full as well
    board = board.filter(_.exists(i => i == null))

    placement
  }

  private def addLine()(implicit m: ClassTag[T]): Unit = {
    board = new Array[T](width) :: board
  }
}
