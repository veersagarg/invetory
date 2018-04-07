package com.roberto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.dto.ProductDTO;
import com.roberto.service.ProductService;

/**
 * Created by roberto on 02/08/17.
 */
@RestController
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity save(@RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok( service.save( productDTO ) );
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable String productId, @RequestBody ProductDTO productDTO) {
		return ResponseEntity.ok( service.update( Long.parseLong( productId ), productDTO ) );
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.DELETE)
	public ResponseEntity delete(@PathVariable String productId) {
		service.delete( Long.parseLong( productId ) );
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable String productId) {
		return ResponseEntity.ok( service.findOne( Long.parseLong( productId ) ) );
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity findByName(@PathVariable String name) {
		return ResponseEntity.ok( service.findByName( name ) );
	}

}