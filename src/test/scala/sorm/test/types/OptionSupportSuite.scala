package sorm.test.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._
import sext._, embrace._
import sorm.test.MultiInstanceSuite


class OptionSupportSuite extends AnyFunSuite with Matchers with MultiInstanceSuite {
  import OptionSupportSuite._

  def entities = Entity[EntityWithOptionInOption]() :: Nil

}
object OptionSupportSuite {

  case class EntityWithOptionInOption
    ( optionInOption : Option[Option[Int]] )

}

