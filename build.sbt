crossScalaVersions := Seq("2.11.6")

lazy val metrics = crossProject
  .settings(
    organization := "com.oomagnitude",
    name := "metrics-shared",
    version := "0.1-SNAPSHOT",
    scalaVersion := "2.11.6",
    autoCompilerPlugins := true,
    libraryDependencies ++= Seq(
      "com.lihaoyi" %%% "upickle" % "0.3.0"
    ),
    licenses := Seq("Apache 2" -> url("http://www.apache.org/licenses/LICENSE-2.0")),
    homepage := Some(url("https://github.com/oomagnitude/metrics-shared")),
    publishMavenStyle := true,
    publishArtifact in Test := false,
    pomIncludeRepository := { _ => false },
    publishTo := {
      val nexus = "https://oss.sonatype.org/"
      if (isSnapshot.value)
        Some("snapshots" at nexus + "content/repositories/snapshots")
      else
        Some("releases"  at nexus + "service/local/staging/deploy/maven2")
    },
    pomExtra := (
        <scm>
          <url>git@github.com:oomagnitude/metrics-shared.git</url>
          <connection>scm:git:git@github.com:oomagnitude/metrics-shared.git</connection>
        </scm>
        <developers>
          <developer>
            <id>yakticus</id>
            <name>Julie Pitt</name>
            <url>http://oomagnitude.com</url>
          </developer>
        </developers>)
  ).jvmSettings(
    libraryDependencies ++= Seq(
      "com.lihaoyi" %% "ammonite-ops" % "0.4.5"
    )
  ).jsSettings()

lazy val jvm = metrics.jvm
lazy val js = metrics.js
