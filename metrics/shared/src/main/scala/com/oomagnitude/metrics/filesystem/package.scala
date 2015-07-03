package com.oomagnitude.metrics

import java.nio.file.{Path, Paths}

import com.oomagnitude.metrics.model.{DataSourceId, ExperimentId, ExperimentRunId}

package object filesystem {
  def pathForProperty(propertyName: String, default: String = "."): Path = {
    val path =
      sys.env.getOrElse(propertyName, sys.props.getOrElse(propertyName, default))
    Paths.get(path)
  }

  /**
   * Base path for experiment data. The model and results are resolved relative to this path.
   * If the `CLA_EXP` environment variable is set, base path will be set to it. Otherwise
   * the current working directory is used.
   */
  val ResultsPath = pathForProperty("CLA_EXP").resolve("results")

  implicit class ExperimentIdExt(id: ExperimentId) {
    def toPath: Path = ResultsPath.resolve(id.experiment)
  }

  implicit class ExperimentRunIdExt(id: ExperimentRunId) {
    def basePath: Path = id.experimentId.toPath.resolve(id.date)
    def metricsPath: Path = basePath.resolve("json")
  }

  implicit class DataSourceIdExt(id: DataSourceId) {
    def toJsonPath: Path = id.experimentRunId.metricsPath.resolve(id.metricId.toString + ".json")
    def toMetaPath: Path = id.experimentRunId.metricsPath.resolve(id.metricId.toString + ".meta")
  }

}
