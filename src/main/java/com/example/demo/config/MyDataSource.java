package com.example.demo.config;

import java.util.Properties;

import org.springframework.stereotype.Component;

import bitronix.tm.resource.jdbc.PoolingDataSource;

@Component
public class MyDataSource {
	public PoolingDataSource getXAPrimaryDataSource() {
		PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
		bitronixDataSourceBean.setMaxPoolSize(5);
		bitronixDataSourceBean.setAllowLocalTransactions(true); // postgresql用の設定で必要っぽい
		bitronixDataSourceBean.setClassName("org.postgresql.xa.PGXADataSource");
		Properties properties = new Properties();
		properties.put("user", "postgres");
		properties.put("password", ""); // パスワード設定してください
		properties.put("url", "jdbc:postgresql://localhost:5432/mydb1");
		bitronixDataSourceBean.setDriverProperties(properties);
		return bitronixDataSourceBean;
	}
	
	public PoolingDataSource getXASecondaryDataSource() {
		PoolingDataSource bitronixDataSourceBean = new PoolingDataSource();
		bitronixDataSourceBean.setMaxPoolSize(5);
		bitronixDataSourceBean.setAllowLocalTransactions(true); // postgresql用の設定で必要っぽい
		bitronixDataSourceBean.setClassName("org.postgresql.xa.PGXADataSource");
		Properties properties = new Properties();
		properties.put("user", "postgres");
		properties.put("password", ""); // パスワード設定してください
		properties.put("url", "jdbc:postgresql://localhost:5432/mydb2");
		bitronixDataSourceBean.setDriverProperties(properties);
		return bitronixDataSourceBean;
	}
}
