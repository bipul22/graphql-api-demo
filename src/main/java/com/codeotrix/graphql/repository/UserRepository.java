package com.codeotrix.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeotrix.graphql.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
