package com.roberto.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.roberto.dto.ProductDTO;
import com.roberto.model.Product;
import com.roberto.repository.ProductRepository;

/**
 * Created by roberto on 08/08/17.
 */
public class ProductServiceTest {

	public static final long PRODUCT_ID = 1L;
	public static final String PRODUCT_NAME = "Ball";
	public static final String PRODUCT_DESCRIPTION = "Soccer ball";
	public static final long PRODUCT_QUANTITY = 10L;

	@InjectMocks
	private ProductService service;

	@Mock
	private ProductRepository repository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks( this );
	}

	@Test
	public void save() {
		service.save( mockProductDTO() );
		verify( repository ).save( mockProduct( null ) );
	}

	@Test
	public void update() {
		service.update( PRODUCT_ID, mockProductDTO() );
		verify( repository ).save( mockProduct( PRODUCT_ID ) );
	}

	@Test
	public void delete() {
		service.delete( PRODUCT_ID );
		verify( repository ).delete( PRODUCT_ID );
	}

	@Test
	public void get() {
		when( repository.findOne( PRODUCT_ID ) ).thenReturn( mockProduct( PRODUCT_ID ) );
		ProductDTO productDTO = service.findOne( PRODUCT_ID );

		assertProduct( productDTO );
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

	private ProductDTO mockProductDTO() {
		return new ProductDTO( null, PRODUCT_NAME, PRODUCT_DESCRIPTION, PRODUCT_QUANTITY );
	}
}