package vorm.reflection

object Sandbox extends App {
  case class Genre(name: String)
  case class Artist(id: String, name: String, genres: Set[Genre], tags: Set[String]) {
    def sings(song: String) = song + "sldfjsldkjf"
  }

  val artist = Artist("234", "Nirvana", Set(Genre("Grunge"), Genre("Rock")), Set("kurt-cobain", "grunge", "nirvana"))

//  tpe[Artist].properties.foreach(println)
//  tpe[Artist].methods.foreach(println)


}
