package basePackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.logging.LogManager;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

import io.opentelemetry.api.logs.Logger;
import utility.TimeUtils;

public class baseClass {
	public static Properties prop = new Properties();
	public static WebDriver driver;
	public static DesiredCapabilities dc;
	public static Logger logger;

	//step1- creating constructor of the class
	public baseClass(){
		//read the config.properties file
		try{
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\java\\environmentVariables\\config.properties");
		prop.load(file);
		}catch (FileNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		
	}
	
	//step2
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public static void initiation(String os, String br) {
		
		
			//switch(prop.getProperty("browser").toLowerCase())
			switch(br.toLowerCase())
			{
			case "chrome" : driver = new ChromeDriver(); break;
			case "edge" : driver= new EdgeDriver(); break;
			case "firefox" : driver = new FirefoxDriver(); break;
			default : System.out.println("Invalid Browser"); return;
			}	
		
		
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TimeUtils.timepage));
		driver.get(prop.getProperty("url"));
		
		
	}
	public void screenshot(String filename) {
		File file=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"\\src\\test\\java\\screenshots\\Screenshots\\"+filename+".jpg"));
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
			}
		
		}
	public static String takeScreenshot(String tname) {
		String timestamp = new SimpleDateFormat("DD-MM-YYYY:hh.mm.ss").format(new Date());
		TakesScreenshot screenshot =  (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);
		String destFilePath = System.getProperty("user.dir")+"\\"+tname+"_"+timestamp+".png";
		File destFile = new File(destFilePath);
		srcFile.renameTo(destFile);
		return destFilePath;
		
	}


}
