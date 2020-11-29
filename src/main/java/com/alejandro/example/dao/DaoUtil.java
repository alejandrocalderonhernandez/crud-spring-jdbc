package com.alejandro.example.dao;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoUtil {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final Logger log = LoggerFactory.getLogger(DaoUtil.class);
	
	private static final String BUNDLE_PATH = "queries.utils";
	private static final String TABLE_PLACEHOLDER = "--table--";
	
	private static String getIdAssigned;
	private static String count;
	
	static {
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(BUNDLE_PATH);
			getIdAssigned = bundle.getString("getIdAssigned");
			count = bundle.getString("count");
		} catch (MissingResourceException e ) {
			log.error("Error to get resource", e);
		}
	}
	
	public Long getIdAssigned(String tableName) {
		return  this.template.queryForObject(
				getIdAssigned.replace(TABLE_PLACEHOLDER, tableName), Long.class);
	}
	
	public Long count(String tableName) {
		return  this.template.queryForObject(
				count.replace(TABLE_PLACEHOLDER, tableName), Long.class);
	}
}
