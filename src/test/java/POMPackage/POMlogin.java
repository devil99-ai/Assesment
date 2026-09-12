package POMPackage;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import basePackage.baseClass;

public class POMlogin extends baseClass{
	WebDriverWait Wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//input[@placeholder='Username']") WebElement username;
	@FindBy(xpath = "//input[@placeholder='Password']") WebElement password;
	@FindBy(xpath = "//button[normalize-space()='Login']") WebElement loginBtn;
	@FindBy(xpath = "//h6[normalize-space()='Dashboard']") WebElement dashboard;
	
	public POMlogin() {
		PageFactory.initElements(driver, this);
	}
	
	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		loginBtn.click();
		
	}
	public void validateDashboard() {
		Wait.until(ExpectedConditions.visibilityOf(dashboard));
		Assert.assertEquals(true, dashboard.isDisplayed());
		
		
	}
}
