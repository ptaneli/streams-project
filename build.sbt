
// *****************************************************************************
// Projects
// *****************************************************************************

lazy val streams =
  project
    .in(file("."))
    .enablePlugins(AutomateHeaderPlugin)
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        library.cats,
        library.catsEffect,
        library.circeCore,
        library.circeGeneric,
        library.circeParser,
        library.fs2Core,
        library.http4sCore,
        library.http4s,
        library.http4sServer,
        library.http4sBlazeServer,
        library.http4sCirce,
        library.log4s,
        library.scalaCheck,
        library.scalaTest,
        library.scalaTestPlusScalaCheck,
      )
    )

// *****************************************************************************
// Library dependencies
// *****************************************************************************

lazy val library =
  new {
    object Version {
      val cats = "1.6.0"
      val catsEffect = "1.3.0"
      val circe = "0.10.0"
      val fs2 = "1.0.4"
      val http4s = "0.20.0"
      val logback = "1.2.3"
      val log4s = "1.7.0"
      val scalaCheck = "1.14.0"
      val scalaTest  = "3.0.6"
      val scalaTestPlusScalaCheck = "1.0.0-SNAP4"
    }
    //See https://github.com/typelevel/cats
    val cats =  "org.typelevel" %% "cats-core" % Version.cats
    // See https://github.com/typelevel/cats-effect
    val catsEffect = "org.typelevel" %% "cats-effect" % Version.catsEffect
    // See https://circe.github.io/circe/
    val circeCore =   "io.circe" %% "circe-core" % Version.circe
    val circeGeneric = "io.circe" %% "circe-generic" % Version.circe
    val circeParser = "io.circe" %% "circe-parser" % Version.circe
    // See https://fs2.io
    val fs2Core = "co.fs2" %% "fs2-core" % Version.fs2
    // See https://github.com/http4s/http4s
    val http4sCore                = "org.http4s"              %% "http4s-core"                              % Version.http4s
    val http4s                    = "org.http4s"              %% "http4s-dsl"                               % Version.http4s
    val http4sServer              = "org.http4s"              %% "http4s-server"                            % Version.http4s
    val http4sBlazeServer         = "org.http4s"              %% "http4s-blaze-server"                      % Version.http4s
    val http4sCirce               = "org.http4s"              %% "http4s-circe"                             % Version.http4s
    // See https://github.com/Log4s/log4s
    val log4s                     = "org.log4s"               %% "log4s"                                    % Version.log4s
    // See https://www.scalacheck.org 
    val scalaCheck = "org.scalacheck" %% "scalacheck" % Version.scalaCheck % Test
    // See http://www.scalatest.org
    val scalaTest  = "org.scalatest"  %% "scalatest"  % Version.scalaTest % Test
    val scalaTestPlusScalaCheck =  "org.scalatestplus" %% "scalatestplus-scalacheck" % Version.scalaTestPlusScalaCheck
  }

// *****************************************************************************
// Settings
// *****************************************************************************

lazy val settings =
  commonSettings ++
  scalafmtSettings

lazy val commonSettings =
  Seq(
    scalaVersion := "2.12.8",
    organization := "org.scalabridge",
    organizationName := "ScalaBridge",
    startYear := Some(2019),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0")),
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
      "-Ypartial-unification",
      "-Ywarn-unused-import",
    ),
    Compile / unmanagedSourceDirectories := Seq((Compile / scalaSource).value),
    Test / unmanagedSourceDirectories := Seq((Test / scalaSource).value),
)

lazy val scalafmtSettings =
  Seq(
    scalafmtOnCompile := true,
  )
