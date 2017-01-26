package com.functional;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

public class ActionsWeb extends DriverScript{
	
	public static void openBrowser(String browser){
		
		if(browser.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/binaries/chromedriver");
			driver = new ChromeDriver();
		}else if(browser.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/binaries/geckodriver");
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}else{
			System.out.println("browser not supported");
			System.exit(0);
		}
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	public static void navigate(String url){
		driver.get(url);
	}
	
	public static void sendkeys(String locator, String data){
		driver.findElement(By.id(locator)).sendKeys(data);
		
		if(locator.startsWith("//")){
			driver.findElement(By.xpath(locator)).clear();
			driver.findElement(By.xpath(locator)).sendKeys(data);
		}else if((locator.contains("#")) || (locator.contains("."))){
			driver.findElement(By.cssSelector(locator)).clear();
			driver.findElement(By.cssSelector(locator)).sendKeys(data);
		}else{
			driver.findElement(By.id(locator)).clear();
			driver.findElement(By.id(locator)).sendKeys(data);
		}
	}
	

	public static void click(String locator){
		

		if(locator.startsWith("//"))
			driver.findElement(By.xpath(locator)).click();
		else if((locator.contains("#")) || (locator.contains(".")))
			driver.findElement(By.cssSelector(locator)).click();
		else
			driver.findElement(By.id(locator)).click();
		
		
		
	}
	
	public static void dropDown(String locator, String data){
		
		Select dropdown;
		if(locator.startsWith("//"))
			dropdown = new Select(driver.findElement(By.id(locator)));
		else if((locator.contains("#")) || (locator.contains(".")))
			dropdown = new Select(driver.findElement(By.id(locator)));
		else
			dropdown = new Select(driver.findElement(By.id(locator)));
		

		dropdown.selectByVisibleText(data);
	}
	
	public static String getText(String locator){
		String actual_text = driver.findElement(By.cssSelector(locator)).getText();
		return actual_text;
	}
	
	
	public static void pause(int time){
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {

		}
	}
	
	public static void quit(){
		driver.quit();
	}



}
