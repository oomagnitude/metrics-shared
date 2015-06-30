package com.oomagnitude.metrics.model

import upickle.Writer

object DataPoint {
  def json[T: Writer](timestep: Int, value: T): String = {
    upickle.write(DataPoint(timestep, value)) + "\n"
  }
}
case class DataPoint[T](timestep: Int, value: T)