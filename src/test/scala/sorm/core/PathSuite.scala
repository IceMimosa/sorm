package sorm.core

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers





class PathSuite extends AnyFunSuite with Matchers {
  
  import Path._
  
  test("pathAndRemainder failure"){
    pending
  }
  test("pathAndRemainder braced parsing"){
    partAndRemainder("(asdf)") should be === (Part.Braced("asdf"), "")
    partAndRemainder("(asdf).sdf") should be === (Part.Braced("asdf"), ".sdf")
    partAndRemainder("(342).sdf") should be === (Part.Braced("342"), ".sdf")
  }
  test("pathAndRemainder dotted parsing"){
    partAndRemainder("sdf") should be === (Part.Dotted("sdf"), "")
    partAndRemainder("sdf.dksfje") should be === (Part.Dotted("sdf"), ".dksfje")
    partAndRemainder(".sdf.dksfje") should be === (Part.Dotted("sdf"), ".dksfje")
  }
}