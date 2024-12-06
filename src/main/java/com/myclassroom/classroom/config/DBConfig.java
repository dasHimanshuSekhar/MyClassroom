package com.myclassroom.classroom.config;

import org.postgresql.ds.PGSimpleDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DBConfig {
    Logger logger = LoggerFactory.getLogger(DBConfig.class);

    @Bean(name = "PgDbConnect")
    @Primary
    public PGSimpleDataSource connectDb(){
        System.out.println("==================== DB Connection Begins =================");
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setUrl("jdbc:postgresql://cliff-dolphin-3009.jxf.gcp-us-west2.cockroachlabs.cloud:26257/defaultdb?sslmode=verify-full");
        ds.setUser("himanshu");
        ds.setPassword("tWcQK6Pw56G2p1WvAxR3FQ");
        System.out.println("==================== DB Connected =================");
        return ds;
    }
}