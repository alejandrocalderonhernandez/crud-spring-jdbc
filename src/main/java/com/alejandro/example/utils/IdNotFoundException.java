package com.alejandro.example.utils;

public class IdNotFoundException  extends Exception {

	private static final long serialVersionUID = -8798861232879024864L;

	public IdNotFoundException(String id) {
		super("Id" + id + "was not found");
	}


}
