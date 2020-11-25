package com.alejandro.example.dao;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alejandro.example.model.Review;
@Repository
public class ReviewDaoImpl implements IReviewDao {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final Logger log= LoggerFactory.getLogger(ReviewDaoImpl.class);
	
	private static String insertQuery;
	private static String deleteByIdQuery;
	
	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH_REVIEW);
			insertQuery = bundle.getString("save");
			deleteByIdQuery = bundle.getString("delete");
		} catch (MissingResourceException e ) {
			log.error("Error to get resource", e);
		}
	}
	
	
	@Override
	public boolean save(Review model) {
		Object[] args = {model.getDescription(), model.getRainting(), model.getIdProduct()};
		return template.update(insertQuery, args) == 1;
	}
	
	@Override
	public boolean delete(Long id) {
		return false;
	}
}
