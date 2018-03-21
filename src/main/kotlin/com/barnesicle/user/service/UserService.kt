package com.barnesicle.user.service

import com.barnesicle.user.dao.UserReactiveDao
import com.barnesicle.user.entity.User
import org.mindrot.jbcrypt.BCrypt
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService @Autowired constructor(val userDao: UserReactiveDao) {

    var SALT : String = "salt123"; // TODO Get from properties

    fun getUsers() : Flux<User>? {
        return userDao.findUsers()
    }

    fun getUserByEmail(email: String) : Mono<User>? {
        return userDao.findUserByUsername(email)
    }

    fun insert(user: User) {
        val userWithEncryptedPassword = user.copy(user.username,
                user.firstName,
                user.lastName,
                user.email,
                //user.created,
                BCrypt.hashpw(user.password, BCrypt.gensalt()))

        userDao.insert(userWithEncryptedPassword)
    }

}