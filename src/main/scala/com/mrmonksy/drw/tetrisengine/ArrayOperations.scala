package com.mrmonksy.drw.tetrisengine

object ArrayOperations {

  /**
    * This checks to see if the two arrays had overlap.
    * @param boardOffset The board offset where the piece is looking to go.
    * @param board The board row representitive.
    * @param compare The block piece to compare against the board row.
    * @param position Position that we're going to examine.
    * @tparam T The type of the board.
    * @return Returns true if the pieces would not be able to fit. False if they're good
    */
  def hasConflict[T](boardOffset:Int, board:Array[T], compare: Array[T])(position:Int): Boolean = {
    !canMerge(compare(position), board(boardOffset+position))
  }

  /**
    * This verifies that there is room at the instnace where you can merge.
    * @param left Left value
    * @param right Right value
    * @tparam T Type that is used.
    * @return Returns true if there is a gap in one of the values.
    */
  def canMerge[T](left: T, right:T) = left == null || right == null
}
