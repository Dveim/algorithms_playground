package playground
package lcs

object LCS {
  def lcs(l: String, r: String): String = {
    // compute useful cache of lengths of LCSs
    val table = Array.ofDim[Int](1 + l.length, 1 + r.length)
    for (i <- 0 to l.length) {
      for (j <- 0 to r.length) {
        table(i)(j) = {
          if (i == 0 || j == 0)
            0
          else if (l(i - 1) == r(j - 1))
            1 + table(i - 1)(j - 1)
          else
            table(i)(j - 1) max table(i - 1)(j)
        }
      }
    }
    // at this point, $table(x)(y) contains length of LCS for ${l.take(x)}, ${r.take(y)}

    // now, let's decipher LCS's value from $table
    val result = new StringBuilder
    var (i, j) = (l.length, r.length)
    while (i > 0 && j > 0) {
      if (l(i - 1) == r(j - 1)) {
        result += l(i - 1)
        i = i - 1
        j = j - 1
      }
      else if (table(i - 1)(j) > table(i)(j - 1))
        i = i - 1
      else
        j = j - 1
    }

    // return reversed
    result.toString.reverse
  }

  def main(args: Array[String]): Unit = {
    println(lcs("AABACDA", "DACBBCAD"))
  }
}
