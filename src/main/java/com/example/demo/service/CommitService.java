package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.primary.Empuser;
import com.example.demo.repository.primary.PrimaryRepository;
import com.example.demo.repository.secondary.SecondaryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommitService {
	private final PrimaryRepository repository;
	private final SecondaryRepository repository2;

	@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
	public Empuser getUser() {
		repository2.updateBySample();
		Empuser user = repository.findBySample();
		repository.updateBySample();
		return user;
	}
}
