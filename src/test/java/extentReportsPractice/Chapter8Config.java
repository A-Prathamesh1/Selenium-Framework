package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter8Config {

	public static void main(String[] args) throws IOException {
		ExtentReports extentReports = new ExtentReports();
		File f = new File("report.html");

		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(f);
		/*
		 * ExtentSparkReporterConfig config = extentSparkReporter.config();
		 * 
		 * config.setTheme(Theme.DARK); config.setReportName("Name of the report");
		 * config.setDocumentTitle("Title of document");
		 * config.setTimeStampFormat("dd-mm-yyyy hh:mm:ss");
		 * config.setCss(".badge-primary{background-color:#1567df}"); config.setJs(
		 * "document.querySelector(\"body > div > div > div.header.navbar > div > div > a > div\").style.display = 'none';"
		 * );
		 */
//		extentSparkReporter.loadJSONConfig(new File("./src/test/resources/extent-report-config.json"));
		extentSparkReporter.loadXMLConfig("./src/test/resources/extent-report-config.xml");
		extentReports.attachReporter(extentSparkReporter);

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());

	}

}
