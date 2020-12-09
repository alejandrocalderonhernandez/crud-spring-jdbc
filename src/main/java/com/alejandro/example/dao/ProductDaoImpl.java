package com.alejandro.example.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alejandro.example.model.Product;
import com.alejandro.example.utils.Utils;

@Repository
public class ProductDaoImpl implements IProductDao {
	
	@Autowired
	private JdbcTemplate template;
			
    @Value("${product.update}")
	private  String updateQuery;
    
    @Value("${product.save}")
	private  String insertQuery;
    
    @Value("${product.findById}")
	private  String selectByIdQuery;
    
    @Value("${product.findAll}")
	private  String selectAllQuery;
    
    @Value("${product.delete}")
	private  String deleteByIdQuery;
	
	@Override
	public Set<Product> findAll() {
		return new HashSet<>(this.template.query(selectAllQuery, Product.getMapper())); 
	}

	@Override
	public Product findById(Long id) {
		Object[] args =  {id};
		Product response = new Product();
		this.template.query(this.selectByIdQuery,  Product.getMapper(), args)
		       .forEach(p -> {
		    	   response.setId(p.getId());
		    	   response.setName(p.getName());
		    	   response.setPrice(p.getPrice());
		    	   response.setCreateAt(p.getCreateAt());
		    	   response.addReviews(p.getTempReview());
		       });
	
		return response;
	}

	@Override
	public boolean save(Product model) {
	      Timestamp now = Utils.toTimestamp(LocalDateTime.now());
		if(model.getId() == null) {
			Object[] args = {model.getName(), model.getPrice(), now};
			return this.template.update(this.insertQuery, args) == 1;
		} else {
			Object[] args = { model.getName(), model.getPrice(), now, model.getId()};
			return this.template.update(this.updateQuery, args ) == 1;
		}
	}

	@Override
	public boolean delete(Long id) {
		Object[] args =  {id};
		return this.template.update(this.deleteByIdQuery, args ) == 1;
	}

}
