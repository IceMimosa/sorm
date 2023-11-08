package sorm.test.features

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import sorm._
import sorm.test.MultiInstanceSuite


class InFilterTest extends AnyFunSpec with Matchers with MultiInstanceSuite {
  import InFilterTest._

  def entities = Set() + Entity[A]()

  instancesAndIds foreach { case (db, dbId) =>

    val a1 = db.save(A(1))
    val a2 = db.save(A(2))
    val a3 = db.save(A(3))

    describe(dbId + " - empty value"){
      db.query[A].whereIn("a", Seq()).fetchOne().should(equal(None))
    }
    describe(dbId + " - valid value"){
      db.query[A].whereIn("a", Seq(2)).fetchOne().should(equal(Some(a2)))
    }

  }

}
object InFilterTest {

  case class A ( a : Int )

}