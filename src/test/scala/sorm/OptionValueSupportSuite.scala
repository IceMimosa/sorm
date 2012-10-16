package sorm

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import sorm._
import core._
import persisted._
import query._
import reflection._
import save._
import structure._
import mapping._
import jdbc._
import create._
import sext._

import samples._
import Sorm._

@RunWith(classOf[JUnitRunner])
class OptionValueSupportSuite extends FunSuite with ShouldMatchers {

  import OptionValueSupportSuite._

  val db = TestingInstance.h2( Entity[EntityWithValuePropertyInOption]() )

  test("saving goes ok"){
    db.save(EntityWithValuePropertyInOption(None))
    db.save(EntityWithValuePropertyInOption(Some(3)))
    db.save(EntityWithValuePropertyInOption(Some(7)))
  }
  test("saved entities are correct"){
    db.fetchById[EntityWithValuePropertyInOption](1).get.a should be === None
    db.fetchById[EntityWithValuePropertyInOption](2).get.a should be === Some(3)
    db.fetchById[EntityWithValuePropertyInOption](3).get.a should be === Some(7)
  }
  test("equals filter"){
    db.one[EntityWithValuePropertyInOption]
      .filterEqual("a", None).fetch().get.id should be === 1
    db.one[EntityWithValuePropertyInOption]
      .filterEqual("a", Some(3)).fetch().get.id should be === 2
  }
  test("not equals filter"){
    db.all[EntityWithValuePropertyInOption]
      .filterNotEqual("a", None)
      .fetch().map{_.id.toInt}.toSet
      .should( not contain (1) and contain (3) and contain (2) )
    db.all[EntityWithValuePropertyInOption]
      .filterNotEqual("a", Some(3))
      .fetch().map{_.id.toInt}.toSet
      .should( not contain (2) and contain (1) and contain (3) )
  }

}
object OptionValueSupportSuite {

  case class EntityWithValuePropertyInOption
    ( a : Option[Int] )

}