package com.alejandro.example.service;

import java.util.Set;

public interface IService<T> {
	
	public Set<T> findAll();
	public T findById(Long id);
	public boolean save(T model);
	public boolean delete(Long id); 

}
