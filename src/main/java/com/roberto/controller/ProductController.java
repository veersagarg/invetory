package com.roberto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roberto.model.Product;
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
	public void save(@RequestBody Product product) {
		service.save( product );
	}

	@RequestMapping(value =  "{productId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Product get(@PathVariable String productId){
		return service.findOne(Long.parseLong( productId ));
	}

	@RequestMapping(value = "{productId}", method = RequestMethod.DELETE)
	public void delete(@PathVariable String productId) {
		service.delete( Long.parseLong( productId ));
	}

	@RequestMapping(value = "/find/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Product> findByName(@PathVariable String name){
		return service.findByName( name );
	}
}