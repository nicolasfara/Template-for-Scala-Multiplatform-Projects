import scala.scalanative.build.*

val scala3Version = "3.4.2"

lazy val root = crossProject(JSPlatform, JVMPlatform, NativePlatform)
  .in(file("."))
  .configs()
    .nativeSettings(
      nativeConfig ~= {
        _.withLTO(LTO.thin)
          .withMode(Mode.releaseSize)
          .withGC(GC.immix)
          .withTargetTriple("aarch64-apple-macosx11.0.0")
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

lazy val macOsArm64 = root.native
  .settings(
    nativeConfig ~= {
      _.withTargetTriple("aarch64-apple-macosx11.0.0")
    }
  )

lazy val macOsX64 = root.native
  .settings(
    nativeConfig ~= {
      _.withTargetTriple("x86_64-apple-macosx10.14.0")
    }
  )