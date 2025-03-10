package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TestLoginSeleniumChrome {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.chrome.driver", "/opt/homebrew/bin/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
//		driver.close();
	}

	@Test
	public void tesLogin() {
		System.out.println("Starte Test Login Erfolgreich");
		
		//Arrange
				
		driver.findElement(By.cssSelector("#__ac_name")).sendKeys("selenium42");
		driver.findElement(By.xpath("//input[@id='__ac_password']")).sendKeys("R5vxI0j60");
		
		//Act
		
		driver.findElement(By.xpath("//input[@value=\'Anmelden\']")).click();
		
		//Assert
		
		String willkommenMeldung = driver.findElement(By.cssSelector(".portalMessage")).getText();
		assertTrue(willkommenMeldung.contains("Willkommen!"));
	}

}
