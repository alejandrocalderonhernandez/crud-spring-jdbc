package com.alejandro.example.service;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alejandro.example.dao.DaoUtil;
import com.alejandro.example.dao.IProductDao;
import com.alejandro.example.dao.IReviewDao;
import com.alejandro.example.model.Product;
import com.alejandro.example.model.Review;
import com.alejandro.example.utils.ElementNorSavedException;
import com.alejandro.example.utils.EmptyDatabaseException;
import com.alejandro.example.utils.IdNotFoundException;

@Service
@Transactional(noRollbackFor = Exception.class)
public class ProductServiceImpl implements ProductService {
	
	
	private static final String TABLE_NAME = "product";
	
	private IProductDao productDao;
	private IReviewDao reviewDao;
	private DaoUtil  daoUtils;
	
	@Autowired
	public ProductServiceImpl(IProductDao productDao, IReviewDao reviewDao,  DaoUtil daoUtils) {
		this.productDao = productDao;
		this.reviewDao = reviewDao;
		this.daoUtils = daoUtils;
	}

	@Override
	public Set<Product> findAll() throws EmptyDatabaseException {
		Set<Product> response = this.productDao.findAll();
		if(!response.isEmpty()) {
			return response;
		} else {
			throw new EmptyDatabaseException();
		}
	}

	@Override
	public Product findById(Long id) throws IdNotFoundException {
		Product  response = this.productDao.findById(id);
		if(response != null) {
			return response;
		} else {
			throw new IdNotFoundException(id.toString());
		}
	}

	@Override
	public void save(Product model) throws ElementNorSavedException {
		try {
			 this.productDao.save(model);
			Set<Review> reviews = model.getReviews()
				.stream()
					.map(r -> new Review(r.getDescription(), r.getRainting(),  this.getId()))
					.collect(Collectors.toSet());
			reviews.forEach(this.reviewDao::save);
		} catch(Exception e) {
			System.out.println("problem: " + e.getMessage());
			throw new ElementNorSavedException();
		}
	}

	@Override
	public void delete(Long id) throws IdNotFoundException {
		if(!this.productDao.delete(id)) {
			throw new IdNotFoundException(id.toString());
		}
	}
	
	@Override
	public Long count() {
		return this.daoUtils.count(TABLE_NAME);
	}
	
	private Long  getId() {
		return this.daoUtils.getIdAssigned(TABLE_NAME);
	}


}
