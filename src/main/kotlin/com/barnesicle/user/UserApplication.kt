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
            User("lbarnes", "Luke", "Barnes", "lbarnes@gmail.com"),
            User("abarnes","Alison", "Barnes", "abarnes@gmail.com"),
            User("hjackman","Hugh", "Jackman", "hjackman@gmail.com"),
            User("kminogue","Kylie", "Minogue", "kminogue@gmail.com"),
            User("nkidman","Nicole", "Kidman", "nkidman@gmail.com"),
            User("hledger","Heath", "Ledger", "hledger@gmail.com"),
            User("ebanner","Eric", "Banner", "ebanner@gmail.com"),
            User("kurban","Keith", "Urban", "kurban@gmail.com"),
            User("chemsworth","Chris", "Hemsworth", "chemsworth@gmail.com"))

    @Bean
    fun init(repository: UserService) = CommandLineRunner {
        for (user in allUsers) {
            repository.insert(user)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
