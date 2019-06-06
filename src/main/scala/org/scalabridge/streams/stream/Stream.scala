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

package org.scalabridge.streams.stream

// Your Stream trait, to be implemented
trait MyStream[A] {
  def map[B](f: A => B): MyStream[B] = this match {
    case Empty() => Empty[B]()
    case Cons(h, t) => Cons(f(h), t.map(f))
  }

  def ++(other: MyStream[A]): MyStream[A] = this match {
    case Empty() => other
    case Cons(h,t) => Cons(h, t.++(other))
  }

  def flatMap[B](f: A => MyStream[B]): MyStream[B] = this match {
    case Empty() => Empty[B]()
    case Cons(h,t) => f(h) ++ t.flatMap(f)

  }

  def zip(other: MyStream[A]): MyStream[(A,A)] = this match {
    case Empty() => Empty[(A,A)]()
    case Cons(h,t) => other match {
      case Empty() => Empty[(A,A)]()
      case Cons(other_h,other_t) => Cons((h, other_h), t.zip(other_t))
    }
  }

  def last: Option[A] = this match {
    case Empty() => Nil()
    case Cons(h, Empty()) => Some(h)
    case Cons(_, t) => t.last
  }

  def interleave(other: MyStream[A]): MyStream[A] = this match {
    case Empty() => other
    case Cons(h,t) => other match {
      case Empty() => this
      case Cons(other_h, other_t) => ???
    }
  }
}
case class Empty[A]() extends MyStream[A]


case class Cons[A](h: A, t: MyStream[A]) extends MyStream[A]

trait Option[A]
case class Nil[A]() extends Option[A]
case class Some[A](a: A) extends Option[A]


object MyStream {

  def fromIterator[A](source: Iterator[A]): MyStream[A] = {
    if(source.hasNext) Cons(source.next(), fromIterator(source)) else Empty[A]
  }

  //  def pure[A]: Stream[A] = Cons(???, Stream[A])

  def const[A](a: A): MyStream[A] = Cons(a, Empty[A])

  def empty[A] : MyStream[A] = Empty[A]

}

