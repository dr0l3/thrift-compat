import com.example.thrift.generated.{ Person, S1 }
import com.twitter.finagle.Thrift
import com.twitter.util.{ Await, Awaitable, Duration, Future }

import scala.concurrent.ExecutionContext.Implicits._

class Service1Impl() extends S1.MethodPerEndpoint {
  override def createPerson(name: String, age: Long, something: String): Future[Person] = {
    Future.value(Person(name, 31, ""))
  }
}

object test extends App {
  val port = 8080
  val server = Thrift.server
    .withLabel("server3")
    .serveIface(s":$port", new Service1Impl())

  Await.ready(server)

  //  val client = Thrift.client
  //    .withLabel("client2")
  //    .build[S2.MethodPerEndpoint](s":$port")
  //
  //  val res: Future[Person] = client.createPerson("hello", 31, "woah")
  //
  //  val zomg = Await.result(res)
  //  println(zomg)
  //  println(zomg)
}
