package com.alejandro.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alejandro.example.model.Review;
@Repository
public class ReviewDaoImpl implements IReviewDao {
	
	@Autowired
	private JdbcTemplate template;
		
    @Value("${review.save}")
	private  String insertQuery;
    
    @Value("${review.delete}")
	private  String deleteByIdQuery;
	
	
	@Override
	public boolean save(Review model) {
		Object[] args = {model.getDescription(), model.getRainting(), model.getIdProduct()};
		return template.update(this.insertQuery, args) == 1;
	}
	
	@Override
	public boolean delete(Long id) {
		Object[] args = {id};
		return this.template.update(this.deleteByIdQuery, args) == 1;
	}
}
