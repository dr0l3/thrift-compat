import com.example.thrift.v2.generated.Service
import com.twitter.finagle.Thrift
import com.twitter.util.Await
import server.{ServerImplV1, ServerImplV2}

object Test extends App{
  val port = 14455

  val server = Thrift.server.serveIface(s":$port",new ServerImplV1)

  val client = Thrift.client.build[Service.MethodPerEndpoint](s":$port")

  val res = Await.result(client.createPerson("rune", 31, "meh"))

  println(res)

}
