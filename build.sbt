scalaVersion := "2.12.7"

name := "SimpleServer"
version := "1.0"

val ScalatraVersion = "2.5.4"
val JettyVersion = "9.2.22.v20170606"


resolvers += Classpaths.typesafeReleases

libraryDependencies ++= Seq(
  "org.scalatra" %% "scalatra" % ScalatraVersion,
  "org.scalatra" %% "scalatra-scalatest" % ScalatraVersion % "test",
  "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
  "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided",
  
  "org.eclipse.jetty" % "jetty-webapp" % JettyVersion % "compile",
  "org.eclipse.jetty" % "jetty-plus" % JettyVersion % "compile"
)
  
