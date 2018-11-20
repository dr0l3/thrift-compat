package server

import com.example.thrift.v2.generated.{Person, Service}
import com.twitter.util.Future

class ServerImplV2 extends Service.MethodPerEndpoint {
  override def createPerson(name: String, age: Long, something: String): Future[Person] = Future(Person(name,age, something))
}
