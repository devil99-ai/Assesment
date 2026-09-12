package POMPackage;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import basePackage.baseClass;

public class POMDeleteUser  extends baseClass{
	WebDriverWait Wait= new WebDriverWait(driver, Duration.ofSeconds(10));
	@FindBy(xpath = "//input[@placeholder='Username']") WebElement username;
	@FindBy(xpath = "//input[@placeholder='Password']") WebElement password;
	@FindBy(xpath = "//button[normalize-space()='Login']") WebElement loginBtn;
	@FindBy(xpath = "//span[normalize-space()='PIM']") WebElement pimLink;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/following::input[1]") WebElement searchUserEmployee;
	@FindBy(xpath = "//button[normalize-space()='Search']") WebElement searchBtn;
	@FindBy(xpath = "//span[contains(normalize-space(),'Found')]") WebElement recordBanner;
	@FindBy(xpath = "//div[@class='orangehrm-container']//button[2]") WebElement deleteBtn;
	@FindBy(xpath = "//button[normalize-space()='Yes, Delete']") WebElement confirmBtn;
	@FindBy(xpath = "//div[@class='oxd-toast-content oxd-toast-content--success']") WebElement confirmdelete;
	public POMDeleteUser() {
		PageFactory.initElements(driver,this);
	}
	
	public void login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		loginBtn.click();
		
	}
	public void findUser(String empId) {
		pimLink.click();
		searchUserEmployee.sendKeys(empId);
		searchBtn.click();	
	}
	public void deleteUser(String empId) {
		deleteBtn.click();
		confirmBtn.click();
		if(confirmdelete.isDisplayed()) {
			if(confirmdelete.getText().contains("Successfully Deleted")) {
				Assert.assertTrue(true, "User Deleted Successfully");
			}
		}
		
		
	}
}
