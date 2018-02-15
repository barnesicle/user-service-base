package com.barnesicle.user.rest

import com.barnesicle.user.entity.User
import com.barnesicle.user.service.ServiceFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersRestController @Autowired constructor(val serviceFactory: ServiceFactory) {

    @GetMapping("/users")
    fun getUsers() : List<User> {
        return serviceFactory.userService.getUsers()
    }

    @GetMapping("/users/{email}")
    fun getUserByEmail(@PathVariable(value = "email") email: String) : User? {
        return serviceFactory.userService.getUserByEmail(email)
    }

}
