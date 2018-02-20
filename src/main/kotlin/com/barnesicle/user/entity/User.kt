package com.barnesicle.user.entity

import java.util.*
import kotlin.properties.Delegates

data class User constructor(val username: String,
                            val firstName: String,
                            val lastName: String,
                            val email: String){

    var created : Date by Delegates.notNull()

    constructor(username: String,
                firstName: String,
                lastName: String,
                email: String,
                created: Date) :
        this(username, firstName, lastName, email) {
        this.created = created
    }

}