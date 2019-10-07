package com.jmmoreno.openshift.cicd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/api/v1/hello")
@Slf4j
@RequiredArgsConstructor
public class HelloController {

	private static final String ENV_PROPERTY_NAME = "HELLO_FROM_NAME";

	@Autowired
	private Environment env;

	@GetMapping
	public ResponseEntity<String> findAll() {
		if (env.containsProperty(ENV_PROPERTY_NAME)) {
			return ResponseEntity.ok("Hello from " + env.getProperty(ENV_PROPERTY_NAME));
		} else {
			return ResponseEntity.ok("Env variable " + ENV_PROPERTY_NAME + " not found");
		}
	}

}