object Functions extends App {

  //
  // Function Calls
  //

  // basic function call with a single param
  println("foo")


  // function call with a block param
  println { "foo" }


  // function call with a more complex block param
  println {
    val f = "f"
    f + "oo"
  }


  // fully qualified function call
  Predef.println("foo")


  // infix function call
  Predef println "foo"


  // named param function call
  println(x = "foo")


  // optional parens for no-param function
  // Note: println is overloaded taking either no params or a single param
  println()
  println


  // identifier
  `println`("foo")


  //
  // Function Definitions
  //

  // no params for a function def
  def noParams = scala.util.Random.alphanumeric.take(8).mkString

  println(noParams)


  // parens for a no-param function def
  def noParamsWithParens() = scala.util.Random.alphanumeric.take(8).mkString

  println(noParamsWithParens)


  // def with a return type
  def noParamsWithParensAndReturnType(): String = scala.util.Random.alphanumeric.take(8).mkString

  println(noParamsWithParensAndReturnType)


  // single param def
  def aSingleParamDef(s: String) = s.toUpperCase

  println(aSingleParamDef("foo"))


  // function body in a block
  def aSingleParamDefBlock(s: String) = {
    s.toUpperCase
  }

  println(aSingleParamDefBlock("foo"))


  // function body in parens
  def aSingleParamDefParens(s: String) = (
    s.toUpperCase
  )

  println(aSingleParamDefParens("foo"))


  // single param and return type
  def aSingleParamDefWithReturnType(s: String): String = s.toUpperCase

  println(aSingleParamDefWithReturnType("foo"))


  // function val
  val aSingleParamFunctionVal = (s: String) => s.toUpperCase

  println(aSingleParamFunctionVal("foo"))


  // function val with body in a block
  val aSingleParamFunctionValBlock = {
    (s: String) => s.toUpperCase
  }

  println(aSingleParamFunctionValBlock("foo"))


  // two param def
  def twoParamDef(s: String, i: Int): String = s.take(i)

  println(twoParamDef("hello", 2))


  // multiple parameter sets
  def twoParamSetsDef(s: String)(i: Int): String = s.take(i)

  println(twoParamSetsDef("hello")(2))


  // partially applied function
  val partiallyAppliedFunction = twoParamSetsDef("hello")(_)

  println(partiallyAppliedFunction(3))


  // partially applied function where the second parameter is applied
  val anotherPartiallyAppliedFunction: (String => String) = twoParamSetsDef(_)(1)

  println(anotherPartiallyAppliedFunction("foo"))


  // polymorphic type parameter for a def
  def typeParamDef[T](o: Option[T]): Boolean = o.isDefined

  println(typeParamDef(Some("foo")))
  println(typeParamDef[String](Some("foo")))


  // create an instance of a single parameter function
  val aFunction1 = new Function1[String, String] {
    override def apply(s: String): String = s.toUpperCase
  }

  println(aFunction1("foo"))


  // create an instance of a single parameter function but use parens after the type
  val aFunction1WithParens = new Function1[String, String]() {
    override def apply(s: String): String = s.toUpperCase
  }

  println(aFunction1WithParens("foo"))


  // shorthand for creating a new single parameter function
  val anotherFunction1 = new (String => String) {
    override def apply(s: String): String = s.toUpperCase
  }

  println(anotherFunction1("foo"))


  // single input parameters can optionally be wrapped in parens
  val yetAnotherFunction1 = new ((String) => String) {
    override def apply(s: String): String = s.toUpperCase
  }

  println(yetAnotherFunction1("foo"))


  // shorthand for a single parameter function
  val andAnotherFunction1 = (s: String) => s.toUpperCase

  println(andAnotherFunction1("foo"))


  // shorthand for a partial function val
  val aPartialFunction: PartialFunction[String, String] = {
    case s: String => s.toUpperCase
  }

  println(aPartialFunction("foo"))


  // shorthand for a partial function def
  def aPartialFunctionDef: PartialFunction[String, String] = {
    case s: String => s.toUpperCase
  }

  println(aPartialFunctionDef("foo"))


  // create a new instance of a partial function
  val anotherPartialFunction = new PartialFunction[String, String] {
    override def isDefinedAt(s: String): Boolean = true
    override def apply(s: String): String = s.toUpperCase
  }

  println(anotherPartialFunction("foo"))


  // an operator
  def &%->(): String = "foo"

  println(&%->())


  // right-associative by putting a colon on the right side of the method name
  class Foo {
    def #:(s: String): String = s.reverse
  }

  val foo = new Foo

  // normal operator call
  println(foo.#:("asdf"))

  // reverse infix call
  println("asdf" #: foo)


  // no equals necessary for functions that return Unit
  def returnsUnit() {
    println("bad form")
  }

  returnsUnit()


  // call by name params are evaluated each time the are accessed
  def callByName(byName: => String, byValue: String): Unit = {
    println(byName, byValue)
    println(byName, byValue)
  }

  callByName(scala.util.Random.alphanumeric.take(8).mkString, scala.util.Random.alphanumeric.take(8).mkString)


  // default values
  def defaultValues(s: String = "asdf") = s.toUpperCase

  //
  println(defaultValues("foo"))

  // the parens are required
  println(defaultValues())


  //
  // Higher-order Functions
  //

  // a function that takes a string and returns it in upper case
  def toUpperCase(s: String): String = s.toUpperCase

  // a higher-order function that takes a function, and calls it with the string "foo"
  def aHigherOrderFunction(f: String => String): String = f("foo")

  println(aHigherOrderFunction(toUpperCase))


  // single input type parameters can optionally be wrapped with parens
  def anotherHigherOrderFunction(f: (String) => String): String = f("foo")

  println(anotherHigherOrderFunction(toUpperCase))


  // multiple input type parameters must be wrapped with parens
  def multipleInputParamHigherOrderFunction(f: (String, Int) => String): String = f("foo", 1)


  // no input type parameters
  // note: the call to f must use parens in this context
  def noInputParamHigherOrderFunction(f: () => String): String = f()


  // a higher-order function that returns a function
  def returnsAHigherOrderFunction: (String => String) = toUpperCase

  println(returnsAHigherOrderFunction("foo"))


  // anonymous function where s is the string input parameter
  println {
    anotherHigherOrderFunction { s =>
      s.toUpperCase
    }
  }


  // anonymous function with input type specified
  println {
    anotherHigherOrderFunction { s: String =>
      s.toUpperCase
    }
  }


  // anonymous function with partial function from a case statement
  println {
    anotherHigherOrderFunction { case s: String =>
      s.toUpperCase
    }
  }


  // anonymous function with multiple params
  println {
    multipleInputParamHigherOrderFunction { (s, i) =>
      s.take(i)
    }
  }


  // anonymous function with multiple params via a case statement
  println {
    multipleInputParamHigherOrderFunction { case (s: String, i: Int) =>
      s.take(i)
    }
  }


  // shorthand anonymous function
  println(anotherHigherOrderFunction(_.toUpperCase))


  // polymorphic type param with a higher-order function
  def aTypeParamedHigherOrderFunction[T](f: String => T) = f("foo")

  println(aTypeParamedHigherOrderFunction(_.length))
  println(aTypeParamedHigherOrderFunction[Int](_.length))

}
