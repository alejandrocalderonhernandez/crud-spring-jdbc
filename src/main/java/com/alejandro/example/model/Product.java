package com.alejandro.example.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.jdbc.core.RowMapper;

import com.google.gson.Gson;

public class Product implements Serializable {

	private static final long serialVersionUID = 6839021250341801528L;
	
	private transient Review tempReview;
	
	private Long id;
	private String name;
	private Double price;
	private LocalDateTime createAt;
	private Set<Review> reviews;
	
	public Product() {
		this.reviews = new HashSet<>();
	}

	public Product(Product product) {
		this.reviews = new HashSet<>();
		this.setId(product.getId());
		this.setName(product.getName());
		this.setPrice(product.getPrice());
		product.setCreateAt(product.createAt);
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LocalDateTime getCreateAt() {
		return createAt;
	}

	public void setCreateAt(LocalDateTime createAt) {
		this.createAt = createAt;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}

	public void addReviews(Review review) {
		this.reviews.add(review);
	}

	public Review getTempReview() {
		return tempReview;
	}

	public void setTempReview(Review tempReview) {
		this.tempReview = tempReview;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
	
	public static RowMapper<Product> getMapper(){
		return (rs, rowNum) -> {
			Product p = new Product();
			p.setId(rs.getLong("id"));
			p.setName(rs.getString("name"));
			p.setPrice(rs.getDouble("price"));
			p.setCreateAt(rs.getTimestamp("create_at").toLocalDateTime());
		    if(rs.getString("description") != null ) {
		    	String description = rs.getString("description");
		    	Short rating = rs.getShort("rating");
		    	p.setTempReview(new Review(description, rating));
		    }
			return p;
		};
	}

}
