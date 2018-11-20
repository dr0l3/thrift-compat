package servers

import com.example.thrift.generated.BinaryService
import com.twitter.finagle.Thrift
import com.twitter.finagle.stats.JavaLoggerStatsReceiver
import com.twitter.finagle.tracing.Annotation.ClientAddr
import com.twitter.finagle.tracing.Trace
import com.twitter.util.Future
import org.apache.thrift.protocol.TJSONProtocol

object Servers {
  class ServerImpl extends BinaryService.MethodPerEndpoint {
    def fetchBlob(id: Long): Future[String] = {
      Trace.record("starting some extremely expensive computation")

      Thread.sleep(5000)
      Trace.record("Finished")
      Future.value("Hello world")
    }
  }

  def createServer3(port: Int) = {

    val server = Thrift.server
      .withLabel("server3")
      .serveIface(s":$port", new ServerImpl)
    server
  }

  def createServer2(port: Int, clientAddr: String) = {
    class ServerImpl extends BinaryService.MethodPerEndpoint {
      val methodPerEndpoint: BinaryService.MethodPerEndpoint =
        Thrift.client
          .withLabel("client2")
          .build[BinaryService.MethodPerEndpoint](clientAddr)

      def fetchBlob(id: Long): Future[String] = {
        methodPerEndpoint.fetchBlob(1234L)
      }
    }
  }

  def createServer1(port: Int, clientAddr: String) = {
    class ServerImpl extends BinaryService.MethodPerEndpoint {
      val methodPerEndpoint: BinaryService.MethodPerEndpoint =
        Thrift.client
          .withLabel("client1")
          .build[BinaryService.MethodPerEndpoint](clientAddr)

      def fetchBlob(id: Long): Future[String] = {
        println("fetching")
        methodPerEndpoint.fetchBlob(1234L)
      }
    }
    val server = Thrift.server
      .withLabel("server2")
      .serveIface("inet!localhost:1235", new ServerImpl)
  }

  def createClient(port: Int) = {
    val methodPerEndpoint: BinaryService.MethodPerEndpoint =
      Thrift.client
        .withLabel("client0")
        .build[BinaryService.MethodPerEndpoint]("localhost:$port")
    methodPerEndpoint
  }
}
