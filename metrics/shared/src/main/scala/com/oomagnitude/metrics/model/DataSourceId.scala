package com.oomagnitude.metrics.model

case class DataSourceId(experimentRunId: ExperimentRunId, metricId: MetricId) {
  val experiment = experimentRunId.experiment
  val date = experimentRunId.date

  override def toString: String = experimentRunId.toString + "/" + metricId.toString
}