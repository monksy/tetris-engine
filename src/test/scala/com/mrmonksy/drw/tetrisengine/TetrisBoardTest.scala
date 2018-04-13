package com.mrmonksy.drw.tetrisengine

import org.scalatest.{Matchers, WordSpec}

class TetrisBoardTest extends WordSpec with Matchers {


  "TetrisBoard::height" should {
    "Start with 0 height" in {
      val expected = 0
      val instance = new TetrisBoard[Int](5)
      val actual = instance.height()

      actual should be(expected)
    }

    "Have a height with a board that has multiple rows" in {
      val expected = 2
      val board = List(
        Array[Integer](null, null, null, null),
        Array[Integer](null, null, null, null)
      )
      val instance = new TetrisBoard[Integer](board)
      val actual = instance.height()

      actual should be(expected)
    }


  }

  "TetrisBoard::doesBlockFit" should {
    "Block fit on an empty board" in {
      val board = List(
        Array[Integer](null, null, null, null)
      )
      val piece = Array[Integer](1, 1)
      val instance = new TetrisBoard[Integer](board)
      val expected = true
      val actual = instance.doesBlockFit(piece, 1, 0)

      actual should be(expected)
    }

    "Block fit on an empty board with a height" in {
      val board = List(
        Array[Integer](null, null, null, null),
        Array[Integer](null, null, null, null)
      )
      val piece = Array[Integer](1, 1)
      val instance = new TetrisBoard[Integer](board)
      val expected = true
      val actual = instance.doesBlockFit(piece, 1, 1)

      actual should be(expected)
    }

    "Block should fit with a conflict" in {
      val board = List(
        Array[Integer](null, null, 2, null)
      )
      val piece = Array[Integer](1, 1)
      val instance = new TetrisBoard[Integer](board)
      val expected = false
      val actual = instance.doesBlockFit(piece, 1, 0)

      actual should be(expected)
    }

    "Not fit with a conflict on board with a height" in {
      val board = List(
        Array[Integer](null, null, null, null),
        Array[Integer](null, null, 1, null)
      )
      val piece = Array[Integer](1, 1)
      val instance = new TetrisBoard[Integer](board)
      val expected = false
      val actual = instance.doesBlockFit(piece, 1, 1)

      actual should be(expected)
    }
  }


}
