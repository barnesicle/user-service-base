package com.barnesicle.user.service

import com.barnesicle.user.dao.UserReactiveCassandraDao
import com.barnesicle.user.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class UserService @Autowired constructor(val userDao: UserReactiveCassandraDao,
                                         val passwordEncoder: PasswordEncoder
) {



    fun findUsers() : Flux<Account>? {
        return userDao.findUsers()
    }

    fun findUserByUsername(username: String) : Mono<Account>? {
        return userDao.findById(username)
    }

    fun insert(account: Account): Mono<Account>? {
        val userWithEncryptedPassword = account.copy(account.username,
                account.firstName,
                account.lastName,
                account.email,
                passwordEncoder.encode(account.password),
                account.roles,
                account.created
                )

        return userDao.insert(userWithEncryptedPassword)
    }

}