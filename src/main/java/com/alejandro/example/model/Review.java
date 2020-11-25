package com.alejandro.example.model;

import java.io.Serializable;

import com.google.gson.Gson;

public class Review implements Serializable {

	private static final long serialVersionUID = -1218994529062036589L;
	
	private Long id;
	private String description;
	private Short rainting;
	
	public Review() {
	}

	public Review(String description, Short rainting) {
		this.description = description;
		this.rainting = rainting;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Short getRainting() {
		return rainting;
	}

	public void setRainting(Short rainting) {
		this.rainting = rainting;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this).toString();
	}
}
