package org.slashdev.model

import org.bson.codecs.pojo.annotations.BsonId
import org.bson.types.ObjectId
import org.slashdev.util.DocumentId
import java.time.Instant
import java.util.*

data class CommentEntity(
    @BsonId @DocumentId var id: ObjectId,
    var externalId: UUID,
    var content: String,
    var createdAt: Instant,
    var userId: UUID,
)
