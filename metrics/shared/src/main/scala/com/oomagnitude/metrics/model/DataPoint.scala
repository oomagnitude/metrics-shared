package com.oomagnitude.metrics.model

import upickle.Writer

object DataPoint {
  def json[T: Writer](timestep: Int, value: T): String = {
    upickle.write(DataPoint(timestep, value)) + "\n"
  }

  def zero[T](implicit z: T) = DataPoint(0, z)
}

/**
 * NOTE: timestep is an Int and not a Long because JavaScript does not support the full range of Long values. See
 * this post for more info:
 *
 * https://github.com/lihaoyi/upickle/issues/66
 *
 * @param timestep
 * @param value
 */
case class DataPoint[T](timestep: Int, value: T)