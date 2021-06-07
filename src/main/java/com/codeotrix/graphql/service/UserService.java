package com.codeotrix.graphql.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeotrix.graphql.entity.Contact;
import com.codeotrix.graphql.entity.User;
import com.codeotrix.graphql.repository.UserRepository;
import com.codeotrix.graphql.repository.ContactRepository;

import graphql.schema.DataFetcher;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ContactRepository contactRepository;

	public DataFetcher<User> getUser() {
		return environment -> userRepository.findById(environment.getArgument("id")).orElseThrow();
	}

	public DataFetcher<List<User>> getUsers() {
		return environment -> userRepository.findAll();
	}

	public DataFetcher<User> createUser() {
		return environment -> userRepository.save(
				new User(environment.getArgument("name"), environment.getArgument("score"), contactRepository.save(
						new Contact(environment.getArgument("mobile"), environment.getArgument("email")))));

	}

}
