package com.oomagnitude.metrics.model.ext

case class MutualInfo(cells: (String, String), jc: Double, ejc: Double)
case class CellInfo(id: String, numConnections: Int)
case class MutualInfos(cells: List[CellInfo], links: List[MutualInfo])

object MutualInfos {
  val zero = MutualInfos(List.empty, List.empty)
}
