package com.barnesicle.user.dao;

import com.barnesicle.user.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.ReactiveCassandraOperations;

public class UserReactiveDao {

    @Autowired
    private ReactiveCassandraOperations operations;

    public void insert(Account user) {

    }

}
