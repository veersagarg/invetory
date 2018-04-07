package com.roberto.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.roberto.config.Application;

/**
 * Created by roberto on 07/04/18.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class StatusControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void status() {
		String body = restTemplate.getForObject( "/status", String.class );

		assertThat( body, equalTo( "OK" ) );
	}

}