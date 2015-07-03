package com.oomagnitude.metrics.model

import java.text.SimpleDateFormat
import java.util.Date

object ExperimentRunId {
  val DirectoryDateFormat = new SimpleDateFormat("YYYY-MM-dd-HH-mm-ss")

  def now(experimentId: ExperimentId): ExperimentRunId = {
    val dateString = DirectoryDateFormat.format(new Date)
    ExperimentRunId(experimentId, dateString)
  }
}

case class ExperimentRunId(experimentId: ExperimentId, date: String) {
  val experiment = experimentId.experiment

  override def toString: String = experimentId.toString + "/" + date
}