package com.oomagnitude.metrics.model
import upickle.default._
import upickle.{default => upickle}

object DataPoint {
  def zero[T](implicit z: T) = DataPoint(0, z)

  def read[T: Reader](json: String): DataPoint[T] = upickle.read[DataPoint[T]](json)
  def write[T: Writer](dataPoint: DataPoint[T]): String = upickle.write(dataPoint)
  def write[T: Writer](timestep: Int, sample: T): String = write(DataPoint(timestep, sample))
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