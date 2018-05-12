package com.mrmonksy.tetrisengine

object App {

  def main(args: Array[String]) {
    val isVerbose = args.contains("-v")

    println("Tetris")

    var input: String = null
    val INPUT_HANDLER = """(\w)(\d+)""".r


    do {
      println("Please insert your block selection: ")
      input = scala.io.StdIn.readLine()

      if (input != null) {
        val items = input.split(",")
        val tetrisBoard = new TetrisBoard[Character](10)
        items.foreach(i => {
          val INPUT_HANDLER(piece, placement) = i.trim
          tetrisBoard.addPiece(Shapes.mapping(piece), placement.toInt)
          if (isVerbose) {
            println(tetrisBoard.toString())
          }


        }
        )
        println(tetrisBoard.height())
      }
    } while (input != null)
  }

}
