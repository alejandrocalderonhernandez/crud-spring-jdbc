package com.alejandro.example.service;

import java.util.Set;

import com.alejandro.example.utils.ElementNorSavedException;
import com.alejandro.example.utils.EmptyDatabaseException;
import com.alejandro.example.utils.IdNotFoundException;

public interface IService<T> {
	
	public Set<T> findAll() throws EmptyDatabaseException;
	public T findById(Long id) throws IdNotFoundException;
	public void save(T model) throws ElementNorSavedException;
	public void delete(Long id) throws IdNotFoundException; 
	public Long count ();

}
