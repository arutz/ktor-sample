package org.slashdev.services

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import com.mongodb.client.MongoCollection
import com.mongodb.client.MongoDatabase
import org.bson.codecs.configuration.CodecRegistries
import org.bson.codecs.pojo.PojoCodecProvider
import org.koin.core.annotation.Single
import org.slashdev.model.CommentEntity
import org.slashdev.util.DurationCodec
import org.slashdev.util.TimezoneCodec
import org.slashdev.util.UUIDCodec

class MongoConfig(
    var username: String = "root",
    var password: String = "root",
    var database: String = "homepage",
    var host: String = "localhost",
    var port: Int = 27017,
)

/**
 * This constant is used for configuring a default mapping service in the context of
 * [org.litote.kmongo].* utility functions.
 */
private const val SYSTEM_PROP_MAPPING_SERVICE_DEFAULT = "org.litote.mongo.mapping.service"

@Single
class MongoProvider {

  private val mongoConfig = MongoConfig()

  private fun mongoClient(): MongoClient = run {
    val connectionString =
        ConnectionString(
            "mongodb://${mongoConfig.username}:${mongoConfig.password}@${mongoConfig.host}:${mongoConfig.port}")
    val clientSettings =
        MongoClientSettings.builder()
            .applyConnectionString(connectionString)
            .codecRegistry(
                CodecRegistries.fromRegistries(
                    CodecRegistries.fromCodecs(TimezoneCodec(), DurationCodec(), UUIDCodec()),
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(
                        PojoCodecProvider.builder().automatic(true).build())))
            .build()
    val client = MongoClients.create(clientSettings)
    client
  }

  fun mongoDefaultDatabase(): MongoDatabase = mongoClient().getDatabase(mongoConfig.database)

  fun commentCollection(): MongoCollection<CommentEntity> =
      mongoDefaultDatabase().getCollection("comments", CommentEntity::class.java)
}
