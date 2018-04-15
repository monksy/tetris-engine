package com.mrmonksy.drw.tetrisengine

object Shapes {
  val CUBE = List(
    Array[Character]('Q', 'Q'),
    Array[Character]('Q', 'Q')
  )

  val Z_SHAPE = List(
    Array[Character]('Z', 'Z', null),
    Array[Character](null, 'Z', 'Z')
  )

  val S_SHAPE = List(
    Array[Character](null, 'S', 'S'),
    Array[Character]('S', 'S', null)

  )

  val UPSIDEDOWN_TRIANGLE = List(
    Array[Character]('T', 'T', 'T'),
    Array[Character](null, 'T', null)
  )

  val PIPE = List(
    Array[Character]('I', 'I', 'I', 'I')
  )

  val BIG_L = List(
    Array[Character]('L', null),
    Array[Character]('L', null),
    Array[Character]('L', 'L')
  )

  val BACKWARD_L = List(
    Array[Character](null, 'J'),
    Array[Character](null, 'J'),
    Array[Character]('J', 'J')
  )

  val mapping = Map("Q" -> CUBE,
    "Z" -> Z_SHAPE,
    "S" -> S_SHAPE,
    "T" -> UPSIDEDOWN_TRIANGLE,
    "I" -> PIPE,
    "L" -> BIG_L,
    "J" -> BACKWARD_L)
}
