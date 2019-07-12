scalaVersion := "2.11.12"

name := "SimpleServer"
version := "1.0"


val AkkaHttpVersion = "10.1.8"
val SprayJsonVersion = "1.3.5"
val SlickVersion = "2.1.0"
val SlickJodaMapperVersion = "1.2.0"
val PlayVersion = "2.7.1"

resolvers ++= Seq(
  Classpaths.typesafeReleases
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.9.2",
  "com.typesafe.play" %% "play-json" % PlayVersion,
  "org.json4s" % "json4s-jackson_2.11" % "3.4.1",
  "io.spray" %%  "spray-json" % SprayJsonVersion,
  "joda-time" % "joda-time" % "2.7",
  "org.joda" % "joda-convert" % "1.7",
  "ch.qos.logback" % "logback-classic" % "1.2.3",
  "org.postgresql" % "postgresql" % "9.3-1100-jdbc4",
  "com.typesafe.slick" %% "slick" % SlickVersion,
  "com.github.tototoshi" %% "slick-joda-mapper" % SlickJodaMapperVersion
  
)

enablePlugins(JavaAppPackaging)