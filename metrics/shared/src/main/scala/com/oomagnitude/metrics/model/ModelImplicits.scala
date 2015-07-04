package com.oomagnitude.metrics.model

import scala.language.implicitConversions

trait ModelImplicits {
  implicit def stringToMetricId(name: String): MetricId = MetricId(List(name))

  implicit val timeUnit2TimeUnit = TimeUnit.timeUnit2TimeUnit _

  implicit val jTimeUnit2TimeUnit = TimeUnit.jTimeUnit2TimeUnit _

  implicit val experimentIdOrdering = new Ordering[ExperimentId] {
    override def compare(x: ExperimentId, y: ExperimentId): Int = x.experiment.compare(y.experiment)
  }

  implicit val experimentRunOrdering = new Ordering[ExperimentRunId] {
    override def compare(x: ExperimentRunId, y: ExperimentRunId): Int = y.date.compare(x.date)
  }

  implicit val metricIdOrdering = new Ordering[MetricId] {
    override def compare(x: MetricId, y: MetricId): Int = x.toString.compare(y.toString)
  }

  implicit val dataSourceOrdering = new Ordering[DataSourceId] {
    override def compare(x: DataSourceId, y: DataSourceId): Int = metricIdOrdering.compare(x.metricId, y.metricId)
  }

  implicit val metadataOrdering = new Ordering[MetricMetadata] {
    override def compare(x: MetricMetadata, y: MetricMetadata): Int = dataSourceOrdering.compare(x.id, y.id)
  }
}
