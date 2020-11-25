package com.alejandro.example;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.alejandro.example.dao.IProductDao;
import com.alejandro.example.model.Product;

@SpringBootApplication
public class CrudSpringJdbcApplication implements CommandLineRunner{

	@Autowired
	private IProductDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(CrudSpringJdbcApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		
		//Product insert = new Product();
		//insert.setName("test");
		//insert.setPrice(347.98);
		//insert.setId(null);
		//System.out.println(dao.save(insert));
		Product update = this.dao.findById(2L);
		System.out.println("Update: " + update);
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
