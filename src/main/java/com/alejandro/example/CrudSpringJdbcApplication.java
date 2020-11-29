package com.alejandro.example;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alejandro.example.model.Product;
import com.alejandro.example.model.Review;
import com.alejandro.example.service.ProductService;

@SpringBootApplication
public class CrudSpringJdbcApplication implements CommandLineRunner{
	
	private static final Logger log = LoggerFactory.getLogger(CrudSpringJdbcApplication.class) ;
	
	@Autowired
	private ProductService service;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJdbcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Get records inserted into database 
		log .info(this.service.findAll().toString());
		
		//Create a new record and insert, the id should be 6
		Product toInsert = new Product();
		toInsert.setName("test");
		toInsert.setPrice(347.98);
		toInsert.setId(null);
		Review firstReview = new Review("test-1", Short.valueOf("5"));
		Review secondReview = new Review("test-2", Short.valueOf("5"));
		Review thridReview = new Review("test-3", Short.valueOf("5"));
		Set<Review> reviews = new HashSet<>();
		reviews.add(firstReview);
		reviews.add(secondReview);
		reviews.add(thridReview);
		toInsert.setReviews(reviews);
		this.service.save(toInsert);
		log.info(this.service.findById(6L).toString());
		
		//Update record with id 6 change name test for updatedTest
		Product toUpdate = this.service.findById(6L);
		toUpdate.setName("updatedTest");
		this.service.save(toUpdate);
		log.info(this.service.findById(6L).toString());
		
		//Delete a record with id 1 First log the size should be equals to 6, second log equals to 5
		log.info("before delete nuber records is: " + this.service.count() );
		this.service.delete(1L);
		log.info("after delete nuber records is: " + this.service.count() );

	}

}
