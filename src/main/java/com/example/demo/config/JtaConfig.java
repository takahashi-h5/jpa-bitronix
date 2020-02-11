package com.example.demo.config;

import javax.transaction.TransactionManager;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

import bitronix.tm.BitronixTransactionManager;
import bitronix.tm.TransactionManagerServices;

@Configuration
@EnableTransactionManagement
public class JtaConfig {

	@Bean(name = "transactionManager")
	public PlatformTransactionManager transactionManager() throws Throwable {
		TransactionManager bitronixTransactionManager = TransactionManagerServices.getTransactionManager();
		bitronixTransactionManager.setTransactionTimeout(10000);
		BJtaPlatform.transaction = (BitronixTransactionManager) bitronixTransactionManager;
		BJtaPlatform.transactionManager = bitronixTransactionManager;
		return new JtaTransactionManager(bitronixTransactionManager);
	}
}
