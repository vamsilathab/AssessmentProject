package testcases;
import factory.BuildFactory;
import pages.FBPage;
import pages.WHHomePage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;

import static utility.AppUtils.getActions;

import java.io.IOException;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class FBLogin {

	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;
	
		@BeforeMethod
		public void setUp() {
			report = new ExtentReports("./Reports/NewReport.html", true);
			logger = report.startTest("Driver to launch browser");
			driver = AppUtils.getDriver();
			logger.log(LogStatus.PASS, "Browser launched");			
		}
	   
		@Test(groups = {"CRITICAL"}, priority=1, description="Verify User is login into FB")
		public void verifyFBLogin() throws Exception {
			driver.get(BuildFactory.ConfigObject().getFBApplicationUrl());
			FBPage fb=new FBPage();	
			Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getFBApplicationUrl()), 200, "Alert! Page is broken");	
			fb.login(BuildFactory.ConfigObject().getFBEmail(), BuildFactory.ConfigObject().getDecodedValue(BuildFactory.ConfigObject().getFBPassword()));
			AppUtils.sleep(10000);
			fb.clickHomeIcon();		
			logger.log(LogStatus.INFO, "FB login page Test!");
			logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
		}
		
		@Test(groups = {"CRITICAL"}, priority=2, description="Verify User is able create a post and submit in FB")
		public void verifySubmitPost() throws Exception {
			String post = "Hello World";
			FBPage fb=new FBPage();	
			fb.writeAPost(post);
			fb.submitAPost();
			Assert.assertTrue(fb.verifyPost(post));
			logger.log(LogStatus.INFO, "FB post Test!");
			logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
		}
	    
		 @AfterTest public void closeTest() throws IOException {
			 AppUtils.quitDriver(); 
		  }

}
