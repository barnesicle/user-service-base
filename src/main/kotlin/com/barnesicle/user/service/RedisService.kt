package com.barnesicle.user.service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import io.lettuce.core.api.reactive.RedisStringReactiveCommands;

//@Component
class RedisService @Autowired constructor(val reactiveCommands: RedisStringReactiveCommands<String, String>)