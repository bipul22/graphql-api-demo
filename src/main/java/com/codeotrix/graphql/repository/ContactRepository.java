package com.codeotrix.graphql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeotrix.graphql.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
