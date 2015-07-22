package com.oomagnitude.metrics.model

object MetricId {
  val separator = "."
}

case class MetricId(path: List[String], separator: String = MetricId.separator) {

  def /(rest: MetricId) = MetricId(path ::: rest.path)
  def /(rest: String) = MetricId(path ::: List(rest))

  def parent: Option[MetricId] = path match {
    case Nil => None
    case last :: Nil => None
    case list => Some(MetricId(list.reverse.tail.reverse))
  }

  def startsWith(other: MetricId): Boolean = {
    path.size >= other.path.size && other.path.indices.forall(i => path(i) == other.path(i))
  }

  override val toString = path.mkString(separator)
}