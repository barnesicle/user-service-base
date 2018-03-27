package com.barnesicle.user

import com.barnesicle.user.entity.Account
import com.barnesicle.user.service.AccountService
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.data.cassandra.repository.config.EnableReactiveCassandraRepositories
import java.util.*

@SpringBootApplication
@EnableReactiveCassandraRepositories
class UserApplication {

    // Test Data
    private final val created = Date()

    private var allAccounts: List<Account> = listOf(
            Account("Luke", "Barnes", "lbarnes@gmail.com", "password123", setOf("USER","ADMIN"), created),
            Account("Alison", "Barnes", "abarnes@gmail.com", "password123", setOf("USER"), created),
            Account("Hugh", "Jackman", "hjackman@gmail.com", "password123", setOf("USER"), created),
            Account("Kylie", "Minogue", "kminogue@gmail.com", "password123", setOf("USER"), created),
            Account("Nicole", "Kidman", "nkidman@gmail.com", "password123", setOf("USER"), created),
            Account("Heath", "Ledger", "hledger@gmail.com", "password123", setOf("USER"), created),
            Account("Eric", "Banner", "ebanner@gmail.com", "password123", setOf("USER"), created),
            Account("Keith", "Urban", "kurban@gmail.com", "password123", setOf("USER"), created),
            Account("Chris", "Hemsworth", "chemsworth@gmail.com", "password123", setOf("USER"), created))

    @Bean
    fun init(repository: AccountService) = CommandLineRunner {
        // TODO Only do if db is empty
        for (user in allAccounts) {
            repository.insert(user)?.block()
        }
    }

}

fun main(args: Array<String>) {
    runApplication<UserApplication>(*args)
}
