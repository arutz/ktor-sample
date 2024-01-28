package org.slashdev.apps

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slashdev.services.MongoProvider

open class CommentApplication : KoinComponent {

  private val commentProvider: MongoProvider by inject()

  fun findComments() = commentProvider.commentCollection().find().toList()
}
