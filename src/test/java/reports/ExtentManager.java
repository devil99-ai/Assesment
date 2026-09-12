package reports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {

        if (extent == null) {

            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

            String reportPath = System.getProperty("user.dir")
                    + "/reports/ExtentReport_" + timeStamp + ".html";

            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);

            spark.config().setDocumentTitle("Automation Report");
            spark.config().setReportName("Pizza Hut Automation");
            spark.config().setTheme(Theme.STANDARD);

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo("Application", "Orange Hrm");
            extent.setSystemInfo("Framework", "Selenium TestNG");
            extent.setSystemInfo("Author", "Souvik Sharma");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java", System.getProperty("java.version"));

        }

        return extent;
    }

}
