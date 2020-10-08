package br.com.blz.testjava.config;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.io.Resources;

import br.com.blz.testjava.repository.ProductRestRepository;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;

@Configuration
public class GraphQLConfig {

	@Autowired
	private ProductRestRepository productRestRepository;
	
	private GraphQL graphQL;
	
	@Bean
    public GraphQL graphQL() { 
        return graphQL;
    }
	
	@PostConstruct
    public void init() throws IOException {
        this.graphQL = GraphQL
        		.newGraphQL(buildSchema(Resources.toString(Resources.getResource("graphql/schema.graphqls"), StandardCharsets.UTF_8)))
        		.build();
    }

    private GraphQLSchema buildSchema(String sdl) {
    	return new SchemaGenerator().makeExecutableSchema(new SchemaParser().parse(sdl), buildWiring());
    }
	
    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
        	.type(newTypeWiring("Query")
    			.dataFetcher("listProducts", dataFetching -> productRestRepository.findAll())
        		.dataFetcher("productById", dataFetching -> productRestRepository.findById(Long.valueOf(dataFetching.getArgument("sku")))))
            .build();
    }
    
}
