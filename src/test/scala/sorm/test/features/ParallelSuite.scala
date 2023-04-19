package sorm.test.features

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers



import sext._, embrace._
import sorm._
import sorm.test.MultiInstanceSuite


class ParallelSuite extends AnyFunSuite with Matchers with MultiInstanceSuite {
  import ParallelSuite._

  def entities = Set(Entity[A]())
  instancesAndIds foreach { case (db, dbId) =>
    val data = 10 to 200
    data.par.foreach(v => db.save(A(v)))
    test(dbId + " - fetching"){
      db.query[A].order("id").fetch().map(_.a).toSet
        .should(equal(data.toSet))
    }
  }

}
object ParallelSuite {
  case class A ( a : Int )
}