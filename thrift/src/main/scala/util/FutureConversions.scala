package util

import cats.effect.IO
import com.twitter.util.{ Future => TFuture, Promise => TPromise }

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ Future, Promise }
import scala.util.{ Failure, Success }

object FutureConversions {
  implicit def scalaFutureToTwitterFuture[A](future: Future[A]): TFuture[A] = {
    val promise = TPromise[A]()
    future.onComplete {
      case Success(value) => promise.setValue(value)
      case Failure(e: Throwable) => promise.setException(e)
    }

    promise
  }

  implicit def twitterFutureToScalaFuture[A](tf: TFuture[A]): Future[A] = {
    val promise = Promise[A]()

    tf.respond {
      case com.twitter.util.Throw(e) => promise.failure(e)
      case com.twitter.util.Return(value) => promise.success(value)
    }

    promise.future
  }

  implicit def twitterFutureToIO[A](tf: TFuture[A]): IO[A] = {
    IO.fromFuture(IO(twitterFutureToScalaFuture(tf)))
  }
}
