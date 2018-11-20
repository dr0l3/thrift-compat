import com.example.thrift.generated.S1
import com.twitter.finagle.Thrift
import com.twitter.util.{ Await, Future }

object test extends App {

  val port = 12345
  val client = Thrift.client
    .withLabel("client2")
    .build[S1.MethodPerEndpoint](s"127.0.0.1:$port")

  val res = client.createPerson("hello", 31, "something")

  val zomg = Await.result(res)
  println(zomg)
}
