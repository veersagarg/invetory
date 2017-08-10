package com.roberto.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.roberto.model.Product;

/**
 * Created by roberto on 02/08/17.
 */
public interface ProductRepository extends CrudRepository<Product, Long>{

	List<Product> findByName(String name);

}