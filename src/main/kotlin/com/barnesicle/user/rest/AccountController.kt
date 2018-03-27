package com.barnesicle.user.rest

import com.barnesicle.user.entity.Account
import com.barnesicle.user.request.AccountSignup
import com.barnesicle.user.service.ServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import java.util.*


@RestController
class AccountsRestController @Autowired constructor(val serviceFactory: ServiceFactory) {

    @GetMapping("/account")
    fun getAccounts() : Flux<Account>? {
        return serviceFactory.accountService.findAccounts()
    }

    @GetMapping("/account/{username}")
    fun getAccountByEmail(@PathVariable(value = "email") username: String) : Mono<Account>? {
        return serviceFactory.accountService.findAccountByUsername(username)
    }

    @PostMapping("/account/signup")
    fun insert(@Valid @RequestBody accountSignup: AccountSignup): ResponseEntity<*> {

        //logger.debug("POST")
        //logger.debug(accountSignup.toString())

        val account = Account(
                accountSignup.firstName,
                accountSignup.lastName,
                accountSignup.email,
                accountSignup.password,
                setOf("USER"),
                Date())

        serviceFactory.accountService.insert(account)

        return ResponseEntity<Account>(account, HttpStatus.CREATED)
    }

}
