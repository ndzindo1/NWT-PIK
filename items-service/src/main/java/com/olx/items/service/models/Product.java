package com.olx.items.service.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="categoryId", nullable=false)
	public Category category;
	
	private Long userId;
	
	private Boolean preOwned;
	
	private Boolean status;
	
	private Boolean arhived;
	
	private String name;
	
	private String location;
	
	private String description;
	
	private Double price;
	
	private Long createdAt;
	
	@OneToMany(targetEntity = Image.class, cascade = CascadeType.ALL)
	@JoinColumn(name="productId", referencedColumnName = "id")
	private List<Image> images;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getPreOwned() {
		return preOwned;
	}

	public void setPreOwned(Boolean preOwned) {
		this.preOwned = preOwned;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getArhived() {
		return arhived;
	}

	public void setArhived(Boolean arhived) {
		this.arhived = arhived;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
}