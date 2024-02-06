package com.inetbanking.testCases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;

import org.apache.logging.log4j.core.LoggerContext;

public class BaseClass {
	protected ReadConfig read = new ReadConfig();

	public String APP_URL = read.getPropertyValue("APP_URL");
	public String user_name = read.getPropertyValue("user_name");
	public String password = read.getPropertyValue("password");
	public static WebDriver driver;
	public static Logger log;

	@Parameters("browser")
	@BeforeClass
	public void setUp(String browser) {
		log = LogManager.getLogger(BaseClass.class);

		LoggerContext context = (org.apache.logging.log4j.core.LoggerContext) LogManager.getContext(false);
		File file = new File(System.getProperty("user.dir") + "\\log4j2.xml");

		// this will force a reconfiguration
		context.setConfigLocation(file.toURI());

		switch (browser) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			log.info("Launched Chrome Browser");
			break;
		case "firefox":
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			log.info("Launched Firefox Browser");
			break;
		case "ie":
			System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//Drivers//IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			log.info("Launched IE Browser");
			break;
		}

		driver.get(APP_URL);

	}

//	public String getBvr() {
//		Capabilities capabilties = ((RemoteWebDriver) driver).getCapabilities();
//		return capabilties.getBrowserVersion();
//	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public static String captureScreenShot(String fileName) {
		TakesScreenshot takescreenshot = (TakesScreenshot) driver;
		File sourceFile = takescreenshot.getScreenshotAs(OutputType.FILE);
		log.info("Screenshot of failed test captured");
		File destFile = new File(fileName);

		try {
			FileUtils.copyFile(sourceFile, destFile);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return destFile.getAbsolutePath();
	}
}
