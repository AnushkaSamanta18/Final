package com.cts.Flexride.Entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "car_table")
public class CarEntity {
	
	/*
	 * @OneToMany(mappedBy = "car", cascade=CascadeType.ALL, orphanRemoval=true)
	 * private List<BookingEntity> bookings;
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private String company;

	@Column(nullable = false)
	private double pricePerDay;

	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPricePerDay() {
		return pricePerDay;
	}

	public void setPricePerDay(double pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
}
