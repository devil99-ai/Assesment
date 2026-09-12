package POMPackage;

import java.lang.foreign.AddressLayout;
import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import basePackage.baseClass;

public class POMregisterUser extends baseClass{
	WebDriverWait Wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//span[normalize-space()='PIM']") WebElement pimLink;
	@FindBy(xpath = "//button[normalize-space()='Add']") WebElement addUser;
	@FindBy(xpath = "//input[@placeholder='First Name']") WebElement firstname;
	@FindBy(xpath = "//input[@placeholder='Last Name']") WebElement lastname;
	@FindBy(css = ".oxd-form-loader") WebElement formLoader;
	@FindBy(xpath = "//label[normalize-space()='Employee Id']/following::input[1]") WebElement empId;
	@FindBy(xpath = "//input[@type='file']") WebElement addImage;
	@FindBy(xpath = "//button[normalize-space()='Save']") WebElement addBtn;
	@FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']") WebElement successBanner;
	
	
	
	public POMregisterUser() {
		PageFactory.initElements(driver,this);
	}
	public void opePIM() {
		pimLink.click();
	}
	public void addUser(String firstName,String lastName,String employeeId,String imagePath) {
		addUser.click();
		Wait.until(ExpectedConditions.invisibilityOf(formLoader));
		firstname.sendKeys(firstName);
		lastname.sendKeys(lastName);
		empId.click();
		empId.sendKeys(Keys.CONTROL, "a");
		empId.sendKeys(employeeId);
		addImage.sendKeys(imagePath);
		addBtn.click();
		
		
		
	}
	public void verifyUser(String firstName) throws InterruptedException {
		Wait.until(ExpectedConditions.visibilityOf(successBanner));
	    if(successBanner.isDisplayed()) {
	    Assert.assertTrue(true,"User record was found");
	    Thread.sleep(5000);
	    }
		
	}

}
