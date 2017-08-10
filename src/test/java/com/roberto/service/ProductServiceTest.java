package com.roberto.service;

import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

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
	public void save(){
		Product product = mockProduct();
		service.save( product );
		Mockito.verify(repository).save( product );
	}

	@Test
	public void get(){
		Mockito.when( repository.findOne( PRODUCT_ID )).thenReturn( mockProduct(PRODUCT_ID) );
		Product product = service.findOne( PRODUCT_ID );

		assertProduct(product);
	}

	@Test
	public void delete(){
		service.delete( PRODUCT_ID );
		Mockito.verify(repository).delete( PRODUCT_ID );
	}

	@Test
	public void findByName(){
		Mockito.when( repository.findByName( PRODUCT_NAME )).thenReturn( Collections.singletonList(mockProduct(PRODUCT_ID)) );
		List<Product> products = service.findByName( PRODUCT_NAME );
		Product product = products.get( 0 );

		Assert.assertEquals( products.size(), 1 );
		assertProduct(product);
	}

	private void assertProduct(Product product){
		Assert.assertEquals( PRODUCT_ID, product.getId(), 0 );
		Assert.assertEquals( PRODUCT_NAME, product.getName());
		Assert.assertEquals( PRODUCT_DESCRIPTION, product.getDescription() );
		Assert.assertEquals( PRODUCT_QUANTITY, product.getQuantity(), 0 );
	}

	private Product mockProduct(Long id){
		Product product = mockProduct();
		product.setId( id );
		return  product;
	}

	private Product mockProduct(){
		return Product.builder()
				.name( PRODUCT_NAME )
				.description( PRODUCT_DESCRIPTION )
				.quantity( PRODUCT_QUANTITY )
				.build();
	}

}