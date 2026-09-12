package dataProvider;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import utility.XLUtilities;


public class excelDataProvider {
	@DataProvider(name="test1Data")
	public String[][] getData() throws IOException {
		String excelPath = System.getProperty("user.dir")+"\\src\\test\\java\\environmentVariables\\excel.xlsx";
		XLUtilities xu = new XLUtilities(excelPath);
		int rownum=xu.getRowCount("Sheet1");
		int colcount=xu.getCellCount("Sheet1", 1);
		String apidata[][] = new String[rownum][colcount];
		for(int i =1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apidata[i-1][j]=xu.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
		
	}
	@DataProvider(name="test2Data")
	public String[][] getUserData() throws IOException {
	    String excelPath = System.getProperty("user.dir") + "\\src\\test\\java\\environmentVariables\\excel.xlsx";
	    XLUtilities xu = new XLUtilities(excelPath);
	    int rownum = xu.getRowCount("Sheet2");
	    int colcount = xu.getCellCount("Sheet2", 1);
	    String apidata[][] = new String[rownum][colcount];
	    for (int i = 1; i <= rownum; i++) {
	        for (int j = 0; j < colcount; j++) {
	            apidata[i - 1][j] = xu.getCellData("Sheet2", i, j);
	        }
	    }
	    return apidata;
	}
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtilities xu = new XLUtilities(path);
		int rownum=xu.getRowCount("Sheet1");
		int colcount=xu.getCellCount("Sheet1", 1);
		String apidata[][] = new String[rownum][colcount];
		for(int i =1;i<=rownum;i++) {
			for(int j=0;j<colcount;j++) {
				apidata[i-1][j]=xu.getCellData("Sheet1", i, j);
			}
		}
		return apidata;
	}
	@DataProvider(name="usernames")
	public String[] getAllUsername() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//UserData.xlsx";
		XLUtilities xu = new XLUtilities(path);
		int rownum=xu.getRowCount("Sheet1");
		int colcount=xu.getCellCount("Sheet1", 1);
		String apidata[] = new String[rownum];
		for(int i =1;i<=rownum;i++) {
				apidata[i-1]=xu.getCellData("Sheet1", i, 1);
			
		}
		return apidata;
	}


}
