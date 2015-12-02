# metrics-shared
Shared types for dealing with metrics

Cross-compiled to Scala.js and JVM.
To include in your project:

```
"com.oomagnitude" %%% "metrics-shared" % "0.1-SNAPSHOT"
```

Make sure that you enable the snapshots repo:

```
resolvers += Resolver.sonatypeRepo("snapshots")
```

## metric data setup

You need to specify a directory on your local machine where the JSON metrics to visualize are stored. Do this
by setting the environment variable `METRICS_ROOT`. The application will expect the following directory structure:

```
METRICS_ROOT/<expname>/<date>/
```

- `<expname>` is a human-readable name for the type of experiment being run. This corresponds to the field `ExperimentId.experiment` in the data model
- `<date>` is a string-serialized ISO date in the form `YYYY-MM-dd-HH-mm-ss`. This corresponds to the field `ExperimentRunId.date` in the data model

Under the `results/<expname>/<date>/` directory is a directory called `json` which contains individual metrics
in the form of timeseries. The filenames take the form `<metricname>.json`, which contains the timeseries data,
with each line of the file being a complete JSON object. There is another file next to it called `<metricname>.meta`
which contains meta information about the metric. Note that `<metricname>` corresponds to `DataSourceId.metricId` in the data model.

The JSON files are serialized objects by the [µpickle](https://github.com/lihaoyi/upickle) library. The data 
objects are specified in the [metrics-shared](https://github.com/oomagnitude/metrics-shared)
project and take the form `DataPoint[T]`. The time raster is in arbitrary units called `timestep`. 

### example data

The `data/` directory contains example data for showing some basic charts. Set `METRICS_ROOT` to the data directory in
order to use this data.

## publishing

This will publish to sonatype:

```
$ sbt 
> publishSigned
```

_Note: this error may happen at the end (after success messages for publishing both JVM and JS artifacts). As far as I 
can tell, it can be safely ignored:_ `java.lang.RuntimeException: Repository for publishing is not specified.`
