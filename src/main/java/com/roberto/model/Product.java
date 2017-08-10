package com.roberto.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by roberto on 02/08/17.
 */
@Entity
@Table(name="products")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@SequenceGenerator(name="seq_product", sequenceName="seq_product")
	@GeneratedValue(generator="seq_product", strategy= GenerationType.SEQUENCE)
	private Long id;

	private String name;

	private String description;

	private Long quantity;
}