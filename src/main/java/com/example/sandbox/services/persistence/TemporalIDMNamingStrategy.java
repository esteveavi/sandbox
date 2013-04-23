package com.example.sandbox.services.persistence;

import org.hibernate.cfg.DefaultComponentSafeNamingStrategy;

public class TemporalIDMNamingStrategy extends DefaultComponentSafeNamingStrategy {
	
	private static final long serialVersionUID = 6390027989379758119L;

	@Override
	public String propertyToColumnName(String propertyName) {
		if (propertyName.equalsIgnoreCase("group")||propertyName.equalsIgnoreCase("key")){
			return "_"+propertyName;
		} else {
			return super.propertyToColumnName(propertyName);
		}
	}
	@Override
	public String columnName(String columnName) {
		if (columnName.equalsIgnoreCase("group")||columnName.equalsIgnoreCase("key")){
			return "_"+columnName;
		} else {
			return super.columnName(columnName);
		}
	}
	
}
