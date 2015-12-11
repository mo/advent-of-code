name := "advent-of-code-2015"

version := "1.0"

scalaVersion := "2.11.7"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

// This implements the sbt test interface and calls our JUnit test suite so that "sbt test" from the command line works.
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
