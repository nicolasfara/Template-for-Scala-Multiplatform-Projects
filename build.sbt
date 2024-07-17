import scala.scalanative.build.*

val scala3Version = "3.4.2"

lazy val root = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("."))
  .configs()
    .nativeSettings(
      nativeConfig ~= {
        _.withLTO(LTO.default)
          .withMode(Mode.releaseSize)
          .withGC(GC.immix)
      }
    )
    .jsSettings(
      libraryDependencies += "org.scala-js" %%% "scalajs-dom" % "2.8.0",
      scalaJSUseMainModuleInitializer := true,
      scalaJSLinkerConfig ~= { _.withOptimizer(true) }
    )
  .settings(
    name := "Template-for-Scala-Multiplatform-Projects",
    scalaVersion := scala3Version,
    scalacOptions ++= Seq(
      "-rewrite",
      "-indent",
    ),
    libraryDependencies ++= Seq()
  )
