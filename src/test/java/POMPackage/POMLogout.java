package POMPackage;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import basePackage.baseClass;

public class POMLogout extends baseClass {

	WebDriverWait Wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//input[@placeholder='Username']") WebElement username;
	@FindBy(xpath = "//span[@class='oxd-userdropdown-tab']") WebElement dropdown;
	@FindBy(xpath = "//a[normalize-space()='Logout']") WebElement logoutBtn;
	
	public POMLogout() {
		PageFactory.initElements(driver, this);
	}
	public void logout() {
		dropdown.click();
		logoutBtn.click();
		Wait.until(ExpectedConditions.visibilityOf(username));
		if(username.isDisplayed()) {
			Assert.assertTrue(true, "Logout Successfully");
		}
		
	}

}
