package com.oomagnitude.metrics.model.ext

import com.oomagnitude.metrics.model.geometry.Coordinate2D

trait Locatable {
  def location: Coordinate2D
}
