package com.gvt.generatecdr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class Oracle {

	private static Properties dbprops;
	private static Properties props = new Properties();
	
	private static Properties getProperties() throws IOException {
		
		if(dbprops == null) {			
			dbprops = new Properties();
			dbprops.setProperty("user", getProp().getProperty("dbUser"));
			dbprops.setProperty("password", getProp().getProperty("dbPass"));
		}
		
		return dbprops;
	}
	
	private static Properties getProp() throws IOException {
		
		if(dbprops == null) {
			FileInputStream file = new FileInputStream(new File(".").getCanonicalPath() + "/config/config.properties");
			props.load(file);
		}
		
		return props;
	}
	
	
	public static List<Map<String, Object>> get(String sql) throws SQLException, IOException {
		
		String url = "jdbc:oracle:thin:@"+getProp().getProperty("dbServer")+":"+getProp().getProperty("dbPort")+":"+getProp().getProperty("dbSID");
		if(getProp().getProperty("dbServiceName") != null && getProp().getProperty("dbServiceName").length() > 1) {
			url = "jdbc:oracle:thin:@"+getProp().getProperty("dbServer")+":"+getProp().getProperty("dbPort")+"/"+getProp().getProperty("dbServiceName");
		}
        Connection conn = DriverManager.getConnection(url, getProperties());
        
        PreparedStatement preStatement = conn.prepareStatement(sql);
        
        ResultSet result = preStatement.executeQuery();
        
        List<Map<String, Object>> rows = getHashMap(result);
        
        result.close();
        conn.close();
        
        return rows;
	}
	
	public static void setBilled() throws Exception {
		
		String url = "jdbc:oracle:thin:@"+getProp().getProperty("dbServer")+":"+getProp().getProperty("dbPort")+":"+getProp().getProperty("dbSID");
		if(getProp().getProperty("dbServiceName") != null && getProp().getProperty("dbServiceName").length() > 1) {
			url = "jdbc:oracle:thin:@"+getProp().getProperty("dbServer")+":"+getProp().getProperty("dbPort")+"/"+getProp().getProperty("dbServiceName");
		}
	      
        Connection conn = DriverManager.getConnection(url, getProperties());
        
        PreparedStatement preStatement = conn.prepareStatement("UPDATE IPVOD_PURCHASE SET BILLED = 1");
        
        preStatement.executeUpdate();
        
        conn.close();
		
	}
	
	private static List<Map<String, Object>> getHashMap(ResultSet rs_SubItemType) throws SQLException {
		 
		List<Map<String, Object>> rows = new ArrayList<Map<String,Object>>();
		
		ResultSetMetaData metaData = rs_SubItemType.getMetaData();
		int colCount = metaData.getColumnCount();
		 
		while (rs_SubItemType.next()) {
			Map<String, Object> columns = new HashMap<String, Object>();
			for (int i = 1; i <= colCount; i++) {
				
				columns.put(metaData.getColumnLabel(i), rs_SubItemType.getObject(i));
			}
			
			if(columns.get("CRM_CUSTOMER_ID") != null) {
				if(((String)columns.get("CRM_CUSTOMER_ID")).indexOf("TV-") >= 0) {
					rows.add(columns);
				}
			} else {
				rows.add(columns);
			}
		}
		
		return rows;
	}
	
	
}
