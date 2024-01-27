package org.slashdev.plugins

import io.ktor.resources.*
import io.ktor.server.application.*
import io.ktor.server.plugins.doublereceive.*
import io.ktor.server.request.*
import io.ktor.server.resources.*
import io.ktor.server.resources.Resources
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.Serializable

fun Application.configureRouting() {
  install(DoubleReceive)
  install(Resources)
  routing {
    get("/") {
      call.respondText("Hello World!")
    }
    post("/double-receive") {
      val first = call.receiveText()
      val theSame = call.receiveText()
      call.respondText(first + " " + theSame)
    }
    get<Articles> { article ->
      // Get all articles ...
      call.respond("List of articles sorted starting from ${article.sort}")
    }
  }
}

@Serializable
@Resource("/articles")
class Articles(val sort: String? = "new")
