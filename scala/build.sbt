name := "advent-of-code-2015"

version := "1.0"

scalaVersion := "2.11.7"

resourceDirectory in Compile := baseDirectory.value / "data"

libraryDependencies += "junit" % "junit" % "4.11" % "test"

// This implements the sbt test interface and calls our JUnit test suite so that "sbt test" from the command line works.
libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"

// Tell Junit to print the name of each test during "sbt test":
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v")