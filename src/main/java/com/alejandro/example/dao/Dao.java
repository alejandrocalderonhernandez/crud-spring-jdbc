package com.alejandro.example.dao;

import java.io.Serializable;
import java.util.Set;

public interface Dao<T extends Serializable, I> {
	
	public Set<T> findAll();
	public T findById(I id);
	public boolean save(T model);
	public boolean delete(I id);

}
