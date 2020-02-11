package com.example.demo.controller.primary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.primary.Empuser;
import com.example.demo.service.CommitService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PrimaryController {
  private final CommitService service;

  @RequestMapping(path = "/test", method = RequestMethod.POST)
  public Empuser json() {
	  
    return service.getUser();
  }
}

