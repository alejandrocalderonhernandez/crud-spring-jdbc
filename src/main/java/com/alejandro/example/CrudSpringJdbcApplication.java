package com.alejandro.example;


import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alejandro.example.dao.IProductDao;
import com.alejandro.example.model.Product;
import com.alejandro.example.model.Review;
import com.alejandro.example.service.ProductService;

@SpringBootApplication
public class CrudSpringJdbcApplication implements CommandLineRunner{

	@Autowired
	private IProductDao dao;
	
	@Autowired
	private ProductService service;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJdbcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		Product insert = new Product();
		insert.setName("test");
		insert.setPrice(347.98);
		insert.setId(null);
		
		Review firstReview = new Review("test-1", Short.valueOf("5"));
		Review secondReview = new Review("test-2", Short.valueOf("5"));
		Set<Review> reviews = new HashSet<>();
		reviews.add(firstReview);
		reviews.add(secondReview);
		
		insert.setReviews(reviews);
		System.out.println(	this.service.save(insert));
		//System.out.println(dao.save(insert));
		//Product update = this.dao.findById(1L);
		//System.out.println("Update: " + update);
		
		System.out.println("Id: " + this.dao.getIdAssigned());
		
		System.out.println(	this.dao.findById(6L).toString());
		//update.setName("New car");
		//this.dao.save(update);
		//System.out.println("Update: " + update);
		//Set<Product> t = this.dao.findAll();
		//t.forEach(p -> System.out.println(p.toString()));
		//this.dao.delete(1L);
		//System.out.println("___");
		//Set<Product> d = this.dao.findAll();
		//d.forEach(p -> System.out.println(p.toString()));

	}

}
