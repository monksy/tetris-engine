package com.mrmonksy.tetrisengine

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

      val actual = instance.getBoard
      val actualHeight = instance.height()

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

      val actual = instance.getBoard
      val actualHeight = instance.height()



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

      val actual = instance.getBoard
      val actualHeight = instance.height()

      actual.flatten should be(expected.flatten)
      actualHeight should be(expectedHeight)
    }

    "Functional Test: 1: I0,4Q8" in {
      val instance = new TetrisBoard[Character](10)

      instance.addPiece(Shapes.PIPE, 0)
      instance.addPiece(Shapes.PIPE, 4)
      instance.addPiece(Shapes.CUBE, 8)

      val expected = 1
      val expectedBoard = List(
        Array[Character](null, null, null, null, null, null, null, null, 'Q', 'Q')
      )
      val actualBoard = instance.getBoard

      val actual = instance.height()

      actual should be(expected)
      actualBoard.flatten should be(expectedBoard.flatten)
    }

    "Functional Test: 2:  T1,Z3,I4" in {
      val instance = new TetrisBoard[Character](10)

      instance.addPiece(Shapes.UPSIDEDOWN_TRIANGLE, 1)
      instance.addPiece(Shapes.Z_SHAPE, 3)
      instance.addPiece(Shapes.PIPE, 4)

      val expected = 4
      val expectedBoard = List(
        Array[Character](null, null, null, null, 'I', 'I', 'I', 'I', null, null),
        Array[Character](null, null, null, 'Z', 'Z', null, null, null, null, null),
        Array[Character](null, 'T', 'T', 'T', 'Z', 'Z', null, null, null, null),
        Array[Character](null, null, 'T', null, null, null, null, null, null, null)
      )
      val actualBoard = instance.getBoard

      val actual = instance.height()

      actual should be(expected)
      actualBoard.flatten should be(expectedBoard.flatten)
    }

    "Functional Test: 3:  Q0,I2,I6,I0,I6,I6,Q2,Q4" in {
      val instance = new TetrisBoard[Character](10)

      instance.addPiece(Shapes.CUBE, 0)
      instance.addPiece(Shapes.PIPE, 2)
      instance.addPiece(Shapes.PIPE, 6)
      instance.addPiece(Shapes.PIPE, 0)
      instance.addPiece(Shapes.PIPE, 6)
      instance.addPiece(Shapes.PIPE, 6)
      instance.addPiece(Shapes.CUBE, 2)
      instance.addPiece(Shapes.CUBE, 4)

      val expected = 3

      val expectedBoard = List(
        Array[Character](null, null, 'Q', 'Q', null, null, null, null, null, null),
        Array[Character](null, null, 'Q', 'Q', null, null, null, null, null, null),
        Array[Character]('Q', 'Q', null, null, 'Q', 'Q', 'I', 'I', 'I', 'I')
      )
      val actualBoard = instance.getBoard
      val actual = instance.height()

      actual should be(expected)
      actualBoard.flatten should be(expectedBoard.flatten)
    }


  }
}
