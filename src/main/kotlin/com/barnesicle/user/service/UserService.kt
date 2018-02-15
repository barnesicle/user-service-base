package com.barnesicle.user.service

import com.barnesicle.user.entity.User
import org.springframework.stereotype.Service

@Service
class UserService {

    private var allUsers: List<User> = listOf(
            User("Luke", "Barnes", "lbarnes@gmail.com"),
            User("Alison", "Barnes", "abarnes@gmail.com"),
            User("Hugh", "Jackman", "hjackman@gmail.com"),
            User("Kylie", "Minogue", "kminogue@gmail.com"),
            User("Nicole", "Kidman", "nkidman@gmail.com"),
            User("Heath", "Ledger", "hledger@gmail.com"),
            User("Eric", "Banner", "ebanner@gmail.com"),
            User("Keith", "Urban", "kurban@gmail.com"),
            User("Chris", "Hemsworth", "chemsworth@gmail.com"))

    fun getUsers() : List<User> {
        return allUsers
    }

    fun getUserByEmail(email: String) : User? {
        return allUsers.find { user -> user.email == email}
    }

}