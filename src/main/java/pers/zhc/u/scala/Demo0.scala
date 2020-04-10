package pers.zhc.u.scala

import java.util.concurrent.atomic.AtomicInteger

import org.junit.jupiter.api.Test


class Demo0 {
  private val a = new AtomicInteger()
  @Test
  def test(): Unit = {
    println(s"a.get() = ${a.get()}")
  }

}
