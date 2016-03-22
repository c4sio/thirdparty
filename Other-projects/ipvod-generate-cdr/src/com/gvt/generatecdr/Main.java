package com.gvt.generatecdr;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

public class Main {

	private static int fileSize = 1000;
	private static String dir = ".";
	
	public static void main(String[] args) throws Exception {
		
		
		String sql = "SELECT * "
				    + " FROM IPVOD_PURCHASE, IPVOD_ASSET, IPVOD_EQUIPMENT, IPVOD_USER, IPVOD_CONTENT_PROVIDER "
        		    + "WHERE IPVOD_PURCHASE.ASSET_ID = IPVOD_ASSET.ASSET_ID "
        		    + "  AND IPVOD_PURCHASE.EQUIPMENT_ID = IPVOD_EQUIPMENT.EQUIPMENT_ID "
        		    + "  AND IPVOD_EQUIPMENT.USER_ID = IPVOD_USER.USER_ID "
        		    + "  AND IPVOD_ASSET.CONTENT_PROVIDER_ID = IPVOD_CONTENT_PROVIDER.CONTENT_PROVIDER_ID "
        		    + "  AND IPVOD_PURCHASE.BILLED = 0";
		
		
        for(int i=0; i<args.length; i++) {
        	if(args[i].toLowerCase().equals("-dir")) {
        		if(i+1 < args.length) {
        			if(args[i+1].length() > 0) {
        				dir = args[i+1];
        			}
        		}
        		
        	} else if(args[i].toLowerCase().equals("-filesize")) {
        		if(i+1 < args.length) {
        			if(args[i+1].length() > 0) {
        				fileSize = Integer.parseInt(args[i+1]);
        			}	
        		}
        	}        	
        }
		
        
        List<Map<String, Object>> rows = Oracle.get(sql);
        generate(rows);
        //update
        Oracle.setBilled();

	}	

	
	public static void generate(List<Map<String, Object>> ipvodPurchases) throws SQLException, IOException {
		System.out.println(dir);
		new File(dir).mkdirs();
		
		int count = 0;
		int filesSize = 0;
		
		while(count < ipvodPurchases.size()) {
			Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String formattedDate = formatter.format(new Date());
			String fileName = "IPVOD_VDR-"+formattedDate.replaceAll(":", "-")+".xml";
			PrintWriter writer = new PrintWriter(dir+"/" + fileName, "UTF-8");			
			filesSize++;
			writer.println("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
			writer.println("<ChargingDataRecords xmlns=\"http://chargingcs.iap.iptv.ericsson.com/OfflineCharging/ChargingDataRecord/\" StartProcessingTime=\""+formattedDate+"\" ChargingDataVersion=\"1.0\">");
			for(int i=count; i<ipvodPurchases.size() && i<fileSize*filesSize; i++) {
				
				Map<String, Object> ipvodPurchase = ipvodPurchases.get(i);
				
				String sql = "SELECT IPVOD_PACKAGE.DESCRIPTION " +
				"FROM IPVOD_PACKAGE_SUBSCRIPTION, IPVOD_PACKAGE, IPVOD_ASSET_PACKAGE " +
				"WHERE IPVOD_PACKAGE_SUBSCRIPTION.PACKAGE_ID = IPVOD_PACKAGE.PACKAGE_ID " +
				"AND IPVOD_ASSET_PACKAGE.PACKAGE_ID = IPVOD_PACKAGE.PACKAGE_ID " +
				"AND IPVOD_ASSET_PACKAGE.ASSET_ID = "+ipvodPurchase.get("ASSET_ID")+" " +
				"AND IPVOD_PACKAGE_SUBSCRIPTION.USER_ID = "+ipvodPurchase.get("USER_ID")+" ";
				
				List<Map<String, Object>> rows = Oracle.get(sql);
				String ipvod = "IPVOD";
				if(rows.size() > 0) {
					
					//System.out.println(rows.get(0).get("DESCRIPTION"));
					ipvod = rows.get(0).get("DESCRIPTION").toString();
				}
				
				count++;
				
				writer.println("	<ChargingDataRecord>");
				
				try {
					try {
						int rating = Integer.parseInt(ipvodPurchase.get("RATING_LEVEL").toString());
						if(ipvodPurchase.get("RATING_LEVEL") != null && (rating == 7 || rating == 8)) {
							ipvodPurchase.put("TITLE", "ON DEMAND");
						}
					} catch(Exception e) {
						//rating is not null and not a number
						//ignore then..
					}
					
					writer.println("		<PurchaseId>"+ipvodPurchase.get("PURCHASE_ID")+"</PurchaseId>");
					writer.println("		<PurchaseDate>"+getFormattedDate(ipvodPurchase.get("PURCHASE_DATE").toString())+"</PurchaseDate>");
					writer.println("		<Price>");
					writer.println("	    	<Amount>"+ipvodPurchase.get("PRICE")+"</Amount>");
					writer.println("	    	<Currency>XXX</Currency>");
					writer.println("		</Price>");
					writer.println("		<DefaultPrice>");
					writer.println("	    	<Amount>"+ipvodPurchase.get("PRICE")+"</Amount>");
					writer.println("	    	<Currency>XXX</Currency>");
					writer.println("		</DefaultPrice>");
					writer.println("		<ChargingId>"+ipvodPurchase.get("CRM_CUSTOMER_ID")+"</ChargingId>");
					writer.println("		<Feature>VOD</Feature>");
					writer.println("		<OfferingId>"+ipvodPurchase.get("ASSET_ID")+"</OfferingId>");
					writer.println("		<BillingText>"+ipvodPurchase.get("TITLE") + "</BillingText>");
					writer.println("		<Units>1</Units>");
					writer.println("		<ServiceRegion>"+ipvodPurchase.get("SERVICE_REGION")+"</ServiceRegion>");
					writer.println("		<Metadata>");
					writer.println("   			<Value Name=\"AssetId\">"+ipvodPurchase.get("ASSET_ID")+"</Value>");
					writer.println("    		<Value Name=\"BillingId\">"+ipvodPurchase.get("BILLING_ID")+"</Value>");
					writer.println("   			<Value Name=\"DeliveryMode\">stream</Value>");
					writer.println("   			<Value Name=\"EndDate\">"+getFormattedDate(ipvodPurchase.get("LICENSE_WINDOW_END").toString())+"</Value>");
					writer.println("   			<Value Name=\"MacAddress\">"+(ipvodPurchase.get("MAC_ADDRESS") != null ? ipvodPurchase.get("MAC_ADDRESS") : "00:00:00:00:00:00")+"</Value>");
					writer.println("   			<Value Name=\"OfferingExpirationDate\">"+getFormattedDate(ipvodPurchase.get("VALID_UNTIL").toString())+"</Value>");
					writer.println("   			<Value Name=\"OfferingType\">MOD</Value>");
					writer.println("    		<Value Name=\"Product\">"+ipvodPurchase.get("PRODUCT")+"</Value>");
					writer.println("    		<Value Name=\"Provider\">"+ipvodPurchase.get("NAME")+"</Value>");
					writer.println("    		<Value Name=\"ProviderId\">"+ipvodPurchase.get("PROVIDER_ID")+"</Value>");
					writer.println("    		<Value Name=\"ServiceId\">"+ipvod+"</Value>");
					writer.println("    		<Value Name=\"StartDate\">"+getFormattedDate(ipvodPurchase.get("LICENSE_WINDOW_START").toString())+"</Value>");
					writer.println("		</Metadata>");
					
				} catch(Exception e) {
					System.out.println("<Error>" + e.getMessage() + "</Error>");
				}
				
				writer.println("	</ChargingDataRecord>");
			
			}
			
			writer.println("</ChargingDataRecords>");
			writer.close();
		}
	}
	
	private static String getFormattedDate(String date) {
		
		String [] x = date.replaceAll("-| |:|\\.", ",").split(",");
		
		GregorianCalendar calendar = new GregorianCalendar();
		
		if(x.length > 0)
			calendar.set(Calendar.YEAR, Integer.parseInt(x[0]));
		if(x.length > 1)
			calendar.set(Calendar.MONTH, Integer.parseInt(x[1]));
		if(x.length > 2)
			calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(x[2]));
		if(x.length > 3)
			calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(x[3]));
		if(x.length > 4)
			calendar.set(Calendar.MINUTE, Integer.parseInt(x[4]));
		if(x.length > 5)
			calendar.set(Calendar.SECOND, Integer.parseInt(x[5]));
		if(x.length > 6)
			calendar.set(Calendar.MILLISECOND, Integer.parseInt(x[6]));
		
	
		Format formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		String formattedDate = formatter.format(calendar.getTime());
		return formattedDate;
		
	}
}
