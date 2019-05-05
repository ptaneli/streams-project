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

package org.scalabridge.streams

import org.scalacheck.Gen
import org.scalatest.check.ScalaCheckDrivenPropertyChecks
import org.scalatest.{ FreeSpec, Matchers }

// Some examples of tests in Scala. You can run them in `sbt` calling `sbt test` or directly in IntelliJ using right-click
class SampleTest extends FreeSpec with Matchers with ScalaCheckDrivenPropertyChecks {

  "Some sample tests" - {
    "a normal test" in {
      1 should be(1)
    }
    "another normal test" in {
      Some(2) shouldBe Some(2)
    }
    "a property based test" in {
      // This is more advanced stuff but added in case we can talk about it
      val onlyPositiveIntegers = Gen.posNum[Int]
      forAll(onlyPositiveIntegers) { i ⇒
        i > 0
      }
    }
  }

}
