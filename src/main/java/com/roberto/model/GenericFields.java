package com.roberto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@MappedSuperclass
@JsonIgnoreProperties(
		value = {"createdAt", "updatedAt"},
		allowGetters = true
)
public abstract class GenericFields implements Serializable {

	@Column(nullable = false, updatable = false)
	private Date createdAt;

	@Column(nullable = false)
	private Date updatedAt;

	@PrePersist
	public void prePersist() {
		Date date = new Date();
		createdAt = date;
		updatedAt = date;
	}

	@PreUpdate
	public void preUpdate() {
		updatedAt = new Date();
	}
}