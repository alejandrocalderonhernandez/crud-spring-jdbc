package com.alejandro.example.dao;

import java.io.Serializable;

public interface WritteDao<T extends Serializable, I> {
	
	public boolean save(T model);
	public boolean delete(I id);
}
