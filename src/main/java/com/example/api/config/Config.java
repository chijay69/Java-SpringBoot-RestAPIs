package com.example.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class Config {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
    @Bean(name = "datasource1")
    @ConfigurationProperties("spring.datasource.db1")
    @Primary
    public DataSource dataSource1() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "datasource2")
    @ConfigurationProperties("spring.datasource.db2")
    public DataSource dataSource2() {
        return DataSourceBuilder.create().build();
    }
    @Bean(name = "tm1")
    @Autowired
    @Primary
    public PlatformTransactionManager transactionManager1(@Qualifier("datasource1") DataSource dataSource1) {
        return new DataSourceTransactionManager(dataSource1);
    }

    @Bean(name = "tm2")
    @Autowired
    public PlatformTransactionManager transactionManager2(@Qualifier("datasource2") DataSource dataSource2) {
        return new DataSourceTransactionManager(dataSource2);
    }
}
