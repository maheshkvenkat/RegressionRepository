package seleniumKT;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import junit.framework.Assert;

public class UserRegistor {
	
	 WebDriver driver;
	 
	 @DataProvider
	 public Object[][] getLoginUserData() throws InvalidFormatException, IOException
	 {
		 
		 File file=new File("C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\Resources\\UserData.xlsx");
		 XSSFWorkbook wbook=new XSSFWorkbook(file);
		 XSSFSheet sheet=wbook.getSheet("Login");
		 int NoOfRows=sheet.getPhysicalNumberOfRows();
		 int NoOfCells=sheet.getRow(0).getPhysicalNumberOfCells();
		 Object[][] obj=new Object[NoOfRows][NoOfCells];
		 for(int i=0;i<NoOfRows;i++)
		 {
			 for(int j=0;j<NoOfCells;j++)
			 {
				 obj[i][j]=sheet.getRow(i).getCell(j).toString();
			 }
		 }
		 return obj;
		 
	 }
	 
	 @Test(dataProvider="getLoginUserData",groups= {"regression"})
	 public void userLogin(String fname,String lname,String email,String pwd) throws IOException, InterruptedException
	 {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.hyrtutorials.com/p/basic-controls.html");
		 driver.findElement(By.id("firstName")).sendKeys(fname);
		 driver.findElement(By.id("lastName")).sendKeys(lname);
		 driver.findElement(By.id("email")).sendKeys(email);
		 driver.findElement(By.id("password")).sendKeys(pwd);
		 driver.findElement(By.xpath("//button[@id='registerbtn']")).click();
		 Thread.sleep(5000);
		 String ActualResult=driver.findElement(By.id("msg")).getText().toString();
		 /*if(driver.findElement(By.id("msg")).getText() == "Registration is Successful")
		 {
			 System.out.print("User Resgitration is Successful");
			 
		 }*/
		 Assert.assertEquals("Registration is Successful",ActualResult);	 
	TakesScreenshot  takess=(TakesScreenshot)driver;
	File srcfile=takess.getScreenshotAs(OutputType.FILE);
	File destfile=new File("C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\Evidences\\result"+UUID.randomUUID()+".png");
	FileUtils.copyFile(srcfile, destfile);
	driver.quit();
		
	 }
	 
	@Test(groups= {"regression"})
	 public void HandleDropdownlist()
	 {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
		driver.manage().window().maximize();
		 driver.get("https://www.hyrtutorials.com/p/html-dropdown-elements-practice.html");
		 WebElement CourseName=driver.findElement(By.xpath("//select[@id='course']"));
		 Select select=new Select(CourseName);
		 select.selectByVisibleText("Dot Net");
		 select.selectByVisibleText("Java");
		// select.selectByValue("Dot Net");
		 //select.selectByValue("Java");
		 List<WebElement> options=select.getOptions();
		 System.out.println(" Options in CourseName Dropdownlist are");
		 for(int i=0;i<options.size();i++)
		 {
			 System.out.println(options.get(i).getText());
		 }
		 System.out.println("Selected Option is CourseName Dropdownlist is"+select.getFirstSelectedOption().getText());
		 driver.quit();
		 
	 }
	 
	 @Test(groups= {"sanity"})
	 public void HandleWindow() throws IOException, InterruptedException
	 {
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.hyrtutorials.com/p/window-handles-practice.html");
		 driver.findElement(By.xpath("//button[@id='newWindowBtn']")).click();
		 driver.findElement(By.xpath("//button[@id='newTabBtn']")).click();
		 System.out.println("Parent window name is "+driver.getWindowHandle().toString());
		Set<String> allowOpenWindows= driver.getWindowHandles();
		System.out.println("All Window Names are ");
		for(String window:allowOpenWindows)
		{
			System.err.println(window);
		}
		
		
		for(String window:allowOpenWindows)
		{
			if(driver.switchTo().window(window).getTitle().contains("Basic Controls"))
			{
				 driver.findElement(By.id("firstName")).sendKeys("mahesh");
				 driver.findElement(By.id("lastName")).sendKeys("Kolukondu");
				 driver.findElement(By.id("email")).sendKeys("test@gmail.com");
				 driver.findElement(By.id("password")).sendKeys("Kvm");
				 driver.findElement(By.xpath("//button[@id='registerbtn']")).click();
				
			}
		}
		 
		 driver.quit();
		 
	 }
	 
	 
	 @Test(groups= {"sanity"},invocationCount=4,successPercentage=75)
	 public void HandleMouseOverEvents()
	 {
		 
		 System.setProperty("webdriver.chrome.driver", "C:\\Users\\VENKATA MAHESH\\Desktop\\Selenium Automation KT Details\\SeleniumPractice\\drivers\\chromedriver.exe");
		 driver=new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.get("https://www.flipkart.com/");
		 Actions action=new Actions(driver);
		 WebElement Electronicz=driver.findElement(By.xpath("//span[text()='Electronics']"));
		 action.moveToElement(Electronicz).perform();
		 WebElement Bluetooth_Headphones=driver.findElement(By.xpath("//a[text()='Bluetooth Headphones']"));
		 action.moveToElement(Bluetooth_Headphones).click().perform();
		 WebElement boAt_Immortal=driver.findElement(By.cssSelector("a[title*='boAt Immortal']"));
		 if(boAt_Immortal.isDisplayed())
		 {
			 boAt_Immortal.click();
			 Set<String> allwindows=driver.getWindowHandles();
			 for(String window:allwindows)
			 {
				 if(driver.switchTo().window(window).getTitle().contains("boAt"))
				 {
					 driver.findElement(By.id("pincodeInputId")).sendKeys("500090");
					 driver.findElement(By.xpath("//span[text()='Check']")).click();
					 
				 }
			 }
		 }	 
		 
		driver.quit();
		 
	 }

	

}
