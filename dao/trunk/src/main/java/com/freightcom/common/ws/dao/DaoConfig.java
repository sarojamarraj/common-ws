package com.freightcom.common.ws.dao;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.freightcom.common.ws.dao" })
@EnableJpaRepositories(basePackages = "com.freightcom.common.ws.dao.user")
public class DaoConfig {
}