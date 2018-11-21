import com.example.thrift.v2.generated.{ Color, Output, Service, UserType }
import com.twitter.finagle.Thrift
import com.twitter.util.{ Await, Try }
import server.{ OutputImlv1, ServerImplV1, ServerImplV2 }

object Test extends App {
  val port = 14455
  val port2 = 14456

  val server = Thrift.server.serveIface(s":$port", new ServerImplV1)
  val server2 = Thrift.server.serveIface(s":$port2", new OutputImlv1)

  val client = Thrift.client.build[Service.MethodPerEndpoint](s":$port")
  val client2 = Thrift.client.build[Output.MethodPerEndpoint](s":$port2")

  // Adding input type
  //  Person createPerson(1: string name, 2: i64 age)
  val a = Try(Await.result(client.createPersonNormal("name", 31, "something")))

  //Add normal tyoe
  //  Person createPersonNormal(1: string name, 2: i64 age)
  val b = Try(Await.result(client.createPersonNormal("name", 31, "something")))

  //Add required type
  //  Person createPersonReq(1: string name, 2: i64 age)
  val c = Try(Await.result(client.createPersonReq("name", 31, "something")))

  //Add required type
  //  Person createPersonOptional(1: string name, 2: i64 age)
  val d = Try(Await.result(client.createPersonOptional("name", 31, "something")))

  //Change type
  //  Person createPersonChangeType(1: string name, 2: i64 age)
  val e = Try(Await.result(client.createPersonChangeType("name", "31")))
  //Required -> "normal"
  //  Person createPersonReqToNormal(1: string name, 2: i64 age)
  val f = Try(Await.result(client.createPersonReqToNormal("name", 31)))

  //Required -> optianal
  //  Person createPersonReqToOptional(1: string name, 2: optional i64 age)
  val g = Try(Await.result(client.createPersonReqToOptional("name", 31)))

  //Normal -> Required
  //  Person createPersonNormalToRequired(1: string name, 2: required i64 age)
  val h = Try(Await.result(client.createPersonNormalToRequired("name", 31)))

  //Normal -> Optional
  //  Person createPersonNormalToOptional(1: string name, 2: optional i64 age)
  val i = Try(Await.result(client.createPersonNormalToOptional("name", 31)))

  //Optional -> Normal
  //  Person createPersonOptionalNormal(1: string name, 2: i64 age)
  val j = Try(Await.result(client.createPersonOptionalNormal("name", Option(31L))))
  val q = Try(Await.result(client.createPersonOptionalNormal("name", None)))

  //Optional -> Required
  //  Person createPersonOptionalRequired(1: string name, 2: required i64 age)
  val k = Try(Await.result(client.createPersonOptionalRequired("name", Option(31L))))
  val l = Try(Await.result(client.createPersonOptionalRequired("name", None)))

  // With Enum
  //  Person createPersonHairColor(1: string name, 2: i64 age, 3: Color color)
  val m = Try(Await.result(client.createPersonHairColor("name", 31, Color.Green)))
  val n = Try(Await.result(client.createPersonHairColor("name", 31, Color.Magenta)))

  //Typedef -> Normal
  //  Person createPersonTypedef(1: string name, 2: i64 age)
  val o = Try(Await.result(client.createPersonTypedef("name", 31)))

  //  Person createPersonTypedefNormal(1: Name name, 2: i64 age)
  val p = Try(Await.result(client.createPersonTypedefNormal("name", 31)))

  println(p.get()._3)

  //remove required type
  //  Person createPersonRemoveRequired(1: string name, 2: i64 age, 3: required string something)
  val r = Try(Await.result(client.createPersonRemoveRequired("name", 31)))

  //remove normal type
  //  Person createPersonRemoveNormal(1: string name, 2: i64 age, 3: string something)
  val s = Try(Await.result(client.createPersonRemoveNormal("name", 31)))

  //remove optional type
  //  Person createPersonRemoveOptional(1: string name, 2: i64 age, 3: optional string something)
  val t = Try(Await.result(client.createPersonRemoveOptional("name", 31)))

  type Name = String
  case class Zomg(name: Name)
  val meh = Zomg("something")
  val u = Try(Await.result(client.createPersonNormal(meh.name, 31, "zomg")))

  // Union
  //  Person createPersonUserType(1: string name, 2: i64 age, 3: UserType userType)
  val v = Try(Await.result(client.createPersonUserType(meh.name, 31, UserType.Agent(false))))
  val w = Try(Await.result(client.createPersonUserType(meh.name, 31, UserType.Bleh(1L))))

  val aa = Try(Await.result(client2.addOptional()))
  val ab = Try(Await.result(client2.addRequired()))
  val ac = Try(Await.result(client2.addNormal()))
  val ad = Try(Await.result(client2.removeNormal()))
  val ae = Try(Await.result(client2.removeRequired()))
  val af = Try(Await.result(client2.removeOptional()))
  val ag = Try(Await.result(client2.change()))
  val ah = Try(Await.result(client2.normalToOptional()))
  val ai = Try(Await.result(client2.normalToRequired()))
  val aj = Try(Await.result(client2.requiredToOptional()))
  val ak = Try(Await.result(client2.requiredToNormal()))
  val al = Try(Await.result(client2.optionalToNormal()))
  val am = Try(Await.result(client2.optionalToRequired()))

  val ba = Try(Await.result(client2.enumChanged()))
  val bb = Try(Await.result(client2.enumRemoved()))
  val bc = Try(Await.result(client2.unionChanged()))
  val bd = Try(Await.result(client2.unionRemoved()))
  val be = Try(Await.result(client2.unionRenamed()))

  val ca = Try(Await.result(client.createPersonUserType("name", 31, UserType.Admin("admin"))))

  val res = List(a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w, aa, ab, ac, ad, ae, af, ag, ah, ai, aj, ak, al, am, ba, bb, bc, bd, be, ca)
  val alphabet = List("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "aa", "ab", "ac", "ad", "ae", "af", "ag", "ah", "ai", "aj", "ak", "al", "am", "ba", "bb", "bc", "bd", "be", "ca")

  res.zip(alphabet).foreach {
    case (res, char) =>
      println(s"$char -> ${res}")
  }

}
