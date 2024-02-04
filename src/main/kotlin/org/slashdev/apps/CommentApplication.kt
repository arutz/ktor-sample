package org.slashdev.apps

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slashdev.services.CollectionProvider

open class CommentApplication : KoinComponent {

  private val collectionProvider: CollectionProvider by inject()

  fun findComments() = collectionProvider.commentCollection().find().toList()
}
