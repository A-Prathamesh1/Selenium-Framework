package internetExplorerLaunchTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class InternetExplorerTest {

	public static void main(String[] args) {
		WebDriverManager.iedriver().setup();

		WebDriver driver = new InternetExplorerDriver();
		driver.get("http://www.google.com");

	}

}
