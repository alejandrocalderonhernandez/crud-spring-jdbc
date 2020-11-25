package com.alejandro.example.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.dao.IProductDao;
import com.alejandro.example.dao.IReviewDao;
import com.alejandro.example.model.Product;
import com.alejandro.example.model.Review;

@Service
@Transactional(noRollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
	
	private IProductDao productDao;
	private IReviewDao reviewDao;
	
	@Autowired
	public ProductServiceImpl(IProductDao productDao, IReviewDao reviewDao) {
		this.productDao = productDao;
		this.reviewDao = reviewDao;
	}

	@Override
	public Set<Product> findAll() {
		return null;
	}

	@Override
	public Product findById(Long id) {
		return null;
	}

	@Override
	public boolean save(Product model) {
		try {
			 this.productDao.save(model);
			Set<Review> reviews = model.getReviews()
				.stream()
					.map(r -> new Review(r.getDescription(), r.getRainting(),  this.getId()))
					.collect(Collectors.toSet());
			System.out.println(reviews);
			reviews.forEach(this.reviewDao::save);
					
			return true;
		} catch(Exception e) {
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		return false;
	}
	
	private Long  getId() {
		return this.productDao.getIdAssigned();
	}

}
