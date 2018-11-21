package server

import com.example.thrift.v2.generated.{ Color, Person, Service, UserType }
import com.twitter.util.Future

class ServerImplV2 extends Service.MethodPerEndpoint {
  override def createPersonNormal(name: String, age: Long, something: String): Future[Person] = {
    Future(Person(name, age, something))
  }

  override def createPersonReq(name: String, age: Long, something: String): Future[Person] = {
    Future(Person(name, age, something))
  }

  override def createPersonOptional(name: String, age: Long, something: String): Future[Person] = {
    Future(Person(name, age, something))
  }

  override def createPersonChangeType(name: String, age: String): Future[Person] = {
    Future(Person(name, age.toLong, "s"))
  }

  override def createPersonReqToNormal(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonReqToOptional(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonNormalToRequired(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonNormalToOptional(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonOptionalNormal(name: String, age: Option[Long]): Future[Person] = {
    Future(Person(name, age.getOrElse(1), "s"))
  }

  override def createPersonOptionalRequired(name: String, age: Option[Long]): Future[Person] = {
    Future(Person(name, age.getOrElse(1), "s"))
  }

  override def createPersonHairColor(name: String, age: Long, color: Color): Future[Person] = {
    println(color)
    Future(Person(name, age, "s"))
  }

  override def createPersonTypedef(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonTypedefNormal(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonRemoveNormal(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonRemoveOptional(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonRemoveRequired(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonUserType(name: String, age: Long, userType: UserType): Future[Person] = {
    println(userType)
    Future(Person(name, age, "s"))
  }
}
