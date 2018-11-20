package v2

import com.example.thrift.generated.Service2.{ GetComplicated, GetString }
import com.example.thrift.generated.{ BinaryService, Service1, Service2 }
import com.twitter.finagle.{ Service, SimpleFilter, Thrift }
import com.twitter.finagle.stats.JavaLoggerStatsReceiver
import com.twitter.finagle.thrift.ThriftClientRequest
import com.twitter.util.Future
import impls.{ Service1Impl, Service2Impl }

object Creators {

  def createService1(clientAddr: String, port: Int) = {
    val client = service2Client(clientAddr)

    Thrift.Server()
      .withLabel("service2")
      .serveIface(s":$port", new Service1Impl(client))
  }

  def createService2(port: Int) = {
    Thrift.Server()
      .withLabel("service2")
      .serveIface(s":$port", new Service2Impl())
  }

  def service2Client(clientAddr: String) = {
    Thrift.client
      .withLabel("client2")
      .build[Service2.MethodPerEndpoint](clientAddr)
  }

  def service1Client(clientAddr: String): Service1.MethodPerEndpoint = {
    Thrift.client
      .withLabel("client1")
      .filtered(new CachingFilter)
      .build[Service1.MethodPerEndpoint](clientAddr)
  }

  def pureClient(clientAddr: String) = {
    Thrift.client
      .withLabel("pure")
      .servicePerEndpoint[Service2.ServicePerEndpoint](clientAddr, "pure")
  }

  val pc: Service2.ServicePerEndpoint = pureClient("heheh")
  val f: Service[GetString.Args, String] = new GetStringFilter().andThen(pc.getString)
  val c: Service[GetComplicated.Args, String] = new GetComplicatedFilter().andThen(pc.getComplicated)

  val b: Service2[Future] = Thrift.client.build("name")
  val res: Future[String] = b.getString("hehe")
}

class CachingFilter() extends SimpleFilter[ThriftClientRequest, Array[Byte]] {
  override def apply(request: ThriftClientRequest, service: Service[ThriftClientRequest, Array[Byte]]): Future[Array[Byte]] = {
    println(request)
    service(request)
  }
}

class GetStringFilter() extends SimpleFilter[GetString.Args, GetString.SuccessType] {
  override def apply(request: GetString.Args, service: Service[GetString.Args, GetString.SuccessType]): Future[GetString.SuccessType] = {
    println(request.input)
    service(request)
  }
}

class GetComplicatedFilter() extends SimpleFilter[GetComplicated.Args, GetComplicated.SuccessType] {
  override def apply(request: GetComplicated.Args, service: Service[GetComplicated.Args, String]): Future[String] = {
    println(request.boolean)
    println(request.number)
    println(request.input)
    service(request)
  }
}
