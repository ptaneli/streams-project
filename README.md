# Streams template

This is a template project for the Streams project. This is an example of the standard structure you can expect to find in a Scala project.

It has been created using [sbt-fresh](https://github.com/sbt/sbt-fresh). Load it in your editor of choice. Some additional libraries have been added to the build. 

The guidelines for the project are in file `Streams-Notes` in the root folder. To be discussed in more detail in our sessions.

The `src` folder contains two packages:

- `stream` contains a placeholder for the Stream you are going to define
- `fs2` contains an example of a `fs2` stream as per the `fs2` documentation. 
- `server` contains some wiring for an HttpServer that will respond to `Get` requests (to be wired to your Stream later on)
 

The http server can be accessed at the following endpoints:

- http://localhost:8080/hello/your_name (you can replace `your_name` by your name or another string)
- http://localhost:8080/seconds this gets an infinite stream of data. In browsers that support it (most of them nowadays) the content in the page will be updated every second with a new value
- http://localhost:8080/myStream this is where you will wire your own stream 

## Contribution policy ##

Contributions via GitHub pull requests are gladly accepted from their original author. Along with
any pull requests, please state that the contribution is your original work and that you license
the work to the project under the project's open source license. Whether or not you state this
explicitly, by submitting any copyrighted material via pull request, email, or other means you
agree to license the material under the project's open source license and warrant that you have the
legal authority to do so.

## License ##

This code is open source software licensed under the
[Apache-2.0](http://www.apache.org/licenses/LICENSE-2.0) license.
