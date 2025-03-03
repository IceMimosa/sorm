package sorm.test.types

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

import sorm._

import samples._
import sorm.test.MultiInstanceSuite


class SeqOfSeqsSupportSuite extends AnyFunSuite with Matchers with MultiInstanceSuite {

  import SeqOfSeqsSupportSuite._

  def entities =  Set() + Entity[A]()
  instancesAndIds foreach { case (db, dbId) =>
    val a1 = db.save(A( Seq() ))
    val a2 = db.save(A( Seq( Seq(2, 3), Seq(), Seq(7) ) ))
    val a3 = db.save(A( Seq( Seq() ) ))
    val a4 = db.save(A( Seq( Seq(78) ) ))
    val a5 = db.save(A( Seq() ))

    test(dbId + " - Empty Seq matches empty Seq and not Seq of empty Seq"){
      db.query[A]
        .whereEqual("a", Seq())
        .fetch()
        .should(
          contain (a1) and
          contain (a5) and
          not contain (a3)
        )
    }
    test(dbId + " - An empty item Seq does not match inexistent one"){
      db.query[A]
        .whereEqual("a.item", Seq())
        .fetch()
        .should(
          not contain (a1) and
          not contain (a5)
        )
    }
    test(dbId + " - A partially matching seq with container seq containing other seq of same size"){
      db.query[A]
        .whereEqual("a.item", Seq(2))
        .fetch()
        .should( not contain (a2) )
    }
  }
}
object SeqOfSeqsSupportSuite {
  case class A ( a : Seq[Seq[Int]] )
}