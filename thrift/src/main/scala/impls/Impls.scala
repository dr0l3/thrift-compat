package impls

import com.example.thrift.generated._
import com.twitter.util.Future

class Service1Impl(service2Client: Service2.MethodPerEndpoint) extends Service1.MethodPerEndpoint {
  override def getUser(id: String): Future[GetUserResponse] = {
    println(s"getUser: id = $id")
    service2Client.getUserName(id)
      .map { resp =>
        resp.name match {
          case Some(name) => GetUserResponse(Some(User(id, name, 11)))
          case None => GetUserResponse(None)
        }
      }
  }
}

class Service2Impl extends Service2.MethodPerEndpoint {
  override def getUserName(id: String): Future[GetUserNameResponse] = {
    println(s"getUserName: id = $id")
    if (System.nanoTime() % 2 == 0) Future.value(GetUserNameResponse(Some("HALLELUJA"))) else Future.value(GetUserNameResponse(None))
  }

  override def getString(input: String): Future[String] = {
    Future.value(s"Hello: $input")
  }

  override def getComplicated(input: String, number: Short, boolean: Boolean): Future[String] = {
    Future.value(s"Hello: $input $number $boolean")
  }
}

