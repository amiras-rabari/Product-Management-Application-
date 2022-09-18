package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Product_info")
public class Product {

	@Id
	@GeneratedValue
	private Long id;

	@Size(min = 5)
//	@Column(unique = true)
	private String name;

	@NotBlank
	private String model;

	@NotBlank
	private String manufacturerName;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(@Size(min = 5) String name, @NotBlank String model, @NotBlank String manufacturerName) {
		super();
		this.name = name;
		this.model = model;
		this.manufacturerName = manufacturerName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", model=" + model + ", manufacturerName=" + manufacturerName
				+ "]";
	}

}
