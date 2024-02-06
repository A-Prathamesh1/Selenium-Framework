package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter7 {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();

		File f = new File("report.html");
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(f);

		extentReports.attachReporter(extentSparkReporter);

		// How to attach author category devices info to test
		extentReports.createTest("test 1", "test description").assignAuthor("Prathamesh").assignCategory("Smoke")
				.assignDevice("Chrome 120").pass("passed test");

		extentReports.createTest("test 2", "test description").assignAuthor("Ausekar").assignCategory("Sanity")
				.assignDevice("edge ").pass("failed test");

		extentReports.createTest("test 3", "test description").assignAuthor("Prathamesh", "Ausekar")
				.assignCategory("Smoke", "Sanity").assignDevice("edge", "FF", "chrome").fail("failed test");

		extentReports.createTest("test 4", "test description").assignAuthor(new String[] { "Prathamesh", "Ausekar" })
				.assignCategory(new String[] { "Smoke", "Sanity" }).assignDevice("edge", "FF", "chrome")
				.fail("failed test");

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

}
