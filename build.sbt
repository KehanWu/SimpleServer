scalaVersion := "2.11.12"

name := "SimpleServer"
version := "1.0"


val AkkaHttpVersion = "10.1.8"
val SprayJsonVersion = "1.3.5"

resolvers ++= Seq(
  Classpaths.typesafeReleases
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "org.json4s" % "json4s-jackson_2.11" % "3.4.1",
  "io.spray" %%  "spray-json" % SprayJsonVersion,
  "joda-time" % "joda-time" % "2.10.3",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2"
)

enablePlugins(JavaAppPackaging)