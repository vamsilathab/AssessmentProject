package testcases;
import factory.BuildFactory;
import pages.FBPage;
import pages.WHHomePage;
import utility.ApiAppUtils;
import utility.AppUtils;
import utility.Helper;

import java.io.IOException;

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
			driver.get(BuildFactory.ConfigObject().getFBApplicationUrl());
		}
	   
		@Test(groups = {"CRITICAL"}, priority=1, description="Verify User is login into FB")
		public void verifyFBLogin() throws Exception {
			FBPage fb=new FBPage();	
			Assert.assertEquals(ApiAppUtils.getCurrentStatusCode(BuildFactory.ConfigObject().getFBApplicationUrl()), 200, "Alert! Page is broken");	
			fb.login(BuildFactory.ConfigObject().getFBEmail(), BuildFactory.ConfigObject().getDecodedValue(BuildFactory.ConfigObject().getFBPassword()));
			Thread.sleep(10000);
			fb.clickHomeIcon();
			fb.writeAPost("text");
			fb.submitAPost();			
			logger.log(LogStatus.INFO, "Test_insurance_company page Test!");
			logger.log(LogStatus.INFO, logger.addScreenCapture(Helper.captuteScreenshot(driver, "validation")));
		}
	    
		 @AfterTest public void closeTest() throws IOException {
			 AppUtils.quitDriver(); 
		  }

}
