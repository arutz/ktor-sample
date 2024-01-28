package org.slashdev.apps

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.slashdev.services.UserService

/** UserListApplication - Application Class use UserRepository */
class UserApplication : KoinComponent {

  private val userService: UserService by inject()

  // display our data
  fun sayHello() {
    val user = userService.getDefaultUser()
    val message = "Hello '$user'!"
    println(message)
  }
}
