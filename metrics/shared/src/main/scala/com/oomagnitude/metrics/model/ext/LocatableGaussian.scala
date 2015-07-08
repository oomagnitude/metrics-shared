package com.oomagnitude.metrics.model.ext

import com.oomagnitude.metrics.model.geometry.Coordinate2D

case class LocatableGaussian(location: Coordinate2D, gaussian: Gaussian) extends Locatable
