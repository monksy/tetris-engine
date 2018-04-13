package com.mrmonksy.drw.tetrisengine

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class TetrisBoard[T](width: Int) {
  private var board = mutable.MutableList[Array[T]]()

  /**
    * This is used for testing and setting an inital board state
    *
    * @param initialBoard The inital board state.
    */
  def this(initialBoard: List[Array[T]]) {
    this(initialBoard.head.length)
    board = mutable.MutableList(initialBoard: _*)
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
    val hasConflictOnBoard = ArrayOperations.hasConflict(boardOffset, board(vHeight), pieceLine)(_)
    (0 to pieceLine.length-1).forall(i => !hasConflictOnBoard(i))
  }

  def printBoard() = {

    println("---- Tetris Board State ----")
    println(board.map(c => s"| ${c.mkString(" ")} |").mkString(System.lineSeparator()))

  }
}
