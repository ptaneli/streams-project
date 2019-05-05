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

package org.scalabridge.streams.fs2

import cats.effect._
import fs2._

// This code creates an infinite fs2 stream that emits '1' forever and then adds '+3' to each value emitted
// To avoid hanging the application in an infinite stream, we just take the first 100 elements and print them
object SampleCode extends App {

  val infiniteStream = Stream.emit(1).repeat.covary[IO].map(_ + 3)

  // Change 100 by another value to modify how many values we want to process.
  val output = infiniteStream.take(100).compile.toVector.unsafeRunSync()

  // Please note printing may take a while if you used very large values for 'take' as the stream has to be processed first
  println(s"Result >> $output")

}
