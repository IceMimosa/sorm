package sorm.persisted

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers
import sorm.persisted.PersistedSuite._
import sorm._


class PersistedSuite extends AnyFunSuite with Matchers {

  test("Different persisted ids make otherwise equaling objects have different hashcodes") {
    Persisted(Genre("a"), 1).hashCode should not equal(Persisted(Genre("a"), 2).hashCode)
  }
  test("Different persisted ids make otherwise equaling objects unequal") {
    Persisted(Genre("a"), 1) should not equal(Persisted(Genre("a"), 2))
  }
  test("Same persisted ids keep equaling objects equal") {
    Persisted(Genre("a"), 1) should equal(Persisted(Genre("a"), 1))
  }
  test("all is fine") {


    val artist = Artist("Nirvana", Set(Genre("rock"), Genre("grunge")))


    val instance = Persisted(artist, 24)

    assert(instance.isInstanceOf[Persisted], "is not Persisted")
    assert(instance.isInstanceOf[Artist], "is not Artist")
    assert(instance.id == 24, "incorrect id")
    assert(instance.name == "Nirvana", "incorrect properties")

    val copy = instance.copy(name = "Puddle of Mudd")

    assert(copy.isInstanceOf[Persisted])
    assert(copy.isInstanceOf[Artist])
    //  the only problem: compiler thinks that `copy` does not implement `Persisted`, so to access `key` we have to specify it manually:
    assert(copy.asInstanceOf[Artist with Persisted].id == 24)
    assert(copy.name == "Puddle of Mudd")
    assert(copy != instance)


  }
  test("dynamic persisted") {
    val properties = Map("name" -> "Nirvana", "genres" -> Set(Genre("grunge")))
    val instance = Persisted[Artist](properties, 35)
    instance should have('name("Nirvana"))
    instance should have('genres(Set(Genre("grunge"))))
    instance should have('id(35))
  }
  test("dynamic persisted untyped iterable") {
    Persisted[Artist](Map("name" -> "", "genres" -> Set()), 0) should
    have(
      'name(""),
      'genres(Set()),
      'id(0)
    )
  }
  test("dynamic persisted fails on incorrect map") {
    an [Exception] should be thrownBy {Persisted[Artist](Map("name" -> "Nirvana"), 35)}
  }
  test("persisted on persisted") {
    an [Exception] should be thrownBy { Persisted(Persisted(Artist("Nirvana", Set()), 2), 4) }
      //.getMessage should be ("Persisted on persisted called")
  }

  test("persisted binary") {
    Persisted(GenreBinary("name", "text".getBytes()), 1L)
  }
}
object PersistedSuite {

  case class Artist(name: String, genres: Set[Genre])

  case class Genre(name: String)

  case class GenreBinary(name: String, text: Array[Byte])
}