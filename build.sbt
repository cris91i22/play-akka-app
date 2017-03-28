lazy val root = (project in file("."))
  .settings(name := "play-akka-app")
  .settings(version := "1.0.0-SNAPSHOT")
  .settings(scalaVersion := "2.11.7")
  .settings(routesGenerator := InjectedRoutesGenerator)
  .enablePlugins(PlayScala)

scalacOptions := Seq("-unchecked", "-deprecation", "-encoding", "utf8")

libraryDependencies ++= Seq( jdbc , cache , ws   , specs2 % Test,
"com.typesafe.slick"          %% "slick"            % "3.1.1",
"com.github.tminglei"         %% "slick-pg"         % "0.14.1",
"com.typesafe.slick"          %% "slick-hikaricp"   % "3.1.1",
"ch.qos.logback"      % "logback-classic"     % "1.2.1",
"com.zaxxer"          % "HikariCP"            % "2.6.0",
"org.postgresql"      % "postgresql"          % "9.4.1208.jre7",
"joda-time"           % "joda-time"           % "2.7")

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  
