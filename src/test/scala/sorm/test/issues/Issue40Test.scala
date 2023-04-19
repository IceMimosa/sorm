package sorm.test.issues

import org.scalatest._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers


class Issue40Test extends AnyFunSuite with Matchers {
  import sorm._
  import Issue40Test._

  test("All is fine"){
    new Instance (
      entities = Set(Entity[Users]()),
      url = "jdbc:h2:file:target/test/db/users",
      initMode = InitMode.Create,
      poolSize = 20
    )
    new Instance (
      entities = Set(Entity[Users]()),
      url = "jdbc:h2:file:target/test/db/users",
      initMode = InitMode.Create,
      poolSize = 20
    )
  }

}
object Issue40Test {
  case class Users()
}