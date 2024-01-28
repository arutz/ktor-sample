package org.slashdev.util

import org.bson.BsonReader
import org.bson.BsonWriter
import org.bson.codecs.Codec
import org.bson.codecs.DecoderContext
import org.bson.codecs.EncoderContext
import java.time.Duration
import java.time.ZoneId
import java.util.*

class UUIDCodec : Codec<UUID> {
  override fun encode(writer: BsonWriter, value: UUID, encoderContext: EncoderContext) =
      writer.writeString(value.toString())

  override fun getEncoderClass(): Class<UUID> = UUID::class.java

  override fun decode(reader: BsonReader, decoderContext: DecoderContext): UUID =
      UUID.fromString(reader.readString())
}

data class Timezone(val zoneId: ZoneId)

class DurationCodec : Codec<Duration> {
  override fun encode(writer: BsonWriter, value: Duration, encoderContext: EncoderContext) =
      writer.writeInt64(value.seconds)

  override fun decode(reader: BsonReader, context: DecoderContext) = run {
    try {
      Duration.ofSeconds(reader.readInt64())
    } catch (e: Throwable) {
      Duration.ofSeconds(reader.readInt32().toLong())
    }
  }

  override fun getEncoderClass() = Duration::class.java
}

class TimezoneCodec : Codec<Timezone> {
  override fun getEncoderClass(): Class<Timezone> {
    return Timezone::class.java
  }

  override fun encode(writer: BsonWriter, value: Timezone, encoderContext: EncoderContext) {
    writer.writeString(value.zoneId.id)
  }

  override fun decode(reader: BsonReader, decoderContext: DecoderContext): Timezone {
    return Timezone(ZoneId.of(reader.readString()))
  }
}
