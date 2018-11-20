package server

import com.example.thrift.v1.generated.{Person, Service}
import com.twitter.util.Future

class ServerImplV1 extends Service.MethodPerEndpoint {
  override def createPerson(name: String, age: Long): Future[Person] = Future(Person(name,age))
}
