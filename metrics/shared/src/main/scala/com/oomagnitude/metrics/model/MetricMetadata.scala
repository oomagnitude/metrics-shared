package com.oomagnitude.metrics.model

import upickle.key

// NOTE: both TimeUnit and Interpretation must live in the same file due to this issue:
// https://github.com/lihaoyi/upickle/issues/68, which was originally caused by:
// https://issues.scala-lang.org/browse/SI-7046

sealed trait TimeUnit
@key("nanoseconds") case object Nanoseconds extends TimeUnit
@key("microseconds") case object Microseconds extends TimeUnit
@key("milliseconds") case object Milliseconds extends TimeUnit
@key("seconds") case object Seconds extends TimeUnit
@key("minutes") case object Minutes extends TimeUnit
@key("hours") case object Hours extends TimeUnit
@key("days") case object Days extends TimeUnit

object TimeUnit {
  implicit def timeUnit2TimeUnit(timeUnit:TimeUnit): java.util.concurrent.TimeUnit = {
    timeUnit match {
      case Nanoseconds => java.util.concurrent.TimeUnit.NANOSECONDS
      case Microseconds => java.util.concurrent.TimeUnit.MICROSECONDS
      case Milliseconds => java.util.concurrent.TimeUnit.MILLISECONDS
      case Seconds => java.util.concurrent.TimeUnit.SECONDS
      case Minutes => java.util.concurrent.TimeUnit.MINUTES
      case Hours => java.util.concurrent.TimeUnit.HOURS
      case Days => java.util.concurrent.TimeUnit.DAYS
    }
  }

  implicit def jTimeUnit2TimeUnit(timeUnit: java.util.concurrent.TimeUnit): TimeUnit = {
    timeUnit match {
      case java.util.concurrent.TimeUnit.NANOSECONDS => Nanoseconds
      case java.util.concurrent.TimeUnit.MICROSECONDS => Microseconds
      case java.util.concurrent.TimeUnit.MILLISECONDS => Milliseconds
      case java.util.concurrent.TimeUnit.SECONDS => Seconds
      case java.util.concurrent.TimeUnit.MINUTES => Minutes
      case java.util.concurrent.TimeUnit.HOURS => Hours
      case java.util.concurrent.TimeUnit.DAYS => Days
    }
  }
}

sealed trait Interpretation
@key("count")
case object Count extends Interpretation  // DataPoint[Double]
@key("scalar")
case object Scalar extends Interpretation // DataPoint[Double]
@key("info")
case class Info(dataType: String) extends Interpretation // DataPoint[dataType]
@key("time")
case class Time(units: TimeUnit) extends Interpretation // DataPoint[TimerSample]
@key("vector")
case class Vector(labels: Seq[String]) extends Interpretation // DataPoint[Seq[Double]]

case class MetricMetadata(id: MetricId, interpretation: Interpretation)

object MetricMetadata {
  implicit class MetaExt(meta: MetricMetadata) {
    def json: String = upickle.write(meta)
  }
}
