package OlamDemo.OlamTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class TestScript {
	String url=" http://52.168.31.28:8010";
	String userid="pwc";
	String password="pwc123";
	String txtUserId = "//input[@id='login-username']";
	String txtPassword = "//input[@id='login-password']";
	String btnLogin = "//button[@id='logbutton']";
	WebDriver driver;

	@Test
	public void test()
	{
		try
		{
			System.setProperty("webdriver.chrome.driver", ".\\Driver\\chromedriver.exe");


			driver = new ChromeDriver();
			String extentReportFile = System.getProperty("user.dir")
					+ "\\extentReportFile.html";
			String extentReportImage = System.getProperty("user.dir")
					+ "\\extentReportImage.png";

			//Create object of extent report and specify the report file path.
			ExtentReports extent = new ExtentReports(extentReportFile, true );

			// Start the test using the ExtentTest class object.
			ExtentTest extentTest = extent.startTest("OLAM Test",
					"Verify OLAM Login");




			extentTest.log(LogStatus.INFO, "Browser Launched");
			driver.get(url);
			driver.manage().window().maximize();
			extentTest.log(LogStatus.INFO, "Navigated to " + url);
			driver.findElement(By.xpath(txtUserId)).sendKeys(userid);
			extentTest.log(LogStatus.INFO, "User id entered");
			driver.findElement(By.xpath(txtPassword)).sendKeys(password);
			extentTest.log(LogStatus.INFO, "Password entered");
			driver.findElement(By.xpath(btnLogin)).click();
			Thread.sleep(2000);

			if(driver.getTitle().equalsIgnoreCase(""))
			{
				extentTest.log(LogStatus.PASS, "User is able to successfully login");
			}
			else
			{
				extentTest.log(LogStatus.FAIL, "User is NOT able to successfully login");
				extentTest.log(LogStatus.INFO,"Error Snapshot : "+extentTest.addScreenCapture(extentReportImage));

			}
			extent.endTest(extentTest);
			extent.flush();
			driver.quit();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}


	}

}

