package com.barnesicle.user.entity

import org.springframework.data.cassandra.core.mapping.Column
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table
import java.util.*

@Table("users_by_username")
data class Account constructor(@PrimaryKey val username: String,
                               @Column("first_name") val firstName: String,
                               @Column("last_name") val lastName: String,
                               @Column("email") val email: String,
                               @Column("password") val password: String,
                               @Column("roles") val roles: Set<String>,
                               @Column("created_timestamp") val created: Date,
                               @Column("verified") val verified: Boolean = false,
                               @Column("enabled") val enabled: Boolean = true,
                               @Column("failed_login_attempts") val failedLoginAttempts: Int = 0){

    constructor(firstName: String, lastName: String, email: String, password: String,
                roles: Set<String>, created: Date, verified: Boolean = true, enabled: Boolean = true, failedLoginAttempts: Int = 0) :
            this(email, firstName, lastName, email, password, roles, created, verified, enabled, failedLoginAttempts)
}