package com.inetbanking.utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.inetbanking.testCases.BaseClass;

public class Reporting extends BaseClass implements ITestListener {

	public ExtentReports extentReports;
	public ExtentSparkReporter sparkReporter;
	public ExtentTest logger;
	public Logger log = LogManager.getLogger(BaseClass.class);;
//	BaseClass bs = new BaseClass();

	public void onStart(ITestContext testcontext) {

		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		String repName = "Test-Report-" + timeStamp + ".html";
		extentReports = new ExtentReports();
		sparkReporter = new ExtentSparkReporter(new File(System.getProperty("user.dir") + "/test-output/" + repName));
		extentReports.attachReporter(sparkReporter);

		try {
			sparkReporter.loadJSONConfig(new File("./src/test/resources/extent-report-config.json"));
		} catch (IOException e) {
			System.out.println("IOException Thrown " + e.getMessage());
			e.printStackTrace();
		}

		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
//		extentReports.setSystemInfo("Browser Version", bs.getBvr());
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("App URL", APP_URL);
		extentReports.setSystemInfo("Username", user_name);
		extentReports.setSystemInfo("Password", password);
		extentReports.setSystemInfo("Environment", "QA");
		extentReports.setSystemInfo("User", "Prathamesh");
		extentReports.setSystemInfo("Host Name", "localhost");
		log.info("on start end");

	}

	public void onTestSuccess(ITestResult tr) {

		logger = extentReports.createTest(tr.getName()).assignAuthor("Prathamesh").assignCategory("Smoke")
				.assignDevice("Chrome 120");
		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN));

		log.info("on test success end");
	}

	public void onTestFailure(ITestResult tr) {

		logger = extentReports.createTest(tr.getName()).assignAuthor("Prathamesh").assignCategory("Smoke")
				.assignDevice("Chrome 120");
		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED));

		// get screen shot

		String screenShotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
		File f = new File(screenShotPath);
		BaseClass.captureScreenShot(screenShotPath);

		if (f.exists()) {
			try {
				logger.fail("Screenshot is below " + logger.addScreenCaptureFromPath(screenShotPath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		log.info("on test fail end");
	}

	public void onTestSkipped(ITestResult tr) {

		logger = extentReports.createTest(tr.getName()).assignAuthor("Prathamesh").assignCategory("Smoke")
				.assignDevice("Chrome 120");
		logger.log(Status.SKIP, MarkupHelper.createLabel(tr.getName(), ExtentColor.ORANGE));

		log.info("on test skip end");
	}

	public void onFinish(ITestContext testContext) {
		extentReports.flush();
		log.info("report generated");
	}

}
