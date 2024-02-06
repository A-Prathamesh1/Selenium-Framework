package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chapter6ScreenShots {
	static WebDriver driver;

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();

		File f = new File("report.html");

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(f);

		extentReports.attachReporter(sparkReport);

		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();

		driver.get("http://www.google.com/");

		String base64Code = captureScreenShot();

		String path = captureScreenShot("Google.jpg");

		extentReports.createTest("Screenshot test 1", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromBase64String(base64Code);

		extentReports.createTest("Screenshot test 2", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image");

		extentReports.createTest("Screenshot test 3", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image1")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image2")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image3")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image4");

		extentReports.createTest("Screenshot test 4", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromBase64String(base64Code, "google homepage Image");

		extentReports.createTest("Screenshot test 5", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromPath(path);

		extentReports.createTest("Screenshot test 6", "test description attching screen shot to test")
				.info("info message").addScreenCaptureFromPath(path, "google home page image1").info("info message")
				.addScreenCaptureFromPath(path, "google home page image2").info("info message")
				.addScreenCaptureFromPath(path, "google home page image3").info("info message")
				.addScreenCaptureFromPath(path, "google home page image4");

		extentReports.createTest("test 7 with log screens").info("test details")
				.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
				.fail(MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code, "image title").build());

		extentReports.createTest("test 8 with log screens").info("test details")
				.fail(MediaEntityBuilder.createScreenCaptureFromPath(path).build())
				.fail(MediaEntityBuilder.createScreenCaptureFromPath(path, "image title").build());

		extentReports.createTest("test 9 with log screens").info("test details")
				.fail("what image shows description",
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
				.fail("what image shows description",
						MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code, "image title").build());

		extentReports.createTest("test 10 with log screens").info("test details")
				.fail("what image shows description", MediaEntityBuilder.createScreenCaptureFromPath(path).build())
				.fail("what image shows description",
						MediaEntityBuilder.createScreenCaptureFromPath(path, "image title").build());

		Throwable t = new Throwable("Throwable instance");
		extentReports.createTest("test 11 with log screens").info("test details").info("this is info description")
				.fail(t, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code).build())
				.fail(t, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Code, "image title").build());

		extentReports.createTest(
				"test 12 with log screens").info("test details").info("this is description")
				.fail(t, MediaEntityBuilder.createScreenCaptureFromPath(path).build())
				.fail(t, MediaEntityBuilder.createScreenCaptureFromPath(path, "image title").build());

		extentReports.flush();
		driver.quit();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

	public static String captureScreenShot() {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		String base64Code = takescreenshot.getScreenshotAs(OutputType.BASE64);
		System.out.print("Screenshot captured successfully");
		return base64Code;

	}

	public static String captureScreenShot(String fileName) {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		File destFile = new File("./Screenshots/" + fileName);

		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.print("Screenshot captured successfully");
		return destFile.getAbsolutePath();
	}

}
