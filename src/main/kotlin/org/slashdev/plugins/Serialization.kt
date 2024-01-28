package org.slashdev.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.litote.kmongo.serialization.SerializationClassMappingTypeService

/**
 * This constant is used for configuring a default mapping service in the context of
 * [org.litote.kmongo].* utility functions.
 */
private const val SYSTEM_PROP_MAPPING_SERVICE_DEFAULT = "org.litote.mongo.mapping.service"

fun Application.configureSerialization() {
  System.setProperty(
      SYSTEM_PROP_MAPPING_SERVICE_DEFAULT,
      SerializationClassMappingTypeService::class.qualifiedName!!)
  install(ContentNegotiation) { json() }
  routing { get("/json/kotlinx-serialization") { call.respond(mapOf("hello" to "world")) } }
}
