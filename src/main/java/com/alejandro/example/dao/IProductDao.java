package com.alejandro.example.dao;

import com.alejandro.example.model.Product;

public interface IProductDao extends CrudDao<Product, Long>{
	
	public static final String BUNDLE_PATH = "queries.product";

}
