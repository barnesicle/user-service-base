package com.barnesicle.user

import com.barnesicle.user.entity.User
import com.barnesicle.user.service.UserService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class UserApplication {

    // Test Data
    private var allUsers: List<User> = listOf(
            User("lbarnes", "Luke", "Barnes", "lbarnes@gmail.com", "password123"),
            User("abarnes","Alison", "Barnes", "abarnes@gmail.com", "password123"),
            User("hjackman","Hugh", "Jackman", "hjackman@gmail.com", "password123"),
            User("kminogue","Kylie", "Minogue", "kminogue@gmail.com", "password123"),
            User("nkidman","Nicole", "Kidman", "nkidman@gmail.com", "password123"),
            User("hledger","Heath", "Ledger", "hledger@gmail.com", "password123"),
            User("ebanner","Eric", "Banner", "ebanner@gmail.com", "password123"),
            User("kurban","Keith", "Urban", "kurban@gmail.com", "password123"),
            User("chemsworth","Chris", "Hemsworth", "chemsworth@gmail.com", "password123"))

    @Bean
    fun init(repository: UserService) = CommandLineRunner {
        // TODO Only do if db is empty
        for (user in allUsers) {
            repository.insert(user)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
