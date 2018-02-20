package com.barnesicle.user.dao

import com.barnesicle.user.cassandra.CassandraConfiguration
import com.barnesicle.user.entity.User
import com.datastax.driver.core.PreparedStatement
import com.datastax.driver.core.Row
import com.datastax.driver.core.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserDao @Autowired constructor(private val session: Session, cassandraConfiguration: CassandraConfiguration) {

    private var selectAllUsers: PreparedStatement? = null
    private var selectUserByEmail: PreparedStatement? = null
    private var insertUser: PreparedStatement? = null

    init {
        selectAllUsers = session.prepare("SELECT * FROM users_by_username")
        selectAllUsers?.consistencyLevel = cassandraConfiguration.readConsistencyLevel

        selectUserByEmail = session.prepare("SELECT * FROM users_by_username WHERE username = ?")
        selectUserByEmail?.consistencyLevel = cassandraConfiguration.readConsistencyLevel

        insertUser = session.prepare("INSERT INTO users_by_username (username, email, password, verified, created_timestamp, first_name, last_name) VALUES (?, ?, ?, ?, ?, ?, ?);")
        insertUser?.consistencyLevel = cassandraConfiguration.writeConsistencyLevel
    }

    private fun fillUser(row: Row) : User {
        val firstName = row.getString("first_name")
        val lastName = row.getString("last_name")
        val email = row.getString("email")
        val username = row.getString("username")
        val created = row.getTimestamp("created_timestamp")
        return User(username, firstName, lastName, email, created)
    }

    fun findUsers(): List<User> {

        val execute = session.execute(selectAllUsers?.bind())
        val rows = execute.all()
        val users : MutableList<User> = ArrayList()

        rows.mapTo(users) { fillUser(it) }

        return users
    }

    fun findUserByUsername(id: String): User {

        val execute = session.execute(selectUserByEmail?.bind(
                id))

        val rows = execute.all()

        if (rows.size > 0) {
            val row = rows[0]
            return fillUser(row)
        }

        return User("","","","");
    }

    fun insert(user: User) {

        session.execute(insertUser?.bind(
                user.username,
                user.email,
                "password123",
                true,
                Date(),
                user.firstName,
                user.lastName))

    }

}