package com.oomagnitude.metrics

import ammonite.{ops => amm}
import com.oomagnitude.metrics.model.{DataSourceId, ExperimentId, ExperimentRunId}

package object filesystem {

  def pathOf(propertyName: String): amm.Path = {
    val dir = sys.env.get(propertyName).orElse(sys.props.get(propertyName))
    dir.map(d => amm.Path(d)).getOrElse(amm.cwd)
  }

  /**
   * Base path for experiment data. The model and results are resolved relative to this path.
   * If the `CLA_EXP` environment variable is set, base path will be set to it. Otherwise
   * the current working directory is used.
   */
  val ResultsPath = pathOf("CLA_EXP") / 'results

  implicit class ExperimentIdExt(id: ExperimentId) {
    def path: amm.Path = ResultsPath / id.experiment
  }

  implicit class ExperimentRunIdExt(id: ExperimentRunId) {
    def basePath: amm.Path = id.experimentId.path / id.date
    def metricsPath: amm.Path = basePath / 'json
  }

  implicit class DataSourceIdExt(id: DataSourceId) {
    def jsonFilename = id.metricId.toString + ".json"
    def metaFilename = id.metricId.toString + ".meta"
    def jsonPath: amm.Path = id.experimentRunId.metricsPath / jsonFilename
    def metaPath: amm.Path = id.experimentRunId.metricsPath / metaFilename
  }

}
