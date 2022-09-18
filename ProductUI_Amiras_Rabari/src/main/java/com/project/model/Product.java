package com.project.model;




public class Product {

	private Long id;


	private String name;

	private String model;

	private String manufacturerName;

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Product(String name, String model, String manufacturerName) {
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
