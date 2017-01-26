package com.functional;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class vijaytest extends DriverScript {

//	public static void main(String[] args) {
	
	@BeforeClass
	public static void openURL() {
		ActionsWeb.openBrowser("chrome");
		ActionsWeb.navigate("http://www.macys.com");

	//	ActionsWeb.click("#globalMastheadSignIn");
		
	}
	

	public void registerUser() {
		
		driver.findElement(By.xpath("//*[contains(text(),'SHOES')]")).click();
		ActionsWeb.pause(3);
		driver.findElement(By.xpath("//*[contains(text(),'Clarks')]")).click();
		ActionsWeb.pause(3);
		
		
/*		Actions builder = new Actions(driver);
		builder.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'SHOES')]"))).perform();
		ActionsWeb.pause(3);
		builder.moveToElement(driver.findElement(By.xpath("//*[contains(text(),'Clarks')]"))).click().perform();
		ActionsWeb.pause(3);
*/
		
/*		
		ActionsWeb.click("#createProfileContainer");
		
		ActionsWeb.sendkeys("firstName","autoFirst");		
		ActionsWeb.sendkeys("lastName", "autoLast");		
		ActionsWeb.sendkeys("email", "autoEmail6@yahoo.com");
		ActionsWeb.sendkeys("password", "autoPassword");
		ActionsWeb.dropDown("month","April");
		ActionsWeb.dropDown("day","11");
		ActionsWeb.dropDown("year","1930");
		ActionsWeb.click("#createProfileBtn");
		ActionsWeb.pause(3);
//		String act_text = ActionsWeb.getText("small.error_msg");
//		System.out.println("act_text - " + act_text);
		ActionsWeb.click("#overalyCancelBtn");
		ActionsWeb.pause(3);
		ActionsWeb.click("#globalMastheadSignIn");
		ActionsWeb.pause(3);
*/
	}
	
		
//###################Login to the App #####################		
	
	@Test
	public void testOrder() {
		registerUser();
//		loginUser(); 
//		searchItem();
//		selectItem();
//		verifyItem();
	}
	
	public  void loginUser() {
		
		ActionsWeb.sendkeys("email", "autoEmail6@yahoo.com");
		ActionsWeb.sendkeys("password", "autoPassword");
		ActionsWeb.click("#signInBtn");
		ActionsWeb.pause(3);
		
		if((driver.getPageSource()).contains("Our records show that you")){
			ActionsWeb.dropDown("snssecurityqna","In what city or town did your mother and father meet?");
			ActionsWeb.sendkeys("securityAnswer", "secret1");
			ActionsWeb.click("#verifySecurityQASubmit");
		}
		
	}
	
	
	public  void searchItem() {
		
		ActionsWeb.sendkeys("globalSearchInputField", "movado");
		
		System.out.println(driver.getPageSource());

		List<WebElement> suggestion_list = driver.findElements(By.className("suggestion"));
		System.out.println(suggestion_list.size());
		
		for(int i =0; i<suggestion_list.size();i++){
			System.out.println(suggestion_list.get(i).getText());
		}
		
		ActionsWeb.click("#subnavSearchSubmit");
	}
		

	public  void selectItem() {
		ActionsWeb.click("//*[contains(text(),'0606576')]");
	}
	
	
	public  void verifyItem() {
		ActionsWeb.assertionText(".brandNameLink", "Movado");
		driver.findElement(By.xpath("//*[contains(text(),'Add to Bag')]")).click();
		driver.findElement(By.id("btnCheckout")).click();

		ActionsWeb.pause(5);

	//	System.out.println(driver.getPageSource());
		String parentHandle = driver.getWindowHandle(); 
		driver.findElement(By.xpath("(//*[contains(@href,'/popup.ognc?popupID=')])[2]")).click();
		String promocode = null;
		for (String winHandle : driver.getWindowHandles()) {
		 //   driver.switchTo().window(winHandle);
			if(!winHandle.equalsIgnoreCase(parentHandle)){
				driver.switchTo().window(winHandle);
				promocode = driver.findElement(By.className("subHeader-text")).getText();
				promocode = promocode.replaceAll("Use promo code ", "");
				promocode = promocode.replaceAll(" in your shopping bag(.*)", "");
				promocode = promocode.trim();
				System.out.println("promocode - "+promocode);
				driver.close(); 
			}
		}

		driver.switchTo().window(parentHandle);
		
		ActionsWeb.sendkeys("promoCode", promocode);
		ActionsWeb.click("#applyPromoCode");
		
		ActionsWeb.pause(5);
		
		ActionsWeb.click("#continueCheckout");
		
		ActionsWeb.pause(5);
		
		
	}

	@AfterClass
	public static void loginOut() {
		
		
		ActionsWeb.pause(3);
		ActionsWeb.click("#globalMastheadSignIn");
		

		ActionsWeb.pause(3);
		ActionsWeb.quit();

	}

}
