package com.functional;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class rightclick {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/binaries/chromedriver");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://www.macys.com");
		Thread.sleep(2000L);
		Actions action= new Actions(driver);

		WebElement R1 = driver.findElement(By.id("globalMastheadSignIn"));
		action.moveToElement(R1);
		action.contextClick(R1).build().perform();
		Thread.sleep(20000L);
		
/*		try {
			Robot robot = new Robot();
			robot.mouseMove(200, 200);
			robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
			Thread.sleep(2000L);
		    robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		    robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		    Thread.sleep(2000L);
		    robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
*/		
		driver.quit();
	    
	}

}
