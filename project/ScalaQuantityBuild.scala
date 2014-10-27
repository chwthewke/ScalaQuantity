import sbt._
import sbt.Keys._
import com.typesafe.sbt.SbtScalariform._
import com.typesafe.sbteclipse.core.EclipsePlugin._
import scalariform.formatter.preferences._

object ScalaQuantityBuild extends Build {

  lazy val scalaQuantityScalariformSettings = scalariformSettings ++ Seq(
    ScalariformKeys.preferences := defaultPreferences
      .setPreference( AlignSingleLineCaseStatements, true )
      .setPreference( SpaceBeforeColon, true )
      .setPreference( SpaceInsideParentheses, true )    
  )

  lazy val scalaQuantitySettings = 
    Project.defaultSettings ++
    scalaQuantityScalariformSettings ++
    Seq(
      organization := "net.chwthewke",
      scalaVersion := "2.9.1",
      libraryDependencies += "org.scalatest" %% "scalatest" % "1.7.1" % "test" withSources () withJavadoc (),
      //scalacOptions ++= Seq( "-feature", "-deprecation" ),
      unmanagedSourceDirectories in Compile := (scalaSource in Compile).value :: Nil,
      unmanagedSourceDirectories in Test := (scalaSource in Test).value :: Nil,
      EclipseKeys.projectFlavor := EclipseProjectFlavor.Scala,
      EclipseKeys.createSrc := EclipseCreateSrc.Default + EclipseCreateSrc.Resource
    )

  lazy val scalaQuantity = Project(
    id = "scala-quantity",
    base = file( "." ),
    settings = scalaQuantitySettings ++
      Seq(
        name := "scala-quantity",
        initialCommands := "import scalaquantity._"
      )
  )

}
