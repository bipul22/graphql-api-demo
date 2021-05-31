package com.codeotrix.graphql;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.codeotrix.graphql.service.UserService;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;

@SpringBootApplication
public class GraphqlApiDemoApplication {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(GraphqlApiDemoApplication.class, args);
	}

	@Bean
	public GraphQL graphQL() throws IOException {

		// Getting file from resource folder.
		ClassPathResource classPathResource = new ClassPathResource("schema.graphql");

		// Initializing the typeDefinitionRegistry from schema file using SchemaParser.
		TypeDefinitionRegistry typeDefinitionRegistry = new SchemaParser().parse(classPathResource.getInputStream());

		// Setting runtimeWiring with service methods
		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getUser", userService.getUser()))
				.type(TypeRuntimeWiring.newTypeWiring("Query").dataFetcher("getUsers", userService.getUsers())).build();

		// Initializing the graphQLSchema using SchemaGenerator
		// Passing typeDefinitionRegistry and runtimeWiring as arguments.
		GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeDefinitionRegistry, runtimeWiring);

		// Returning the GraphQL object
		return GraphQL.newGraphQL(graphQLSchema).build();
	}
}
