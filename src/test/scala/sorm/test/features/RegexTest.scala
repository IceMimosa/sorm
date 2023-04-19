package sorm.test.features

import org.scalatest._
import sorm.Entity
import RegexTest._
import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import sorm.core.DbType
import sorm.test.MultiInstanceSuite


class RegexTest extends AnyFunSpec with Matchers with MultiInstanceSuite {

  def entities = Set() + Entity[User]()
  override def dbTypes = DbType.H2 :: DbType.Mysql :: DbType.Postgres :: Nil

  instancesAndIds foreach { case (db, dbId) =>

    val a1 = db.save(User("abc1"))
    val a2 = db.save(User("abc2"))
    val a3 = db.save(User("abc3"))

    describe(dbId + " - regex"){
      db.query[User].whereRegex("fullname", "^abc").count().should(equal(3))
    }
    describe(dbId + " - not regex"){
      db.query[User].whereNotRegex("fullname", "^abc1").count().should(equal(2))
    }

  }

}
object RegexTest {
  case class User(fullname: String)
}