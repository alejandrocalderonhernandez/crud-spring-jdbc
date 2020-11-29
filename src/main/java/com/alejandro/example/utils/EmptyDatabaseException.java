package com.alejandro.example.utils;

public class EmptyDatabaseException extends Exception {

	private static final long serialVersionUID = -8798861232839024867L;

	public EmptyDatabaseException() {
		super("The database is empty");
	}

}
