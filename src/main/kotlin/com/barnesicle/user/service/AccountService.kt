package com.barnesicle.user.service

import com.barnesicle.user.dao.AccountReactiveCassandraDao
import com.barnesicle.user.entity.Account
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AccountService @Autowired constructor(val accountDao: AccountReactiveCassandraDao,
                                            val passwordEncoder: PasswordEncoder) {



    fun findAccounts() : Flux<Account>? {
        return accountDao.findUsers()
    }

    fun findAccountByUsername(username: String) : Mono<Account>? {
        return accountDao.findById(username)
    }

    fun insert(account: Account): Mono<Account>? {
        val accountWithEncryptedPassword = account.copy(account.username,
                account.firstName,
                account.lastName,
                account.email,
                passwordEncoder.encode(account.password),
                account.roles,
                account.created
                )

        return accountDao.insert(accountWithEncryptedPassword)
    }

}