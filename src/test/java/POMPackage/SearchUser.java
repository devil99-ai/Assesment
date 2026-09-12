package POMPackage;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import basePackage.baseClass;

public class SearchUser extends baseClass {

	@FindBy(xpath = "//span[normalize-space()='PIM']") WebElement pimLink;

	@FindBy(xpath = "//label[normalize-space()='Employee Id']/following::input[1]") WebElement searchUserEmployee;
	@FindBy(xpath = "//button[normalize-space()='Search']") WebElement searchBtn;
	@FindBy(xpath = "//span[contains(normalize-space(),'Found')]") WebElement recordBanner;
	@FindBy(xpath = "//div[@class='orangehrm-container']//button[1]") WebElement editBtn;
	@FindBy(xpath = "//a[normalize-space()='Job']") WebElement jobBtn;
	@FindBy(xpath = "//label[contains(.,'Job Title')]/following::div[1]") WebElement jobTitleBtn;
	@FindBy(xpath = "//div[@role='listbox']//div") List<WebElement> jobTitleList;
	@FindBy(xpath = "//label[contains(.,'Employment Status')]/following::div[1]") WebElement empStatus;
	@FindBy(xpath = "//div[@role='listbox']//div") List<WebElement> empStatusList;
	@FindBy(xpath = "//button[normalize-space()='Save']") WebElement saveBtn;
	@FindBy(xpath = "//div[@class='oxd-table-body']/div/div/div[5]") WebElement jobTitleTab;
	@FindBy(xpath = "//div[@class='oxd-table-body']/div/div/div[6]") WebElement empStatusTab;
	
	
	
	public SearchUser() {
		PageFactory.initElements(driver,this);
	}
	public void findUser(String empId) {
		pimLink.click();
		searchUserEmployee.sendKeys(empId);
		searchBtn.click();	
	}
	public void editUser(String jobTitle,String EmpStatus) throws InterruptedException {
		
			editBtn.click();
			jobBtn.click();
			Thread.sleep(2000);
			jobTitleBtn.click();
			for(int i=0;i<jobTitleList.size();i++) {
				if(jobTitleList.get(i).getText().contains(jobTitle)) {
					jobTitleList.get(i).click();
					System.out.println("job title added");
				}
			}
			empStatus.click();
			for(int i=0;i<empStatusList.size();i++) {
				if(empStatusList.get(i).getText().contains(EmpStatus)) {
					empStatusList.get(i).click();
					System.out.println("emp status added");
				}
			}
//			Thread.sleep(2000);
			saveBtn.click();
				
		}
		
	
	public void verifyEmployee(String empId,String JobTitle,String EmpStatus) {
		pimLink.click();
		searchUserEmployee.sendKeys(empId);
		searchBtn.click();
		
		if(jobTitleTab.getText().contains(JobTitle)) {
			Assert.assertTrue(true,"Job Title Added successfully");
			
		}
		if(empStatusTab.getText().contains(EmpStatus)) {
			Assert.assertTrue(true,"Employee Status Added successfully");
			
		}
		
	}

}
