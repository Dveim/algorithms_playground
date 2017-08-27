package playground.lcs

import org.scalatest.FunSuite

class LCSSuite extends FunSuite {
  test("discontiguous 1") {
    assert(LCS.lcs("AABACDA", "DACBBCAD").length == 4)
  }

  test("discontiguous 2") {
    assert(LCS.lcs("AABACDA", "DACBBCADA") == "ABCDA")
  }

  test("contiguous") {
    assert(LCS.lcs("QWERTY", "WERU") == "WER")
  }

  test("empty input") {
    assert(LCS.lcs("QWERTY", "") == "")
    assert(LCS.lcs("", "QWERTY") == "")
  }
}
