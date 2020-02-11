package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import bitronix.tm.resource.jdbc.PoolingDataSource;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
	    entityManagerFactoryRef = "db2EntityManagerFactory",
	    transactionManagerRef = "transactionManager",
	    basePackages = "com.example.demo.repository.secondary"
)
public class SecondaryDbConfig {

	@Autowired
	private MyDataSource dataSource;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Bean(name = "db2DataSource")
	public DataSource dataSource() {
		PoolingDataSource xaDataSource = dataSource.getXASecondaryDataSource();
		xaDataSource.setUniqueName("db2DataSource"); // ユニークな名前にする
		return xaDataSource;
	}

	@Bean(name = "db2EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder,
			@Qualifier("db2DataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", BJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.jdbc.lob.non_contextual_creation", true); // postgresql用
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.example.demo.entity.secondary");
		entityManager.setPersistenceUnitName("SecondaryDataBase");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
