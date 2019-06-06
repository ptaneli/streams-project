/*
 * Copyright 2019 ScalaBridge
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.scalabridge.streams.server

import cats.data.Kleisli
import cats.implicits._
import cats.effect._
import fs2.Stream
import org.http4s._
import org.http4s.dsl.io._
import org.http4s.server.blaze._
import org.http4s.implicits._

import scala.concurrent.duration._

// Starts an http server on port 8080. You can access it at http://localhost:8080/hello/you
object HttpServer extends IOApp {

  val seconds: Stream[IO, FiniteDuration] = Stream.awakeEvery[IO](1.second)

  val baseService: Kleisli[IO, Request[IO], Response[IO]] = HttpRoutes
    .of[IO] {
      case GET -> Root / "hello" / name =>
        Ok(s"Hello, $name.")

      case GET -> Root / "seconds" =>
        Ok(seconds.map(_.toString + "  "))

      case GET -> Root / "myStream" =>
        // you want to use your stream in here to see the response returning on the server
        Ok("Your stream in here")

      case GET -> Root / "test" =>
        Ok("My stream testing")
    }
    .orNotFound

  def run(args: List[String]): IO[ExitCode] =
    BlazeServerBuilder[IO]
      .bindHttp(8080, "localhost")
      .withHttpApp(baseService)
      .serve
      .compile
      .drain
      .as(ExitCode.Success)
}
