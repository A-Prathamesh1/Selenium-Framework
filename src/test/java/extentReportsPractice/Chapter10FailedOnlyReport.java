package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter10FailedOnlyReport {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();

		ExtentSparkReporter extentSparkReporter_All = new ExtentSparkReporter("AllTests.html");

		ExtentSparkReporter extentSparkReporter_Failed = new ExtentSparkReporter("FailerTest.html");
		// Filter the failed tests
		extentSparkReporter_Failed.filter().statusFilter().as(new Status[] { Status.FAIL });

		ExtentSparkReporter extentSparkReported_SkippedAndWarning = new ExtentSparkReporter("Skiped_Warninng.html");
		// Filter the skipped and warning tests
		extentSparkReported_SkippedAndWarning.filter().statusFilter().as(new Status[] { Status.SKIP, Status.WARNING });

		extentReports.attachReporter(extentSparkReporter_All, extentSparkReporter_Failed,
				extentSparkReported_SkippedAndWarning);

		extentReports.createTest("test1", "test description").pass("passed test").assignAuthor("Prathamesh")
				.assignCategory("Smoke").assignDevice("chrome");

		extentReports.createTest("Test 2", "test desc").fail("failed test").assignCategory("Prat")
				.assignCategory("smoke");

		extentReports.createTest("test 3", "test desc").skip("Skipped tset").assignAuthor("prathamesh");

		extentReports.createTest("test 4", "test description").warning("warning test").assignAuthor("prathamesh");

		extentReports.flush();

		Desktop.getDesktop().browse(new File("AllTests.html").toURI());
		Desktop.getDesktop().browse(new File("FailerTest.html").toURI());
		Desktop.getDesktop().browse(new File("Skiped_Warninng.html").toURI());
	}

}
