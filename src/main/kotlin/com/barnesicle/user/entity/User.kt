package com.barnesicle.user.entity

import java.util.*
import kotlin.properties.Delegates

data class User constructor(val username: String,
                            val firstName: String,
                            val lastName: String,
                            val email: String,
                            val password: String){

    //var created : Date by Delegates.notNull()

    constructor(username: String,
                firstName: String,
                lastName: String,
                email: String,
                created: Date,
                password: String) :
        this(username, firstName, lastName, email, password) {
        //this.created = created
    }

    fun copy(username: String,
             firstName: String,
             lastName: String,
             email: String,
             created: Date,
             password: String) = User(username, firstName, lastName, email, created, password)

}