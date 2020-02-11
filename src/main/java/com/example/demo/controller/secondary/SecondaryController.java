package com.example.demo.controller.secondary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.secondary.EmpUser2;
import com.example.demo.repository.secondary.SecondaryRepository;

import lombok.RequiredArgsConstructor;
@RestController(value="test1")
@RequiredArgsConstructor
public class SecondaryController {
	
  private final SecondaryRepository repository;
  @RequestMapping(path = "/test2", method = RequestMethod.POST)
  public EmpUser2  json2() {
	  EmpUser2 user = repository.findBySample();
      return user;
  }
}

