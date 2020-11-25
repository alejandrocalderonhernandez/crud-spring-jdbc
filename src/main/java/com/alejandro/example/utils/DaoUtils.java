package com.alejandro.example.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DaoUtils {
	
	public static Timestamp toTimestamp(LocalDateTime date) {
		return  Timestamp.valueOf(date);
	}
}
