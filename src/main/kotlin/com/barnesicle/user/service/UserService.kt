package com.barnesicle.user.service

import com.barnesicle.user.dao.UserDao
import com.barnesicle.user.entity.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService @Autowired constructor(val userDao: UserDao) {

    fun getUsers() : List<User> {
        return userDao.findUsers()
    }

    fun getUserByEmail(email: String) : User? {
        return userDao.findUserByUsername(email);
    }

    fun insert(user: User) {
        userDao.insert(user)
    }

}