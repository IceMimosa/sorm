package sorm.test

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import core.DbType


class TestingInstancesSuite extends AnyFunSuite with Matchers {
  import TestingInstancesSuite._

  test("Sequentially accessing instances gets them cleaned up") {
    TestingInstances.instances( Set() + Entity[A](), poolSizes = 1 :: 6 :: 12 :: Nil, dbTypes = DbType.H2 :: Nil )
      .map { case (db, dbId) => db.save(A(1)) ; db.query[A].count() }
      .shouldBe(1 :: 1 :: 1 :: Nil)
  }
  
}
object TestingInstancesSuite {

  case class A ( a : Int )

}