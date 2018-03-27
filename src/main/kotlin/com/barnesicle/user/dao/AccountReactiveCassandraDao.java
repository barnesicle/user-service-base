package com.barnesicle.user.dao;

import com.barnesicle.user.entity.Account;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.cassandra.repository.ReactiveCassandraRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AccountReactiveCassandraDao extends ReactiveCassandraRepository<Account, String> {
    @Query("SELECT * FROM users_by_username")
    Flux<Account> findUsers();

    /*@Query("SELECT * FROM users_by_username where username = 0?")
    Mono<Account> findUserByUsername(final String username);*/
}