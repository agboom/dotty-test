lazy val root = (project in file(".")).
  settings(
    name := "dotty-test",
    description := "Test use cases for dotty compiler",
    version := "0.1",
    mainClass in (Compile, run) := Some("Main"),


    // All the settings set below this line are important to get your project
    // to compile with Dotty. Please read the comments carefully.

    // Dotty version
    scalaVersion := "0.1-SNAPSHOT",

    // Enable Scala 2 compatibility mode.
    // This will allow you to use Scala 2 features that have been removed
    // from Dotty, like procedure syntax.
    // This is not required to compile code with Dotty, but it makes it easier
    // to test Dotty on an existing Scala 2 code base.
    // The long-term plan is to have a rewriting tool that can do most of the
    // porting work for you.
    scalacOptions ++= Seq("-language:Scala2"),

    // Note: Dotty can use Scala 2.11 libraries so we set `scalaBinaryVersion`
    // to `2.11` for convenience. However, if you publish an artefact compiled
    // with Dotty, you should set it to `0.1`, this will force you to change
    // your library dependencies to be of the form `"org.foo" % "bar_2.11" % "1.0"`
    // instead of `"org.foo" %% "bar" % "1.0"`
    scalaBinaryVersion := "2.11",

    // By default, sbt will depend on the scala-library version `scalaVersion`,
    // so we need to override it.
    //autoScalaLibrary := false,
    // 2.11.5 is the version used by Dotty itself currently, we do the same to
    // avoid trouble.
    libraryDependencies += "org.scala-lang" % "scala-library" % "2.11.5",

    // Maintained at https://github.com/smarter/dotty-bridge
    scalaCompilerBridgeSource := ("ch.epfl.lamp" % "dotty-bridge" % "0.1-SNAPSHOT" % "component").sources()
  )
