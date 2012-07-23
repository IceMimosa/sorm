package vorm.query

import vorm._
import reflection._
import structure._
import Query._

case class Query
  ( kind    : Kind,
    mapping : structure.mapping.Table,
    where   : Option[WhereNode] = None,
    order   : Seq[Order]        = Nil,
    limit   : Limit             = Limit() )
object Query {

  trait Kind
  object Kind {
    case object Select extends Kind
    case object Count extends Kind
  }

  sealed trait WhereNode
  object WhereNode {


    sealed trait Filter extends WhereNode {
      def mapping : Mapping
      def value : Any
    }

    sealed case class Equals
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class NotEquals
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class Bigger
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class BiggerIncluding
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class Smaller
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class SmallerIncluding
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class Like
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class Regex
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class In
      ( mapping : Mapping,
        value : Any )
      extends Filter

    sealed case class Contains
      ( mapping : Mapping,
        value : Any )
      extends Filter

    /**
     * Makes part of a collection
     */
    sealed case class Constitutes
      ( mapping : Mapping,
        value : Any )
      extends Filter

    /**
     * Includes a collection
     */
    sealed case class Includes
      ( mapping : Mapping,
        value : Any )
      extends Filter

    /**
     * For collections. Could be replaced by reference to `size` pseudo-
     * property, as well as there could be introduced such common properties as
     * `keys` and `values` for maps.
     */
    sealed case class HasSize
      ( mapping : Mapping,
        value : Any )
      extends Filter


    sealed trait Composite extends WhereNode

    sealed case class And
      ( left : WhereNode,
        right : WhereNode )
      extends Composite

    sealed case class Or
      ( left : WhereNode,
        right : WhereNode )
      extends Composite
  }

  case class Order
    ( mapping : Mapping,
      reverse : Boolean = false )

  case class Limit
    ( offset : Int = 0,
      amount : Option[Int] = None )
}