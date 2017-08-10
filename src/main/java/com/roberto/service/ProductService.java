package com.roberto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roberto.model.Product;
import com.roberto.repository.ProductRepository;

/**
 * Created by roberto on 02/08/17.
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public void save(Product product){
		repository.save( product );
	}

	public Product findOne(Long productId){
		return repository.findOne( productId );
	}

	public void delete(Long productId){
		repository.delete( productId );
}

	public List<Product> findByName(String name) {
		return repository.findByName( name);
	}
}