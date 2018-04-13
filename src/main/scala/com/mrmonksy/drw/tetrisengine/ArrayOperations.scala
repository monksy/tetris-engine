package com.mrmonksy.drw.tetrisengine

object ArrayOperations {

  /**
    * This checks to see if the two arrays had overlap.
    *
    * @param boardOffset The board offset where the piece is looking to go.
    * @param board       The board row representitive.
    * @param compare     The block piece to compare against the board row.
    * @param position    Position that we're going to examine.
    * @tparam T The type of the board.
    * @return Returns true if the pieces would not be able to fit. False if they're good
    */
  def hasConflict[T](boardOffset: Int, board: Array[T], compare: Array[T])(position: Int): Boolean = {
    !canMerge(compare(position), board(boardOffset + position))
  }

  /**
    * This verifies that there is room at the instnace where you can merge.
    *
    * @param left  Left value
    * @param right Right value
    * @tparam T Type that is used.
    * @return Returns true if there is a gap in one of the values.
    */
  def canMerge[T](left: T, right: T) = left == null || right == null

  /**
    * This merges the piece into the src array. This is a dangerous merge. Also, if there is an item that fails the merge, the array will remain modified up until the failure.
    *
    * THIS METHOD IS D*A*N*G*E*R*O*U*S It will modify the src array.
    *
    * @param src   Source of the array to modify.
    * @param piece The piece to merge in.
    * @param boardOffset
    * @param preMergeCheck
    * @tparam T The type of the value being stored in the board.
    * @return Returns the reference to the src. Note this is a modified array.
    *
    */
  def mergeLine[T](src: Array[T], piece: Array[T], boardOffset: Int, preMergeCheck: (T, T) => Boolean): Array[T] = {
    (boardOffset to (boardOffset + piece.length-1)).foreach(pos => {
      val left = src(pos)
      val piecePos = pos - boardOffset
      val right = piece(piecePos)

      if (preMergeCheck(left, right)) {
        src.update(pos, right)
      } else {
        throw new IllegalArgumentException(s"The piece (${piece.mkString(",")}) could not be written into the array at $pos/$piecePos due to a conflict with the following values ($left, $right)")
      }
    }
    )

    src
  }

}
