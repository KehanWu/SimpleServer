scalaVersion := "2.11.12"

name := "SimpleServer"
version := "1.0"


resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"   % "10.1.8",
  "com.typesafe.akka" %% "akka-stream" % "2.5.19"
)

enablePlugins(JavaAppPackaging)