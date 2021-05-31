package com.codeotrix.graphql.service;

import java.util.ArrayList;

import java.util.List;

import org.springframework.stereotype.Service;

import com.codeotrix.graphql.entity.User;

import graphql.schema.DataFetcher;

@Service
public class UserService {

	public DataFetcher<User> getUser() {
		return environment -> getUserDataWithId(environment.getArgument("id"));
	}

	public DataFetcher<List<User>> getUsers() {
		return environment -> getUserDataList();
	}

	public List<User> getUserDataList() {
		List<User> userList = new ArrayList();
		setUserDataList(userList);
		return userList;
	}

	public User getUserDataWithId(Integer id) {
		return getUserDataList().stream().filter(user -> id == user.getId()).findFirst().get();
	}

	public void setUserDataList(List<User> userList) {
		userList.add(new User(1, "Raj", 600));
		userList.add(new User(2, "Rahul", 700));
		userList.add(new User(3, "Rakesh", 650));
	}
}
