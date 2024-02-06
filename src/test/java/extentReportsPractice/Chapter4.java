package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter4 {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();

		File f = new File("report.html");

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(f);

		extentReports.attachReporter(sparkReport);
		//Precedence of logging levels
		/*
		 * fail
		 * skip
		 * warning
		 * pass
		 * info */
		extentReports.createTest("Test 1").log(Status.INFO, "info1");

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
	}

}
