package com.oomagnitude.metrics.model

import com.oomagnitude.metrics.model.Metrics.TimeUnit

import scala.language.implicitConversions

trait ModelImplicits {
  implicit def stringToMetricId(name: String): MetricId = MetricId(List(name))

  implicit val timeUnit2TimeUnit = TimeUnit.timeUnit2TimeUnit _

  implicit val jTimeUnit2TimeUnit = TimeUnit.jTimeUnit2TimeUnit _
}
