package com.barnesicle.user.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class ServiceFactory @Autowired constructor(val accountService: AccountService)