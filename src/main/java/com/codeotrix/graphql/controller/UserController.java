package com.codeotrix.graphql.controller;

import java.util.List;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.codeotrix.graphql.entity.User;
import com.codeotrix.graphql.model.UserRequest;
import com.codeotrix.graphql.repository.UserRepository;
import com.codeotrix.graphql.service.UserService;

import graphql.ExecutionInput;
import graphql.GraphQL;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private GraphQL graphql;

	@Autowired
	private UserRepository userRepository;

	@PostMapping(value = "/query")
	public Map<String, Object> execute(@RequestBody UserRequest userRequest) {
		return graphql
				.execute(ExecutionInput.newExecutionInput().query(userRequest.getQuery())
						.operationName(userRequest.getOperationName()).variables(userRequest.getVariables()).build())
				.toSpecification();
	}

	// Rest API

	@GetMapping
	public User getUser(@RequestParam(name = "id") Integer id) {
		return userRepository.findById(id).get();
	}

	@GetMapping("/all")
	public List<User> getUserList() {
		return userRepository.findAll();
	}

}
