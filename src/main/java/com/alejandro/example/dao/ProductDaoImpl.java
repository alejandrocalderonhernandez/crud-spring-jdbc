package com.alejandro.example.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alejandro.example.model.Product;
import com.alejandro.example.utils.Utils;

@Repository
public class ProductDaoImpl implements IProductDao {

	private static final Logger log = LoggerFactory.getLogger(ProductDaoImpl.class);
	
	@Autowired
	private JdbcTemplate template;
			
	private static String updateQuery;
	private static String insertQuery;
	private static String selectByIdQuery;
	private static String selectAllQuery;
	private static String deleteByIdQuery;
	
	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH);
			updateQuery = bundle.getString("update");
			insertQuery = bundle.getString("save");
			selectAllQuery = bundle.getString("findAll");
			selectByIdQuery = bundle.getString("findById");
			deleteByIdQuery = bundle.getString("delete");
		} catch (MissingResourceException e ) {
			log.error("Error to get resource", e);
		}
	}
	
	@Override
	public Set<Product> findAll() {
		return new HashSet<>(this.template.query(selectAllQuery, Product.getMapper())); 
	}

	@Override
	public Product findById(Long id) {
		Object[] args =  {id};
		Product response = new Product();
		this.template.query(selectByIdQuery,  Product.getMapper(), args)
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
			return this.template.update(insertQuery, args) == 1;
		} else {
			Object[] args = { model.getName(), model.getPrice(), now, model.getId(),};
			return this.template.update(updateQuery, args ) == 1;
		}
	}

	@Override
	public boolean delete(Long id) {
		Object[] args =  {id};
		return this.template.update(deleteByIdQuery, args ) == 1;
	}

}
