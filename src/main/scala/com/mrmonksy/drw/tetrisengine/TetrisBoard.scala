package com.mrmonksy.drw.tetrisengine

import java.io.OutputStream

import scala.collection.mutable

class TetrisBoard[T](width:Int) {
  val board = mutable.MutableList[Array[T]]()


  def printBoard() = {

    println("---- Tetris Board State ----")
    println (board.map(c=> s"| ${c.mkString(" ")} |").mkString(System.lineSeparator()))

  }
}
