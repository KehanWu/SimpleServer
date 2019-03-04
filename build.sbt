scalaVersion := "2.11.8"

name := "SimpleServer"
version := "1.0"

val ScalatraVersion = "2.5.4"
val JettyWeb = "9.2.15.v20160210"
val JettyPlus = "9.2.15.v20160210"


resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  "org.eclipse.jetty" % "jetty-webapp" % JettyWeb % "compile",
  "org.eclipse.jetty" % "jetty-plus" % JettyPlus % "compile",
  "com.lightbend.akka" %% "akka-stream-alpakka-slick" % "1.0-M2",
  "com.typesafe.akka" %% "akka-actor" % "2.5.21",
  "com.typesafe.akka" %% "akka-stream" % "2.5.21",
  "joda-time" % "joda-time" % "2.10.1"

)

