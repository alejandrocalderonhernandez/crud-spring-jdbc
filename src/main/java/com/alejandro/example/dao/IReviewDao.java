package com.alejandro.example.dao;

import com.alejandro.example.model.Review;

public interface IReviewDao extends WritteDao<Review, Long>{
	
	public static final String BUNDLE_PATH_REVIEW= "queries.review";

}
