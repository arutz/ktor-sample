package org.slashdev.services

import org.koin.core.annotation.Single
import org.slashdev.model.User

@Single
class UserService(private val userRepository: UserRepository) {

  fun findUser(userName: String) = userRepository.findUser(userName)

  fun addUser(user: User) = userRepository.addUsers(listOf(user))

  fun addUsers(users: List<User>) = userRepository.addUsers(users)

  fun getDefaultUser(): User =
      userRepository.findUser(DEFAULT_USER.name) ?: error("Can't find default user")
}

val DEFAULT_USER = User(name = "rutza")
