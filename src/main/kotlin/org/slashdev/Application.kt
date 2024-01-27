package org.slashdev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module
import org.slashdev.apps.UserApplication
import org.slashdev.plugins.*

fun main() {
  startKoin { modules(AppModule().module) }
  embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module)
      .start(wait = true)
}

fun Application.module() {
  UserApplication().sayHello()
  configureHTTP()
  configureMonitoring()
  configureSerialization()
  configureDatabases()
  configureRouting()
}
