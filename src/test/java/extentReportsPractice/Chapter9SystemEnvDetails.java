package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Chapter9SystemEnvDetails {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();
		File f = new File("report.html");

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(f);

		extentReports.attachReporter(extentSparkReporter);

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();

		// getting the version of driver
		Capabilities capabilties = ((RemoteWebDriver) driver).getCapabilities();
		System.out.println(capabilties.getBrowserName());
		System.out.println(capabilties.getBrowserVersion());

		// System.getProperties().list(System.out);
		System.out.println(System.getProperty("os.name"));
		System.out.println(System.getProperty("os.version"));
		System.out.println(System.getProperty("java.version"));

		extentReports.setSystemInfo("OS", System.getProperty("os.name"));
		extentReports.setSystemInfo("OS Version", System.getProperty("os.version"));
		extentReports.setSystemInfo("Browser Version", capabilties.getBrowserVersion());
		extentReports.setSystemInfo("Java Version", System.getProperty("java.version"));
		extentReports.setSystemInfo("App URL", "URL of app from properties file");
		extentReports.setSystemInfo("Password", "1345");
		driver.quit();

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

}
