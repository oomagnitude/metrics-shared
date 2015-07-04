package com.oomagnitude.metrics.model

case class ExperimentRunId(experimentId: ExperimentId, date: String) {
  val experiment = experimentId.experiment

  override def toString: String = experimentId.toString + "/" + date
}