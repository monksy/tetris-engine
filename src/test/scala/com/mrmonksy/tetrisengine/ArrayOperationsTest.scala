package com.mrmonksy.tetrisengine

import org.scalatest.{Matchers, WordSpec}

class ArrayOperationsTest extends WordSpec with Matchers {

  "ArrayOperationsTest::canMerge" should {
    "fail when both items aren't open" in {
      val expected = false
      val actual = ArrayOperations.canMerge(true, true)

      actual should be(expected)
    }
    "pass when both values are open" in {
      val expected = true
      val actual = ArrayOperations.canMerge(null, null)

      actual should be(expected)
    }
    "pass when the left side is open, the right isn't" in {
      val expected = true
      val actual = ArrayOperations.canMerge('A, null)

      actual should be(expected)
    }
    "pass when the left side is used, and the right is open" in {
      val expected = true
      val actual = ArrayOperations.canMerge(null, 'B)

      actual should be(expected)
    }
  }

  "ArrayOperations::hasConflicts" should {
    "Have a conflict on the first item" in {
      val expected = true
      val board = Array[Integer](1, 1, null)
      val boardOffset = 1
      val piece = Array[Integer](1, 1)

      val actual = ArrayOperations.hasConflict(boardOffset, board, piece)(0)

      actual should be(expected)
    }
    "Not have a conflict where both items are blank" in {
      val expected = false
      val board = Array[Integer](1, 1, null)
      val boardOffset = 1
      val piece = Array[Integer](1, 1)

      val actual = ArrayOperations.hasConflict(boardOffset, board, piece)(1)

      actual should be(expected)
    }
  }

  "ArrayOperations::mergeLine" should {
    "Merge the items in without any validation issues" in {
      val board = Array[Integer](1, null, null, 1)
      val piece = Array[Integer](2, 2)
      val expected = Array[Integer](1, 2, 2, 1)
      val actual = ArrayOperations.mergeLine(board, piece, 1, (_: Integer, _: Integer) => true)

      actual should be(expected)
    }

    "Fail to merge and catch the exception" in {
      val board = Array[Integer](1, null, null, 1)
      val piece = Array[Integer](2, 2)

      the[IllegalArgumentException] thrownBy ArrayOperations.mergeLine(board, piece, 1, (_: Integer, _: Integer) => false)
    }

    "Merge items with the piece having a null" in {
      val board = Array[Integer](1, null, null, 1)
      val piece = Array[Integer](null, 2, 2)
      val expected = Array[Integer](1, 2, 2, 1)
      val actual = ArrayOperations.mergeLine(board, piece, 0, (_: Integer, _: Integer) => true)

      actual should be(expected)
    }
  }

  "TetrisBoard::mergeBlock" should {
    "mergeABlock without any conflict checking" in {
      val board = List(
        Array[Integer](null, null, 2, null),
        Array[Integer](1, null, null, 3)
      )
      val piece = List(
        Array[Integer](6),
        Array[Integer](6)
      )

      val expected = List(
        Array[Integer](null, 6, 2, null),
        Array[Integer](1, 6, null, 3)
      )

      val actual = ArrayOperations.mergeBlock(board, piece, (1, 0), (_: Integer, _: Integer) => true)

      //This is flattened because deep collections aren't compared correctly in scalatest
      actual.flatten should be(expected.flatten)
    }
    "mergeABlock with conflict checking" in {
      val board = List(
        Array[Integer](null, null, 2, null),
        Array[Integer](1, null, null, 3)
      )
      val piece = List(
        Array[Integer](6),
        Array[Integer](6)
      )

      val expected = List(
        Array[Integer](null, 6, 2, null),
        Array[Integer](1, 6, null, 3)
      )

      val actual = ArrayOperations.mergeBlock(board, piece, (1, 0), ArrayOperations.canMerge)

      //This is flattened because deep collections aren't compared correctly in scalatest
      actual.flatten should be(expected.flatten)
    }
    "Fail to merge a block due to conflict checking" in {
      val board = List(
        Array[Integer](null, null, 2, null),
        Array[Integer](1, null, null, 3)
      )
      val piece = List(
        Array[Integer](6),
        Array[Integer](6)
      )

      an[IllegalArgumentException] should be thrownBy
        ArrayOperations.mergeBlock(board, piece, (1, 0), (_: Integer, _: Integer) => false)

    }
    "merge a block with a starting at a position of 1" in {
      val board = List(
        Array[Integer](null, -1, 2, null),
        Array[Integer](null, null, 2, null),
        Array[Integer](1, null, null, 3)
      )
      val piece = List(
        Array[Integer](6),
        Array[Integer](6, 6)
      )

      val expected = List(
        Array[Integer](null, -1, 2, null),
        Array[Integer](null, 6, 2, null),
        Array[Integer](1, 6, 6, 3)
      )

      val actual = ArrayOperations.mergeBlock(board, piece, (1, 1), ArrayOperations.canMerge)

      //This is flattened because deep collections aren't compared correctly in scalatest
      actual.flatten should be(expected.flatten)
    }
  }

}
