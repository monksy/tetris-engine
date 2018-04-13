package com.mrmonksy.drw.tetrisengine

object Shapes {
  val CUBE = Array(
    Array('Q', 'Q'),
    Array('Q', 'Q')
  )

  val Z_SHAPE = Array(
    Array('Z', 'Z', null),
    Array(null, 'Z', 'Z')
  )

  val S_SHAPE = Array(
    Array(null, 'S', 'S'),
    Array('S', 'S', null)

  )

  val UPSIDEDOWN_TRIANGLE = Array(
    Array('T', 'T', 'T'),
    Array(null, 'T', null)
  )

  val PIPE = Array(
    Array('I', 'I', 'I', 'I')
  )

  val BIG_L = Array(
    Array('L', null),
    Array('L', null),
    Array('L', 'L')
  )

  val BACKWARD_L = Array(
    Array(null, 'J'),
    Array(null, 'J'),
    Array('J', 'J')
  )

  val mapping = Map("Q" -> CUBE,
    "Z" -> Z_SHAPE,
    "S" -> S_SHAPE,
    "T" -> UPSIDEDOWN_TRIANGLE,
    "I" -> PIPE,
    "L" -> BIG_L,
    "J" -> BACKWARD_L)
}
