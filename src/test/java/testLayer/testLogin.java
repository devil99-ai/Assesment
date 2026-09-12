package testLayer;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Endpoints.UserEndpoint;
import POMPackage.*;
import Payload.User;
import basePackage.baseClass;
import dataProvider.excelDataProvider;
import io.restassured.response.Response;

public class testLogin extends baseClass {
	POMregisterUser user;
	POMlogin login;
	SearchUser searchuser;
	POMDeleteUser delete;
	POMLogout logout;
	
	public testLogin() {
		super();
	}
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void initSetup(@Optional("Windows") String os,@Optional("chrome") String browser) {
		//docker.dockerRun();
		initiation(os, browser);
		user = new POMregisterUser();
		login = new POMlogin();
		searchuser = new SearchUser();
		delete=new POMDeleteUser();
		logout =new POMLogout();
	
	}
	@Test(priority = 1, dataProvider = "test1Data",dataProviderClass = excelDataProvider.class)
	public void login(String username, String password) throws InterruptedException {
		login.login(username,password);

		Thread.sleep(3000);
		login.validateDashboard();
	}
	@Test(priority = 2, dataProvider = "test2Data",dataProviderClass = excelDataProvider.class)
	public void createUser(String firstName,String lastName,String employeeId,String imagePath,String jobTitle,String empStatus) throws InterruptedException {
		user.opePIM();
		user.addUser(firstName,lastName,employeeId,imagePath);
		user.verifyUser(firstName);
		searchuser.findUser(employeeId);
		searchuser.editUser(jobTitle,empStatus);
		searchuser.verifyEmployee(employeeId, jobTitle, empStatus);
		
	}
	@Test(priority = 3, dataProvider = "test2Data",dataProviderClass = excelDataProvider.class)
	public void DeleteuserFromUI(String firstName,String lastName,String employeeId,String imagePath,String jobTitle,String empStatus) {
		delete.findUser(employeeId);
		delete.deleteUser(employeeId);
		
	}
	@Test(priority = 4, dataProvider = "test2Data",dataProviderClass = excelDataProvider.class)
	public void LogoutFromUI(String firstName,String lastName,String employeeId,String imagePath,String jobTitle,String empStatus) {
		logout.logout();
		
	}
	@Test(priority = 5,dataProvider = "Data",dataProviderClass = excelDataProvider.class)
	public void APITestPostuser(String userID,String Username,String Firstname,String Lastname,String Email,String Password,String Phone) {
		User userpayload= new User();
		userpayload.setId(Integer.parseInt(userID));
		userpayload.setUsername(Username);
		userpayload.setFirstName(Firstname);
		userpayload.setLastName(Lastname);
		userpayload.setEmail(Email);
		userpayload.setPassword(Password);
		userpayload.setPhone(Phone);
		
		Response response = UserEndpoint.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	
	@Test(priority = 6,dataProvider = "usernames",dataProviderClass = excelDataProvider.class)
	public void APITestDeleteuser(String Username) {
		
		Response response = UserEndpoint.deleteUser(Username);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		
	}
	

}
