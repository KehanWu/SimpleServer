scalaVersion := "2.11.12"

name := "SimpleServer"
version := "1.0"


val AkkaHttpVersion = "10.1.8"

resolvers ++= Seq(
  Classpaths.typesafeReleases
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-xml" % AkkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "org.json4s" % "json4s-jackson_2.11" % "3.4.1"
)

enablePlugins(JavaAppPackaging)