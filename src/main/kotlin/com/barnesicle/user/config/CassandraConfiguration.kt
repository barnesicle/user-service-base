package com.barnesicle.user.config;

import com.datastax.driver.core.Cluster
import com.datastax.driver.core.ConsistencyLevel
import com.datastax.driver.core.ProtocolVersion
import com.datastax.driver.core.Session
import com.datastax.driver.core.policies.DCAwareRoundRobinPolicy
import com.datastax.driver.core.policies.TokenAwarePolicy
import info.archinnov.achilles.embedded.CassandraEmbeddedServerBuilder
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CassandraConfiguration constructor(@Value("\${db.url}") val contactPoints: String,
                                         @Value("\${db.database}") val keyspaceName: String,
                                         @Value("\${db.port}") val port: Int,
                                         @Value("\${db.username}") val username: String,
                                         @Value("\${db.password}") val password: String,
                                         @Value("\${db.consistency.level.write:LOCAL_QUORUM}") val writeConsistencyLevel: ConsistencyLevel,
                                         @Value("\${db.consistency.level.read:LOCAL_ONE}") val readConsistencyLevel: ConsistencyLevel,
                                         @Value("\${db.data-center}") val dataCenter: String) {

    @Bean
    fun testCluster(): Cluster {

       /* logger.info("Contact Points: " + contactPoints)
        logger.info("Keyspace Name: " + keyspaceName)
        logger.info("Username: " + username)
        logger.info("Write Consistency Level: " + writeConsistencyLevel)
        logger.info("Read Consistency Level: " + readConsistencyLevel)
        logger.info("Data center: " + dataCenter)*/

        val dcAwareBuilder = DCAwareRoundRobinPolicy.builder()
        if (StringUtils.isNotBlank(dataCenter)) {
            dcAwareBuilder.withLocalDc(dataCenter)
        }

        // Will come from config as comma separated list
        val contactPoints = this.contactPoints.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        return Cluster.builder().addContactPoints(*contactPoints).
                withPort(port).
                withCredentials(username, password).
                withProtocolVersion(ProtocolVersion.V4).
                withLoadBalancingPolicy(TokenAwarePolicy(dcAwareBuilder.build())).build()

    }

    @Bean
    @Throws(Exception::class)
    fun session(): Session {
        return testCluster().connect(keyspaceName)
    }

    /*@Bean
    fun testCluster(): Cluster {
        return CassandraEmbeddedServerBuilder.builder()
                .withKeyspaceName("users")
                .cleanDataFilesAtStartup(true)
                .withScript("create_script.cql")
                .withCQLPort(9042)
                .withThriftPort(9160)
                .withStoragePort(7990)
                .withStorageSSLPort(7999).buildNativeCluster()

        *//*val dcAwareBuilder = DCAwareRoundRobinPolicy.builder()
        if (StringUtils.isNotBlank(dataCenter)) {
            dcAwareBuilder.withLocalDc(dataCenter)
        }

        // Will come from config as comma separated list
        val contactPoints = this.contactPoints.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        return Cluster.builder().addContactPoints(*contactPoints).
                withPort(port).
                withCredentials(username, password).
                withProtocolVersion(ProtocolVersion.V4).
                withLoadBalancingPolicy(TokenAwarePolicy(dcAwareBuilder.build())).build()*//*
    }*/

}

