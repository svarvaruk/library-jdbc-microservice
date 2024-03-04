package com.library.jdbc;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

@Configuration
//@EnableTransactionManagement
public class ApplicationConfiguration {
	 
	@Autowired
	Environment environment;
	
	@Bean
	static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
	    return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean(name="postgreDataSource")
	DataSource dataSource() throws SQLException {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
//		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));
		return dataSource;
	}
	  
	@Bean(name="postgreJdbcTemplate")
	JdbcTemplate postgreJdbcTemplate(@Qualifier("postgreDataSource") DataSource dataSource){		
		return new JdbcTemplate(dataSource);
	}

	@Bean
	DataSourceTransactionManager transactionManager(@Qualifier("postgreDataSource") DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();	  
		transactionManager.setDataSource(dataSource);
		return transactionManager;
	}	
	/*
	@Bean
	TransactionTemplate transactionTemplate() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));		
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return new TransactionTemplate(transactionManager);
	}
	  */ 
}
