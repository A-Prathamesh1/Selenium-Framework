package extentReportsPractice;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Chapter5 {

	public static void main(String[] args) throws IOException {
		// Logging different types of information to extent report
		/*
		 * Text: Bold, Italic XML JSON Collection Data (List, Set, Map) Highlight any
		 * message Exception
		 */

		ExtentReports extentReports = new ExtentReports();

		File f = new File("report.html");

		ExtentSparkReporter sparkReport = new ExtentSparkReporter(f);

		extentReports.attachReporter(sparkReport);
		// Text: Bold, Italic
		extentReports.createTest("Test 1").log(Status.INFO, "<b>info1</b>").log(Status.INFO, "<i>info2</i>");
		// XML
		String xmlData = "<menu id=\"file\" value=\"File\">\r\n" + "  <popup>\r\n"
				+ "    <menuitem value=\"New\" onclick=\"CreateNewDoc()\" />\r\n"
				+ "    <menuitem value=\"Open\" onclick=\"OpenDoc()\" />\r\n"
				+ "    <menuitem value=\"Close\" onclick=\"CloseDoc()\" />\r\n" + "  </popup>\r\n" + "</menu>";
		// JSON
		String jsonData = "{\"menu\": {\r\n" + "  \"id\": \"file\",\r\n" + "  \"value\": \"File\",\r\n"
				+ "  \"popup\": {\r\n" + "    \"menuitem\": [\r\n"
				+ "      {\"value\": \"New\", \"onclick\": \"CreateNewDoc()\"},\r\n"
				+ "      {\"value\": \"Open\", \"onclick\": \"OpenDoc()\"},\r\n"
				+ "      {\"value\": \"Close\", \"onclick\": \"CloseDoc()\"}\r\n" + "    ]\r\n" + "  }\r\n" + "}}";

		extentReports.createTest("XML Test").info(MarkupHelper.createCodeBlock(xmlData, CodeLanguage.XML));

		extentReports.createTest("JSON Test").log(Status.INFO,
				MarkupHelper.createCodeBlock(jsonData, CodeLanguage.JSON));
		// Collection Data (List, Set, Map)
		List<String> listData = new ArrayList<String>();
		listData.add("apple");
		listData.add("mango");
		listData.add("pear");

		Map<Integer, String> mapData = new HashMap();
		mapData.put(1, "setA");
		mapData.put(2, "setB");
		mapData.put(3, "setC");

		Set<Integer> setData = mapData.keySet();

		extentReports.createTest("list test").info(MarkupHelper.createOrderedList(listData))
				.info(MarkupHelper.createUnorderedList(listData));

		extentReports.createTest("map test").info(MarkupHelper.createOrderedList(mapData))
				.info(MarkupHelper.createUnorderedList(mapData));

		extentReports.createTest("set test").info(MarkupHelper.createOrderedList(setData))
				.info(MarkupHelper.createUnorderedList(setData));

		// Highlight any message
		extentReports.createTest("Highlight log test")
				.info(MarkupHelper.createLabel("Highlighted text", ExtentColor.BLACK)).info("Non highlighted text");
		// Exception
		try {
			int i = 5 / 0;

		} catch (Exception e) {
			extentReports.createTest("EXception Test").info(e);
		}

		Throwable t = new RuntimeException("This is custom exception");
		extentReports.createTest(" Exception test2").fail(t);

		extentReports.createTest("Exception test3").fail(t);

		extentReports.flush();
		Desktop.getDesktop().browse(new File("report.html").toURI());
	}

}
