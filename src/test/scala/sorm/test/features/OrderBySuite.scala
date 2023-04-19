package sorm.test.features

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sext._, embrace._
import sorm.test.MultiInstanceSuite


class OrderBySuite extends AnyFunSuite with Matchers with MultiInstanceSuite {
  import OrderBySuite._
  
  def entities = Set(Entity[A]())
  instancesAndIds foreach { case (db, dbId) =>
    val data = 1 to 10
    data.foreach(v => db.save(A(v)))
    test(dbId + " - works"){
      db.query[A].order("a").fetch().map(_.a)
        .should(equal(data))
    }
  }
}
object OrderBySuite {
  case class A ( a : Int )
}