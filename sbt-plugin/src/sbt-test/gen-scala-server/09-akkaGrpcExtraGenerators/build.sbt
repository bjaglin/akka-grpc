resolvers += Resolver.sonatypeRepo("staging")

enablePlugins(AkkaGrpcPlugin)

Compile / akkaGrpcExtraGenerators += generators.CustomGenerator