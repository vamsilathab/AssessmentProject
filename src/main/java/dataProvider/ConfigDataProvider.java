package dataProvider;

import java.io.FileInputStream;
import java.util.Base64;
import java.util.Properties;


public class ConfigDataProvider {
	 Properties prop;
	 FileInputStream configFile;
	
	 public ConfigDataProvider(){
		try {
			 configFile = new FileInputStream("./Config/Config.properties");
			 prop = new Properties();
			 prop.load(configFile);
		} 
		catch (Exception e) {
			System.out.println("Error details: "+e.getMessage());
		}		
	}
	
	public  String getApplicationUrl() {
		String url=prop.getProperty("TestInsurancePageUrl");
		return url;
	}
	
	public  String getFBApplicationUrl() {
		String url=prop.getProperty("FBUrl");
		return url;
	}
	
	public String getEncodedValue() {
		String encodeVal=prop.getProperty("password_encode");
		return encodeVal;
	}
	
	public String getEmail() {
		String encodeVal=prop.getProperty("email");
		return encodeVal;
	}
	
	public String getUserName() {
		return prop.getProperty("userName");	
	}
	
	public String getFBEmail() {
		String encodeVal=prop.getProperty("fb_email");
		return encodeVal;
	}
	
	public String getFBPassword() {
		String encodeVal=prop.getProperty("fb_password");
		return encodeVal;
	}
	
	
	public String getFirstName() {
		String encodeVal=prop.getProperty("firstName");
		return encodeVal;
	}
	
	public String getReviewText() {
		return prop.getProperty("reviewText");
	}
	
	public String getDecodedValue(String encodedString) {
		Base64.Decoder decoder = Base64.getDecoder();  
       return new String(decoder.decode(encodedString));
	}
	
	
			
}
