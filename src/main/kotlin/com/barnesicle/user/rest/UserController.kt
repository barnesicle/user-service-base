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
class UsersRestController @Autowired constructor(val serviceFactory: ServiceFactory) {

    @GetMapping("/users")
    fun getUsers() : Flux<Account>? {
        return serviceFactory.userService.findUsers()
    }

    @GetMapping("/users/{email}")
    fun getUserByEmail(@PathVariable(value = "email") email: String) : Mono<Account>? {
        return serviceFactory.userService.findUserByUsername(email)
    }

    @PostMapping("/users/signup")
    fun insert(@Valid @RequestBody accountSignup: AccountSignup): ResponseEntity<*> {

        //logger.debug("POST")
        //logger.debug(accountSignup.toString())

        val account = Account(accountSignup.email,
                accountSignup.firstName,
                accountSignup.lastName,
                accountSignup.email,
                accountSignup.password,
                setOf("USER"),
                Date())

        serviceFactory.userService.insert(account)

        return ResponseEntity<Account>(account, HttpStatus.CREATED)
    }

}
