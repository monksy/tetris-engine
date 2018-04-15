package com.mrmonksy.drw.tetrisengine

/**
  * @author ${user.name}
  */
object App {

  def main(args: Array[String]) {
    val isVerbose = args.contains("-v")

    println("Tetris")

    var input: String = null
    val INPUT_HANDLER = """(\w)(\d+)""".r


    while (true) {
      println("Please insert your block selection: ")
      input = scala.io.StdIn.readLine()

      val items = input.split(",")
      val tetrisBoard = new TetrisBoard[Character](10)
      items.foreach(i => {
        val INPUT_HANDLER(piece, placement) = i.trim
        tetrisBoard.addPiece(Shapes.mapping(piece), placement.toInt)
        if (isVerbose) {
          tetrisBoard.printBoard()
        }

        println(tetrisBoard.height())
      }
      )

    }
  }

}
