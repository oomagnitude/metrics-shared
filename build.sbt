crossScalaVersions := Seq("2.11.6")

lazy val metrics = crossProject
  .settings(
    organization := "com.oomagnitude",
    name := "metrics-shared",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.6",
    autoCompilerPlugins := true,
    libraryDependencies += "com.lihaoyi" %%% "upickle" % "0.2.7",
    publishTo := Some(Resolver.file("file", new File(Path.userHome.absolutePath+"/.m2/repository")))
  )//.jvmSettings().jsSettings()

lazy val jvm = metrics.jvm
lazy val js = metrics.js

