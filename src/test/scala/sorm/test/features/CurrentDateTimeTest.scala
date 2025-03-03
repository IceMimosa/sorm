package sorm.test.features

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sext._, embrace._
import sorm.test.MultiInstanceSuite


class CurrentDateTimeTest extends AnyFunSuite with Matchers with MultiInstanceSuite {
  
  def entities = Set()
  instancesAndIds foreach { case (db, dbId) =>
    test(dbId + " - should be changing"){
      val a = db.now()
      Thread.sleep(15)
      val b = db.now()
      (b.getMillis - a.getMillis) should be >= 15l
    }
  }
}
