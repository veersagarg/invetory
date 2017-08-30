package com.roberto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.roberto.dto.ProductDTO;
import com.roberto.model.Product;
import com.roberto.repository.ProductRepository;

/**
 * Created by roberto on 02/08/17.
 */
@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	public void save(ProductDTO productDTO) {
		repository.save( productDTO.toEntity() );
	}

	public void update(Long productId, ProductDTO productDTO) {
		Product product = productDTO.toEntity();
		product.setId( productId );
		repository.save( product );
	}

	public void delete(Long productId) {
		repository.delete( productId );
	}

	public ProductDTO findOne(Long productId) {
		return ProductDTO.of( repository.findOne( productId ) );
	}

	public List<ProductDTO> findByName(String name) {
		List<Product> products = repository.findByName( name );
		return products.stream().map( product -> ProductDTO.of( product ) )
				.collect( Collectors.toList() );
	}
}