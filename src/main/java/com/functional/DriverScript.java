package com.functional;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class DriverScript {

	static WebDriver driver = null;
	static String browser;
	
//	public static void main(String[] args) throws IOException  {
		
	//	System.out.println(System.getProperty("user.dir")+"/config.properties");
	
		static Logger log = Logger.getLogger(com.functional.DriverScript.class.getName());
	
		@BeforeClass
		public static void setUp() throws Exception{
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"/config.properties");
			Properties config = new Properties();
			config.load(fis);
			
			browser = config.getProperty("browser");
			
			System.out.println(browser);
			
			ActionsWeb.openBrowser("chrome");
			ActionsWeb.navigate("http://www.macys.com");
			
			log.info("Hello I am inside setup method");
			
		}
		
		@Test(priority=0, enabled=true)
		public static void clickSignIn(){
			ActionsWeb.click("#globalMastheadSignIn");
			log.info("Hello I am inside signin method");
		}
		
		@Test(priority=1, enabled=false)
		public static void registerUser(){
			ActionsWeb.click("#createProfileContainer");
			ActionsWeb.sendkeys("firstName","selauto");		
			ActionsWeb.sendkeys("lastName", "autoLast");		
			ActionsWeb.sendkeys("email", "selauto1@yahoo.com");
			ActionsWeb.sendkeys("password", "autoPassword");
			ActionsWeb.dropDown("month","April");
			ActionsWeb.dropDown("day","11");
			ActionsWeb.dropDown("year","1930");
			ActionsWeb.click("#createProfileBtn");
			ActionsWeb.pause(5);
			
		}
		
		public static void screenShot(){
			
			System.out.println(Math.random());
			
			int random = (int) (Math.random() * 1000 + 1);
			System.out.println(random);
			
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			System.out.println(timestamp);
			
			try{
				Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				BufferedImage capture = new Robot().createScreenCapture(screenRect);
				ImageIO.write(capture, "bmp", new File(System.getProperty("user.dir")+"/fail"+random+".bmp"));
				ImageIO.write(capture, "bmp", new File(System.getProperty("user.dir")+"/fail"+timestamp+".bmp"));
			
			}catch(Exception e){
				
			}
		}
		
		
		@Test(priority=2)
		public static void loginUser() throws InterruptedException{
			ActionsWeb.sendkeys("email","selauto1@yahoo.com");	
			ActionsWeb.sendkeys("password","autoPassword");	
			ActionsWeb.click("#signInBtn");	
			
			log.info("Hello I am inside login method");
			
			Thread.sleep(3000L);
			if((driver.getPageSource()).contains("Create a profile now to take advantage")){
					screenShot();
			}

			if((driver.getPageSource()).contains("Our records show that you")){
				ActionsWeb.dropDown("snssecurityqna", "What was the name of your first pet?");
				ActionsWeb.sendkeys("securityAnswer","mypetname");
				ActionsWeb.click("verifySecurityQASubmit");
			}
		}

		
		@Test(priority=3, enabled=false)
		public static void search_sugList(){
			
			ActionsWeb.sendkeys("globalSearchInputField","movado");
			
			List<WebElement> suggestion_list = driver.findElements(By.className("suggestion"));
			System.out.println(suggestion_list.size());
			
			for(int i =0; i<suggestion_list.size(); i++){

			//	if(!(suggestion_list.get(i).getText()).equalsIgnoreCase("movado1"))
			//		System.out.println("faileds - " + suggestion_list.get(i).getText());
				
			}

			ActionsWeb.click("subnavSearchSubmit");

			
		}
	
		@Test(priority=4, enabled=false)
		public static void addToCart_Verify(){
			
			ActionsWeb.click("//*[contains(text(),'0606504')]");
			
			
			// ############# verify the brandname and title ##############################		
					
			/*		String expected = "Movado";
					String actual = driver.findElement(By.className("brandNameLink")).getText();
					System.out.println("actual - " + actual);
					
					if(actual.equals(expected))
						System.out.println("passed the check");
					else
						System.out.println("Brand name not matched");
			*/		
					Assert.assertEquals("Movado23", driver.findElement(By.className("brandNameLink")).getText());
					
		}

		@Test(priority=5, enabled=false)
		public static void checkoutCart() throws InterruptedException{
			Thread.sleep(5000L);
			ActionsWeb.click("//*[@data-auto='addToBag']");
			ActionsWeb.click("btnCheckout");
			
			String parentHandle = driver.getWindowHandle(); // get the current window handle
			System.out.println("parentHandle - " + parentHandle);
			
			driver.findElement(By.xpath("(//*[contains(@href,'popup.ognc?popupID=')])[1]")).click();

			String promo_code = null;
			for (String winHandle : driver.getWindowHandles()) {
				
				if(!winHandle.equals(parentHandle)){
					driver.switchTo().window(winHandle);
					
					promo_code = driver.findElement(By.className("disclaimer-text")).getText();
					promo_code = promo_code.replaceAll("(.*)then enter promo code", "");
					promo_code = promo_code.replaceAll("in the box labeled(.*)", "");
					promo_code = promo_code.trim();
					driver.close();
				}
			   System.out.println("winHandle - " + winHandle);
			}
			
			driver.switchTo().window(parentHandle);
			
			ActionsWeb.sendkeys("promoCode",promo_code);
			ActionsWeb.pause(3);
			ActionsWeb.click("applyPromoCode");

			ActionsWeb.pause(5);
			ActionsWeb.click("continueCheckout");
			
		}
		
		@AfterClass
		public static void cleaningUp(){
			
			ActionsWeb.pause(3);		
			ActionsWeb.quit();
			
		}
	

//	}
	
	
	public static void my_report(){
		
	}

}
