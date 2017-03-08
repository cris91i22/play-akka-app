lazy val root = (project in file("."))
  .settings(name := "play-akka-app")
  .settings(version := "1.0.0-SNAPSHOT")
  .settings(scalaVersion := "2.11.7")
  .settings(routesGenerator := InjectedRoutesGenerator)
  .enablePlugins(PlayScala)

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test )

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
