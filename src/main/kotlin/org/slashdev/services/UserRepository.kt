package org.slashdev.services

import org.koin.core.annotation.Single
import org.slashdev.model.User

interface UserRepository {
  fun findUser(name: String): User?

  fun addUsers(users: List<User>)
}

@Single
class UserRepositoryImpl() : UserRepository {

  private var usersStorage = ArrayList<User>(listOf(DEFAULT_USER))

  override fun findUser(name: String): User? {
    return usersStorage.firstOrNull { it.name == name }
  }

  override fun addUsers(users: List<User>) {
    usersStorage.addAll(users)
  }
}
