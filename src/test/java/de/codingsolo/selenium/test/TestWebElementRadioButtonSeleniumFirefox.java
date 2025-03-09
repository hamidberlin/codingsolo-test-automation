package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestWebElementRadioButtonSeleniumFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.close();
	}

	@Test
	public void testRadioButton() {
		System.out.println("Starte Test Radio Button");
		//Aufbau eine UnitTests
		
		//Arrange
		WebElement inputUsername = driver.findElement(By.cssSelector("#__ac_name"));
		inputUsername.sendKeys("selenium42");
		
		
		WebElement inputPassword =driver.findElement(By.xpath("//input[@id='__ac_password']"));
		inputPassword.sendKeys("R5vxI0j60");
		
		WebElement btnLogin = driver.findElement(By.xpath("//input[@value=\'Anmelden\']"));
		btnLogin.click();
		
		//Navigation
		WebElement btnMenu = driver.findElement(By.id("portaltab-burger-menu"));
		btnMenu.click();
		
		WebElement linkSideMenuSelenium = driver.findElement(By.linkText("Selenium Testapplikationen"));
		linkSideMenuSelenium.click();
		
		WebElement linkWebElement = driver.findElement(By.linkText("Web Elemente"));
		linkWebElement.click();
		
		WebElement radioButton1 = driver.findElement(By.cssSelector(("input[value='radio1']")));
		WebElement radioButton2 = driver.findElement(By.cssSelector(("input[value='radio2']")));
		WebElement radioButton3 = driver.findElement(By.cssSelector(("input[value='radio3']")));
		
				
		//Act
		radioButton1.click();
		assertEquals(driver.findElement(By.cssSelector("input:checked")).getAttribute("value"), "radio1");
		
		radioButton2.click();
		assertEquals(driver.findElement(By.cssSelector("input:checked")).getAttribute("value"), "radio2");
		
		radioButton3.click();
		assertEquals(driver.findElement(By.cssSelector("input:checked")).getAttribute("value"), "radio3");
		
		
		//Assert
		
	}

}
