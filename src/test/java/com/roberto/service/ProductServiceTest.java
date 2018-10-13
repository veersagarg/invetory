package com.roberto.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.roberto.dto.ProductDTO;
import com.roberto.model.Product;
import com.roberto.repository.ProductRepository;

/**
 * Created by roberto on 08/08/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	private static final long PRODUCT_ID = 1L;
	private static final String PRODUCT_NAME = "Ball";
	private static final String PRODUCT_DESCRIPTION = "Soccer ball";
	private static final long PRODUCT_QUANTITY = 10L;

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Test
	public void save() {
		Product product = mockProduct( null );
		Product savedProduct = mockProduct( PRODUCT_ID );
		when( repository.save( product ) ).thenReturn( savedProduct );

		ProductDTO savedProductDTO = service.save( mockProductDTO( null ) );

		verify( repository ).save( product );
		assertThat( savedProductDTO, equalTo( mockProductDTO( PRODUCT_ID ) ) );
	}

	@Test
	public void update() {
		Product product = mockProduct( PRODUCT_ID );
		when( repository.save( product ) ).thenReturn( product );

		ProductDTO updatedProductDTO = service.update( PRODUCT_ID, mockProductDTO( null ) );

		verify( repository ).save( mockProduct( PRODUCT_ID ) );
		assertThat( updatedProductDTO, equalTo( mockProductDTO( PRODUCT_ID ) ) );
	}

	@Test
	public void delete() {
		service.delete( PRODUCT_ID );

		verify( repository ).deleteById( PRODUCT_ID );
	}

	@Test
	public void get() {
		when( repository.findById( PRODUCT_ID ) ).thenReturn( Optional.of( mockProduct( PRODUCT_ID ) ) );

		ProductDTO productDTO = service.findOne( PRODUCT_ID );

		assertProduct( productDTO );
	}

	@Test
	public void getWhenIsEmpty() {
		when( repository.findById( PRODUCT_ID ) ).thenReturn( Optional.empty() );

		ProductDTO productDTO = service.findOne( PRODUCT_ID );

		assertNull( productDTO );
	}

	@Test
	public void findByName() {
		when( repository.findByName( PRODUCT_NAME ) )
				.thenReturn( Collections.singletonList( mockProduct( PRODUCT_ID ) ) );

		List<ProductDTO> productsDTO = service.findByName( PRODUCT_NAME );

		assertEquals( productsDTO.size(), 1 );
		assertProduct( productsDTO.get( 0 ) );
	}

	private void assertProduct(ProductDTO productDTO) {
		assertEquals( PRODUCT_ID, productDTO.getId(), 0 );
		assertEquals( PRODUCT_NAME, productDTO.getName() );
		assertEquals( PRODUCT_DESCRIPTION, productDTO.getDescription() );
		assertEquals( PRODUCT_QUANTITY, productDTO.getQuantity(), 0 );
	}

	private Product mockProduct(Long productId) {
		return new Product( productId, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY );
	}

	private ProductDTO mockProductDTO(Long productId) {
		return new ProductDTO( productId, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY );
	}
}