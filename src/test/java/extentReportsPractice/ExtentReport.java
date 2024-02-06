package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();
		File f = new File("report.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(f);

		// attach reporter to engine
		extentReports.attachReporter(sparkReporter);

		ExtentTest test1 = extentReports.createTest("Test 1", null);
		test1.pass("Test 1 passed");

		ExtentTest test2 = extentReports.createTest("Test 2", null);
		test2.log(Status.FAIL, "Test 2 failed");

		extentReports.createTest("Test 3", null).skip("skipped");

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
	}

}
