package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import bitronix.tm.resource.jdbc.PoolingDataSource;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
    entityManagerFactoryRef = "db1EntityManagerFactory",
    transactionManagerRef = "transactionManager",
    basePackages = "com.example.demo.repository.primary"
)
public class PrimaryDbConfig {

	@Autowired
	private MyDataSource dataSource;

	@Autowired
	private JpaVendorAdapter jpaVendorAdapter;

	@Primary
	@Bean(name = "db1DataSource")
	public DataSource dataSource() {
		PoolingDataSource xaDataSource = dataSource.getXAPrimaryDataSource();
		xaDataSource.setUniqueName("db1DataSource"); // ユニークな名前にする
		return xaDataSource;
	}

	@Primary
	@Bean(name = "db1EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			@Qualifier("db1DataSource") DataSource dataSource) {
		Map<String, Object> properties = new HashMap<String, Object>();
		properties.put("hibernate.transaction.jta.platform", BJtaPlatform.class.getName());
		properties.put("javax.persistence.transactionType", "JTA");
		properties.put("hibernate.jdbc.lob.non_contextual_creation", true); // postgresql用
		LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
		entityManager.setJtaDataSource(dataSource);
		entityManager.setJpaVendorAdapter(jpaVendorAdapter);
		entityManager.setPackagesToScan("com.example.demo.entity.primary");
		entityManager.setPersistenceUnitName("PrimaryDataBase");
		entityManager.setJpaPropertyMap(properties);
		return entityManager;
	}

}
