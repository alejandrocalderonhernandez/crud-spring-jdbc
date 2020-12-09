package com.alejandro.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DaoUtil {
	
	@Autowired
	private JdbcTemplate template;
	
	private static final String TABLE_PLACEHOLDER = "--table--";
	
    @Value("${getIdAssigned}")
	private String getIdAssigned;
    @Value("${count}")
	private String count;
	
	/* Reference if we don't load the properties using spring
	 * static { try { ResourceBundle bundle = ResourceBundle.getBundle(queries.utils);
	 * getIdAssigned = bundle.getString("getIdAssigned"); count =
	 * bundle.getString("count"); } catch (MissingResourceException e ) {
	 * log.error("Error to get resource", e); } }
	 */
	
	public Long getIdAssigned(String tableName) {
		return  this.template.queryForObject(
				this.getIdAssigned.replace(TABLE_PLACEHOLDER, tableName), Long.class);
	}
	
	public Long count(String tableName) {
		return  this.template.queryForObject(
				this.count.replace(TABLE_PLACEHOLDER, tableName), Long.class);
	}
}
