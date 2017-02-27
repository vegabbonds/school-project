package com.nts.intern.school.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

@Configuration
public class MybatisConfiguration {	
	
	@Bean
	public DataSourceTransactionManager mybatisTransaction(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}	
}