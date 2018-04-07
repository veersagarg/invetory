package com.roberto.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.roberto.config.Application;
import com.roberto.dto.ProductDTO;

/**
 * Created by roberto on 07/04/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class ProductControllerTest {

	private static final String PRODUCT_NAME = "ball";
	private static final String UPDATED_PRODUCT_NAME = "ball";
	private static final String ANOTHER_PRODUCT_NAME = "computer";

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void save() {
		ResponseEntity response = restTemplate
				.postForEntity( "/products", buildProduct( PRODUCT_NAME ), ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
	}

	@Test
	public void update() {
		ResponseEntity<ProductDTO> createdResponse = restTemplate
				.postForEntity( "/products", buildProduct( PRODUCT_NAME ), ProductDTO.class );
		ProductDTO product = createdResponse.getBody();
		product.setName( UPDATED_PRODUCT_NAME );
		HttpEntity<ProductDTO> request = new HttpEntity<>( product, null );

		ResponseEntity<ProductDTO> response = restTemplate
				.exchange( "/products/" + product.getId(), HttpMethod.PUT, request, ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( response.getBody().getName(), equalTo( UPDATED_PRODUCT_NAME ) );
	}

	@Test
	public void delete() {
		ResponseEntity<ProductDTO> createdResponse = restTemplate
				.postForEntity( "/products", buildProduct( PRODUCT_NAME ), ProductDTO.class );
		ProductDTO product = createdResponse.getBody();

		ResponseEntity response = restTemplate
				.exchange( "/products/" + product.getId(), HttpMethod.DELETE, null, ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.NO_CONTENT ) );
	}

	@Test
	public void get() {
		ResponseEntity<ProductDTO> createdResponse = restTemplate
				.postForEntity( "/products", buildProduct( PRODUCT_NAME ), ProductDTO.class );
		ProductDTO product = createdResponse.getBody();

		ResponseEntity<ProductDTO> response = restTemplate
				.getForEntity( "/products/" + product.getId(), ProductDTO.class );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		assertThat( response.getBody().getName(), equalTo( PRODUCT_NAME ) );
	}

	@Test
	public void findByName() {
		restTemplate.postForEntity( "/products", buildProduct( ANOTHER_PRODUCT_NAME ), ProductDTO.class );

		ResponseEntity<List<ProductDTO>> response = restTemplate
				.exchange( "/products/find/" + ANOTHER_PRODUCT_NAME, HttpMethod.GET, null,
						new ParameterizedTypeReference<List<ProductDTO>>() {
						} );

		assertThat( response.getStatusCode(), equalTo( HttpStatus.OK ) );
		List<ProductDTO> products = response.getBody();
		assertThat( products, hasSize( 1 ) );
		assertThat( products.get( 0 ).getName(), equalTo( ANOTHER_PRODUCT_NAME ) );
	}

	private ProductDTO buildProduct(String name) {
		return ProductDTO.builder()
				.name( name )
				.quantity( 10L )
				.build();
	}

}