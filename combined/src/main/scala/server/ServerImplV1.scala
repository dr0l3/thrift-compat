package server

import com.example.thrift.v1.generated._
import com.twitter.util.Future

class ServerImplV1 extends Service.MethodPerEndpoint {
  override def createPersonNormal(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonReq(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonOptional(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonChangeType(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonReqToNormal(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonReqToOptional(name: String, age: Option[Long]): Future[Person] = Future(Person(name, age.getOrElse(1), "s"))

  override def createPersonNormalToRequired(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonNormalToOptional(name: String, age: Option[Long]): Future[Person] = Future(Person(name, age.getOrElse(1), "s"))

  override def createPersonOptionalNormal(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonOptionalRequired(name: String, age: Long): Future[Person] = Future(Person(name, age, "s"))

  override def createPersonHairColor(name: String, age: Long, color: Color): Future[Person] = Future {
    println(color)
    Person(name, age, "s")
  }

  override def createPersonTypedef(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonTypedefNormal(name: String, age: Long): Future[Person] = {
    Future(Person(name, age, "s"))
  }

  override def createPersonRemoveNormal(name: String, age: Long, something: String): Future[Person] = {
    Future(Person(name, age, something))
  }

  override def createPersonRemoveOptional(name: String, age: Long, something: Option[String]): Future[Person] = {
    Future(Person(name, age, something.getOrElse("defu")))
  }

  override def createPersonRemoveRequired(name: String, age: Long, something: String): Future[Person] = {
    Future(Person(name, age, something))
  }

  override def createPersonUserType(name: String, age: Long, userType: UserType): Future[Person] = {
    println(userType)
    Future(Person(name, age, "s"))
  }
}

class OutputImlv1 extends Output.MethodPerEndpoint {
  override def addOptional(): Future[Something] = Future(Something("a"))

  override def addRequired(): Future[Something2] = Future(Something2("a", "b"))

  override def addNormal(): Future[Something3] = Future(Something3("a", "b"))

  override def removeNormal(): Future[Something4] = Future(Something4("a"))

  override def removeRequired(): Future[Something5] = Future(Something5("a"))

  override def removeOptional(): Future[Something6] = Future(Something6("a"))

  override def change(): Future[Something7] = Future(Something7("a", "b"))

  override def normalToOptional(): Future[Something8] = Future(Something8("a", "b"))

  override def normalToRequired(): Future[Something9] = Future(Something9("a", "b"))

  override def requiredToOptional(): Future[Something10] = Future(Something10("a", "b"))

  override def requiredToNormal(): Future[Something11] = Future(Something11("a", "b"))

  override def optionalToNormal(): Future[Something12] = Future(Something12("a"))

  override def optionalToRequired(): Future[Something13] = Future(Something13("a"))

  override def enumChanged(): Future[Color2] = Future(Color2.Yellow)

  override def enumRemoved(): Future[Color2] = Future(Color2.Magenta)

  override def unionChanged(): Future[Meh] = Future(Meh.Hello("hello"))

  override def unionRemoved(): Future[Meh] = Future(Meh.Woo("woo"))
}
