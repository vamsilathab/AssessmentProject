package pages;

import factory.BuildFactory;

import static utility.AppUtils.*;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class FBPage {

	public void login(String email, String password){
		getDriver().switchTo().activeElement().sendKeys(email);
       getWebElement(FB_PASSWORD).sendKeys(password);
       getWebElement(FB_LOGINBUTTON).click();
    }

    public void clickHomeIcon() throws InterruptedException{ 
    	getActions().sendKeys(Keys.ESCAPE).build().perform();
    	sleep(5000);
    	getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.cssSelector(FB_HOMEICON)));
        getWebElement(FB_HOMEICON).click();
    }

    public boolean checkCreateAPost(){
    	getWebDriverWait().until(ExpectedConditions.elementToBeClickable(By.xpath(CREATE_POST)));
    	return getWebElement(CREATE_POST).isDisplayed();
    }

    public void writeAPost(String postText){
        if(checkCreateAPost()) {
        	getWebElement(CREATE_POST).click();
        	getWebDriverWait(20).until(ExpectedConditions.elementToBeClickable(By.xpath(POST_SELECTION)));
        	getWebElement(POST_SELECTION).click();
        	getWebElement(POST_SELECTION).sendKeys(postText);
        }
        else {
            System.out.println("Create a post popup is not displayed");
        }
    }

    public void submitAPost() throws InterruptedException{
    	getWebDriverWait(20).until(ExpectedConditions.visibilityOfElementLocated(By.xpath(POST_BUTTON)));
    	getWebElement(POST_BUTTON).click();
    	sleep(5000);
    }
    
    public boolean verifyPost(String postText) throws InterruptedException{   	 
    	getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='"+postText+"']")));
    	return getWebElement("//div[text()='"+postText+"']").isDisplayed();
    }
    

}
    
