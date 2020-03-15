package demo

import org.junit.jupiter.api.Test

class Demo6 {
  @Test
  def test(): Unit = {
    val str = 1 + "2"
    println(str)
  }
}
