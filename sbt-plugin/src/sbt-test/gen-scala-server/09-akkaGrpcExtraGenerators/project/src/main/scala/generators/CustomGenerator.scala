package generators

import akka.grpc.gen.Logger
import akka.grpc.gen.scaladsl.{ScalaCodeGenerator, Service}
import com.google.protobuf.compiler.PluginProtos.CodeGeneratorResponse

import scala.collection.immutable

object CustomGenerator extends ScalaCodeGenerator {

  override def name: String = "custom-generator"

  override def perServiceContent: Set[(Logger, Service) => immutable.Seq[CodeGeneratorResponse.File]] =
    super.perServiceContent + generate

  private val generate: (Logger, Service) => immutable.Seq[CodeGeneratorResponse.File] =
    (logger, service) => {
      val b = CodeGeneratorResponse.File.newBuilder()
      b.setContent("// Auto-generated")
      b.setName(s"${service.packageDir}/Custom${service.name}.scala")
      immutable.Seq(b.build)
    }
}
