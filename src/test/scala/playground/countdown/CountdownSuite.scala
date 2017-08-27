package playground.countdown

import org.scalatest.FunSuite
import Countdown._

class CountdownSuite extends FunSuite {
  test("invalid 1") {
    intercept[AssertionError] {
      solutions(List(1, -2, 3), 2)
    }
  }

  test("invalid 2") {
    intercept[AssertionError] {
      solutions(List(1, 2, 3), -5)
    }
  }

  test("no target sum 1") {
    assert(solutions(List(), 42).isEmpty)
  }

  test("no target sum 2") {
    assert(solutions(List(1, 2, 3), 42).isEmpty)
  }

  test("valid 1") {
    val results = solutions(List(2, 3, 5, 6), 42)
    assert(results.exists(_.toString == "(6 * (2 + 5)) = 42"))
    assert(results.length == 3)
  }

  test("valid 2") {
    val results = solutions(List(1, 2, 3, 4), 24)
    assert(results.exists(_.toString == "(2 * (3 * 4)) = 24"))
    assert(results.exists(_.toString == "(4 * (3 + (1 + 2))) = 24"))
    assert(results.length == 8)
  }

  test("valid 3") {
    val results = solutions(List(1, 2, 3, 4, 5), 42)
    assert(results.exists(_.toString == "(2 * (1 + (4 * 5))) = 42"))
    assert(results.exists(_.toString == "(2 + (5 * ((3 - 1) * 4))) = 42"))
    assert(results.length == 39)
  }
}
