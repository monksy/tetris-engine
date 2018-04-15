package com.mrmonksy.drw.tetrisengine

import org.scalatest.{Matchers, WordSpec}

class TetrisBoardTest extends WordSpec with Matchers {


  "TetrisBoard::height" should {
    "Start with 0 height" in {
      val expected = 0
      val instance = new TetrisBoard[Integer](5)
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

  "TetrisBoard::findBestFit" should {
    "fit for a flat one height item in blank board" in {
      val board = List(
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null)
      )
      val piece = List(
        Array[Integer](1, 1, 1, 1)
      )
      val horOffset = 1
      val expected = 4
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)

    }
    f"fit for a flat two height item in blank board" in {
      val board = List(
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null)
      )
      val piece = List(
        Array[Integer](1, 1, 1, 1),
        Array[Integer](null, 1, 1, null)
      )
      val horOffset = 1
      val expected = 3
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)
    }
    "fit for a flat one height item in a board with blocking" in {
      val board = List(
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, 4, null),
        Array[Integer](null, null, 3, null, null),
        Array[Integer](null, 2, null, null, null)
      )
      val piece = List(
        Array[Integer](1, 1, 1, 1)
      )
      val horOffset = 1
      val expected = 1
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)

    }
    "fit for a flat two height item in a board with blocking" in {
      val board = List(
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, 5),
        Array[Integer](null, null, null, null, 4),
        Array[Integer](null, null, 3, null, null),
        Array[Integer](null, 2, null, null, null)
      )
      val piece = List(
        Array[Integer](1, 1, 1, 1),
        Array[Integer](null, 1, 1, null)
      )
      val horOffset = 1
      val expected = 0
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)
    }

    "fit for a flat two height item in a board with potential stops" in {
      val board = List(
        Array[Integer](null, null, null, null, null),
        Array[Integer](null, null, null, null, 4),
        Array[Integer](null, null, 3, null, null),
        Array[Integer](null, null, null, null, 4)
      )
      val piece = List(
        Array[Integer](1, 1, 1, 1),
        Array[Integer](null, 1, 1, null)
      )
      val horOffset = 1
      val expected = 0
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)
    }

    "fit for a three piece height item in a board with blocking" in {
      val board = List(
        Array[Integer](7, null, null, null, null),
        Array[Integer](7, null, null, null, null),
        Array[Integer](7, null, null, null, null),
        Array[Integer](7, null, null, null, null),
        Array[Integer](7, 6, null, null, 8),
        Array[Integer](7, 6, 3, null, null),
        Array[Integer](7, 2, null, null, null)
      )
      val piece = List(
        Array[Integer](null, null, 1, null),
        Array[Integer](1, 1, 1, 1),
        Array[Integer](null, 1, 1, null)
      )
      val horOffset = 1
      val expected = 2
      val instance = new TetrisBoard[Integer](board)

      val actual = instance.findBestFit(piece, horOffset)

      actual should be(expected)
    }

  }

  val THREE_FLAT_PIECE = List(
    Array[Integer](1, 1, 1)
  )

  val SINGLE_DOT_PIECE = List(Array[Integer](2))

  "TetrisBoard::addPiece" should {
    "Add a simple flat THREE_FLAT_PIECE to the board" in {
      val instance = new TetrisBoard[Integer](5)

      instance.addPiece(THREE_FLAT_PIECE, 0)
      val expected = List(
        Array(1, 1, 1, null, null)
      )
      val expectedHeight = 1

      val actual = instance.getBoard()
      val actualHeight = instance.height()
      instance.printBoard()


      actual.flatten should be(expected.flatten)
      actualHeight should be(expectedHeight)
    }

    "Add a simple flat THREE_FLAT_PIECE with overloay" in {
      val instance = new TetrisBoard[Integer](5)

      instance.addPiece(THREE_FLAT_PIECE, 0)
      instance.addPiece(THREE_FLAT_PIECE, 1)
      val expected = List(
        Array(null, 1, 1, 1, null),
        Array(1, 1, 1, null, null)
      )
      val expectedHeight = 2

      val actual = instance.getBoard()
      val actualHeight = instance.height()

      instance.printBoard()


      actual.flatten should be(expected.flatten)
      actualHeight should be(expectedHeight)
    }


    "Add some overlay, 3 flats" in {
      val instance = new TetrisBoard[Integer](5)

      instance.addPiece(THREE_FLAT_PIECE, 0)
      instance.addPiece(THREE_FLAT_PIECE, 1)
      instance.addPiece(SINGLE_DOT_PIECE, 4)
      instance.addPiece(THREE_FLAT_PIECE, 2)

      val expected = List(
        Array(null, null, 1, 1, 1),
        Array(null, 1, 1, 1, null),
        Array(1, 1, 1, null, 2)
      )
      val expectedHeight = 3

      val actual = instance.getBoard()
      val actualHeight = instance.height()

      instance.printBoard()

      actual.flatten should be(expected.flatten)
      actualHeight should be(expectedHeight)
    }


  }
}
