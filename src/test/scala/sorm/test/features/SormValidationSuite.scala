package sorm.test.features

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sorm.{Entity, Instance}


class SormValidationSuite extends AnyFunSuite with Matchers {
  import SormValidationSuite._

  test("Mutually recursive types are not supported"){
    an [Instance.ValidationException] should be thrownBy {
      new Instance(
        Entity[F]() :: Entity[G]() :: Nil,
        "jdbc:h2:mem:test",
        initMode = InitMode.DropAllCreate
      ).close()
    }
  }
  test("Mutually recursive types are not supported - deep"){
    an [Instance.ValidationException] should be thrownBy {
      new Instance(
        Entity[F]() :: Entity[H]() :: Nil,
        "jdbc:h2:mem:test",
        initMode = InitMode.DropAllCreate
      ).close()
    }
  }
  test("Recursive types are not supported"){
    an [Instance.ValidationException] should be thrownBy {
      new Instance(
        Entity[E]() :: Nil,
        "jdbc:h2:mem:test",
        initMode = InitMode.DropAllCreate
      ).close()
    }
  }
  test("`Any` type is not supported"){
    an [Instance.ValidationException] should be thrownBy {
      new Instance(
        Entity[D]() :: Nil,
        "jdbc:h2:mem:test",
        initMode = InitMode.DropAllCreate
      ).close()
    }
  }
  test("referred entities validation"){
    an [Instance.ValidationException] should be thrownBy {
      new Instance(
        Entity[A]() :: Nil,
        "jdbc:h2:mem:test",
      initMode = InitMode.DropAllCreate
      ).close()
    }
  }
  test("Correct instantiation doesn't throw exceptions"){
    new Instance(
      Entity[A]() :: Entity[B]() :: Entity[C]() :: Nil,
      "jdbc:h2:mem:test",
      initMode = InitMode.DropAllCreate
    ).close()
  }
}
object SormValidationSuite {
  case class A
    ( a : Seq[Option[(B, Int)]], b : B, c : Seq[C] )
  case class B
    ( a : Int, b : C )
  case class C
    ( a : Int )
  case class D
    ( a : Seq[Any] )
  case class E
    ( a : Seq[E] )
  case class F(a: Seq[G])
  case class G(a: F)
  case class H(a: Seq[G])
}