package com.oomagnitude.metrics.model

case class ExperimentId(experiment: String) {
  override def toString: String = experiment
}
