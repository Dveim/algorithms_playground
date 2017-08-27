package playground
package countdown

object Countdown {

  sealed trait Op {
    def calc(x: Int, y: Int): Int
    def valid(x: Int, y: Int): Boolean // guards (e.g. no <0 numbers) and permutation optimizations (e.g. a+b == b+a) go here
  }

  case object Add extends Op {
    override def toString = "+"
    override def calc(x: Int, y: Int): Int = x + y
    override def valid(x: Int, y: Int): Boolean = x <= y
  }

  case object Subt extends Op {
    override def toString = "-"
    override def calc(x: Int, y: Int): Int = x - y
    override def valid(x: Int, y: Int): Boolean = x > y
  }

  case object Mult extends Op {
    override def toString = "*"
    override def calc(x: Int, y: Int): Int = x * y
    override def valid(x: Int, y: Int): Boolean = (x != 1) && (y != 1) && (x <= y)
  }

  sealed trait Expr

  case class Value(n: Int) extends Expr {
    override def toString: String = n.toString
  }

  case class App(op: Op, l: Expr, r: Expr) extends Expr {
    override def toString = s"($l $op $r)"
  }

  case class Result(expr: Expr, i: Int) {
    override def toString = s"$expr = $i"
  }

  //////////////////////////////////////////

  private def values(expr: Expr): List[Int] = expr match {
    case v: Value => List(v.n)
    case a: App => values(a.l) ++ values(a.r)
  }

  private def eval(expr: Expr): List[Int] = expr match {
    case v: Value => if (v.n > 0) List(v.n) else Nil
    case a: App => for {
        x <- eval(a.l)
        y <- eval(a.r)
        if a.op.valid(x, y)
      } yield a.op.calc(x, y)
  }

  private def combine(lres: Result, rres: Result): List[Result] = {
    val (l, x) = (lres.expr, lres.i)
    val (r, y) = (rres.expr, rres.i)

    for {
      o <- List(Add, Subt, Mult)
      if o.valid(x, y)
    } yield Result(App(o, l, r), o.calc(x, y))
  }

  private def results(values: List[Int]): List[Result] = values match {
    case Nil => Nil
    case List(h) => if (h > 0) List(Result(Value(h), h)) else List()
    case h :: t => for {
        (ls, rs) <- nesplit(values)
        lx <- results(ls)
        ry <- results(rs)
        res <- combine(lx, ry).view
      } yield res
  }

  // brute force search exploration
  private def nesplit[T](l: List[T]): List[(List[T], List[T])] = {
    for (i <- 1 until l.length) yield l.splitAt(i)
  }.toList

  // lists of all subsequences
  private def subs[T](l: List[T]) : List[List[T]] = l match {
    case Nil => List(List())
    case h :: t =>
      val ys = subs(t)
      ys ++ ys.view.map(h :: _)
  }

  // list of all permutations of all subsequences
  private def subbags[T](l: List[T]): List[List[T]] =
    for {
      ys <- subs(l)
      zs <- ys.permutations.toList
    } yield zs

  def solutions(ns: List[Int], target: Int): List[Result] = {
    assert(ns.forall(_ > 0))
    assert(target > 0)

    for {
      nss <- subbags(ns)
      r <- results(nss)
      if r.i == target
    } yield r
  }

  def main(args: Array[String]): Unit = {
    println(solutions(List(2, 3, 5, 6), 42).mkString("\n"))
  }
}