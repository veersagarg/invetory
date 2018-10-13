package com.roberto.dto;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.roberto.model.Product;

public class ProductDTOTest {

	private static final long PRODUCT_ID = 23L;
	private static final String PRODUCT_NAME = "Computer";
	private static final String PRODUCT_DESCRIPTION = "Small computer";
	private static final long PRODUCT_QUANTITY = 2L;

	@Test
	public void toEntity() {
		ProductDTO productDTO = aDTO();

		Product product = productDTO.toEntity();

		assertThat( product.getId(), equalTo( PRODUCT_ID ) );
		assertThat( product.getName(), equalTo( PRODUCT_NAME ) );
		assertThat( product.getDescription(), equalTo( PRODUCT_DESCRIPTION ) );
		assertThat( product.getQuantity(), equalTo( PRODUCT_QUANTITY ) );
	}

	@Test
	public void of() {
		ProductDTO productDTO = ProductDTO.of( aProduct() );

		assertThat( productDTO.getId(), equalTo( PRODUCT_ID ) );
		assertThat( productDTO.getName(), equalTo( PRODUCT_NAME ) );
		assertThat( productDTO.getDescription(), equalTo( PRODUCT_DESCRIPTION ) );
		assertThat( productDTO.getQuantity(), equalTo( PRODUCT_QUANTITY ) );
	}

	private Product aProduct() {
		return new Product( PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY );
	}

	private ProductDTO aDTO() {
		return new ProductDTO( PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY );
	}
}