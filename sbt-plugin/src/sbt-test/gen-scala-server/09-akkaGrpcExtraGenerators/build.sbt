resolvers += Resolver.sonatypeRepo("staging")

enablePlugins(AkkaGrpcPlugin)

Compile / akkaGrpcExtraGenerators += generators.CustomGenerator

Seq(Compile, Test).flatMap(inConfig(_) {
  Seq(
    // workaround to include the metabuild project classes in the classpath of the sandboxed generators
    PB.artifactResolver := {
      val prev = PB.artifactResolver.value
      val metaBuildClasses = (ThisBuild / baseDirectory).value / "project/target/scala-2.12/sbt-1.0/classes/"
      artifact: protocbridge.Artifact => prev(artifact) :+ metaBuildClasses
    }
  )
})