package sorm.test.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sext._, embrace._
import sorm.test.MultiInstanceSuite


class OptionEntitySupportSuite extends AnyFunSuite with Matchers with MultiInstanceSuite {
  import OptionEntitySupportSuite._

  def entities = Entity[A]() :: Entity[B]() :: Nil
  instancesAndIds foreach { case (db, dbId) =>
    test(dbId + " - save none"){
      db.save(B(None))
    }
  }
}
object OptionEntitySupportSuite {

  case class A ( a : Int )
  case class B ( a : Option[A] )

}

