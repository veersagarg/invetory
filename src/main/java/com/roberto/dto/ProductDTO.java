package com.roberto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.roberto.model.Product;

/**
 * Created by roberto on 29/08/17.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private Long quantity;

	public Product toEntity() {
		return new Product( id, name, description, quantity );
	}

	public static ProductDTO of(Product product) {
		return new ProductDTO( product.getId(), product.getName(), product.getDescription(), product.getQuantity() );
	}
}