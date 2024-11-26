package flight;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class flight_reservation {
	 Properties conpro;
	 WebDriver driver;
	@BeforeMethod
	public  void setup() throws Throwable
	{
		conpro=new Properties();
		conpro.load(new FileInputStream("C:\\Users\\HP\\eclipse-workspace\\live_project\\Flight_reservations\\src\\flights_reg.properties\\Flights.properties"));
		driver=new ChromeDriver();
		driver.get(conpro.getProperty("url"));
		driver.findElement(By.xpath(conpro.getProperty("objuser"))).sendKeys(conpro.getProperty("username"));
		driver.findElement(By.xpath(conpro.getProperty("objpass"))).sendKeys(conpro.getProperty("password"));
		driver.findElement(By.xpath(conpro.getProperty("objsignin"))).click();
	}
	@Test
	public  void reservation() throws Throwable {
		driver.findElement(By.xpath(conpro.getProperty("objdate"))).sendKeys(conpro.getProperty("date"));
		new Select(driver.findElement(By.xpath(conpro.getProperty("objfrom")))).selectByVisibleText(conpro.getProperty("from"));
		new Select(driver.findElement(By.xpath(conpro.getProperty("objto")))).selectByVisibleText(conpro.getProperty("to"));
		driver.findElement(By.xpath(conpro.getProperty("objsearch"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(conpro.getProperty("objselect"))).click();
		driver.findElement(By.xpath(conpro.getProperty("objname"))).sendKeys(conpro.getProperty("name"));
		driver.findElement(By.xpath(conpro.getProperty("objclass"))).click();
		driver.findElement(By.xpath(conpro.getProperty("objticket"))).sendKeys(conpro.getProperty("ticket"));
		driver.findElement(By.xpath(conpro.getProperty("objinsert"))).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(conpro.getProperty("objveiw"))).click();
		Thread.sleep(2000);
		String url=driver.getCurrentUrl();
		System.out.println(url);
		if(url.contains(url))
		{
			System.out.println("test case pass");
		}
		else
		{
			System.out.println("test case fail");
		}
		TakesScreenshot ts=(TakesScreenshot)driver;
		File src=ts.getScreenshotAs(OutputType.FILE);
		File trg= new File("C:\\Users\\HP\\eclipse-workspace\\live_project\\Flight_reservations\\src\\flights_reg.properties\\unit.png");
		FileHandler.copy(src, trg);
	}
	@AfterMethod
	public  void teardown()
	{
		driver.quit();
	}

}
