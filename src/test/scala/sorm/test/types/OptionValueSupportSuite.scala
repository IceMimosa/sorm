package sorm.test.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sext._, embrace._
import sorm.test.MultiInstanceSuite


class OptionValueSupportSuite extends AnyFunSuite with Matchers with MultiInstanceSuite {

  import OptionValueSupportSuite._

  def entities = Set() + Entity[A]()
  instancesAndIds foreach { case (db, dbId) =>
    val a1 = db.save(A(None))
    val a2 = db.save(A(Some(3)))
    val a3 = db.save(A(Some(7)))

    test(dbId + " - saved entities are correct"){
      db.fetchById[A](a1.id).a should be === None
      db.fetchById[A](a2.id).a should be === Some(3)
      db.fetchById[A](a3.id).a should be === Some(7)
    }
    test(dbId + " - equals filter"){
      db.query[A]
        .whereEqual("a", None).fetchOne().get should be === a1
      db.query[A]
        .whereEqual("a", Some(3)).fetchOne().get should be === a2
    }
    test(dbId + " - not equals filter"){
      db.query[A]
        .whereNotEqual("a", None)
        .fetch().toSet
        .should( not contain (a1) and contain (a3) and contain (a2) )
      db.query[A]
        .whereNotEqual("a", Some(3))
        .fetch().toSet
        .should( not contain (a2) and contain (a1) and contain (a3) )
    }
    
  }

}
object OptionValueSupportSuite {

  case class A
    ( a : Option[Int] )

}