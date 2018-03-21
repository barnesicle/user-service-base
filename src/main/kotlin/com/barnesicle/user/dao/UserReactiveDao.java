package com.barnesicle.user.dao;

import com.barnesicle.user.entity.User;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserReactiveDao extends ReactiveCassandraRepository<User, String> {
    @Query("SELECT * FROM users")
    Flux<User> findUsers();
    Mono<User> findUserByUsername(final String username);
}