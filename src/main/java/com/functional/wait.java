package com.functional;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class wait {

	public static void main(String[] args) throws InterruptedException {
		 
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/binaries/chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 
		driver.get("http://seleniumpractise.blogspot.in/2016/08/how-to-use-explicit-wait-in-selenium.html");
		driver.findElement(By.xpath("//button[text()='Click me to start timer']")).click();
		 
		
//################################ FLUENT WAIT ##########################################		
		
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(1, TimeUnit.MILLISECONDS);
		wait.withTimeout(10, TimeUnit.SECONDS);

		WebElement element = wait.until(new Function<WebDriver, WebElement>() {

           public WebElement apply(WebDriver driver){
            	 // find the element
            	 WebElement ele = driver.findElement(By.xpath("//p[@id='demo']"));
            	 
      //      	 System.out.println("attribute - " + ele.getAttribute("innerHTML"));
                 if (ele.getAttribute("innerHTML").equalsIgnoreCase("WebDriver")) {
                        System.out.println("Value matched is >>> " + ele.getAttribute("innerHTML"));
                        return ele;
                   }
                  else {
                	  System.out.println("Value is >>> " + ele.getAttribute("innerHTML"));
                	  return null;
                  }
             }
        });
		
		 System.out.println("Final visible status is >>>>> " + element.isDisplayed());
	
//################################ EXPLICIT WAIT ##########################################
		 
/*		WebDriverWait wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//p[@id='demo']"), "I am testing"));
		wait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("//xpath of the element related to records"), "some text")); 		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("myDynamicElement")));
*/
	}

}
