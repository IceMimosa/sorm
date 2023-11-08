package sorm.test.types

import org.scalatest._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import sorm._
import test._


class DoubleSupportSuite extends AnyFunSpec with Matchers with MultiInstanceSuite {
  import DoubleSupportSuite._

  def entities = Set(Entity[A]())
  instancesAndIds foreach { case (db, dbId) =>
    val seq : Seq[Double] = Seq(2, 2.230192321, 3.3209483290840923839230, 0.213)
    seq.foreach(v => db.save(A(v)))
    describe(dbId + " - fetching"){
      db.query[A].order("id").fetch().map(_.a)
        .should(equal(seq))
    }
    describe(dbId + " - filtering"){
      db.query[A].whereLarger("a", 2).fetch()
        .should(have('size(seq.count(_ > 2))))
    }
  }
}
object DoubleSupportSuite {
  case class A ( a : Double )
}
