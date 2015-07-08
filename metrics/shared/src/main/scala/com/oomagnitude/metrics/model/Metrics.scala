package com.oomagnitude.metrics.model

import com.oomagnitude.metrics.model.ext._
import com.oomagnitude.metrics.model.geometry.Geometry2D
import upickle.key

// NOTE: both TimeUnit and Interpretation must live in the same file due to this issue:
// https://github.com/lihaoyi/upickle/issues/68, which was originally caused by:
// https://issues.scala-lang.org/browse/SI-7046
object Metrics {
  sealed trait Trigger

  @key("noAction")
  case object NoAction extends Trigger
  @key("reflex")
  case object Reflex extends Trigger
  @key("exploration")
  case object Exploration extends Trigger
  @key("prediction")
  case object Prediction extends Trigger


  sealed trait Sample

  object Time {val zero = Time(0,0)}
  @key("time")
  case class Time(elapsed: Double, count: Double) extends Sample

  object Count {val zero = Count(0)}
  @key("count")
  case class Count(count: Double) extends Sample

  object Scalar {val zero = Scalar(0)}
  @key("scalar")
  case class Scalar(value: Double) extends Sample

  object MutualInfos {val zero = MutualInfos(List.empty, List.empty)}
  @key("mutualInformation")
  case class MutualInfos(cells: List[CellInfo], links: List[MutualInfo]) extends Sample

  object LabeledGaussians {val zero = LabeledGaussians(List.empty)}
  @key("labeledGaussians")
  case class LabeledGaussians(gaussians: List[(String, List[LocatableGaussian])]) extends Sample

  object Vector {val zero = Vector(Seq.empty)}
  @key("vector")
  case class Vector(values: Seq[Double]) extends Sample

  object ActionTrigger {val zero = ActionTrigger(NoAction)}
  @key("actionTrigger")
  case class ActionTrigger(trigger: Trigger) extends Sample

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

  sealed trait MetricParams
  @key("gaussianParams")
  case class GaussianParams(geometry: Geometry2D, maxPrecision: Double) extends MetricParams
  @key("timeParams")
  case class TimeParams(units: TimeUnit) extends MetricParams
  @key("vectorParams")
  case class VectorParams(labels: Seq[String]) extends MetricParams

  case class MetricMetadata(id: DataSourceId, zero: Sample, parameters: Option[MetricParams])

  object MetricMetadata {
    implicit class MetaExt(meta: MetricMetadata) {
      def json: String = upickle.write(meta)
    }
  }

}

