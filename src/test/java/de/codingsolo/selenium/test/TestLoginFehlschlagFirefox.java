package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestLoginFehlschlagFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		//driver.close();
	}

	@Test
	public void testFehlLogin() {
		System.out.println("Starte Test Login mit Fehlschlag");
		//Aufbau eine UnitTests
		
		//Arrange
		driver.findElement(By.id("__ac_name")).sendKeys("Benutzer");
		driver.findElement(By.name("__ac_password")).sendKeys("test");
		
		//Act
		driver.findElement(By.xpath("//input[@value=\'Anmelden\']")).click();
		
		//Assert
		String fehlerMeldung = driver.findElement(By.cssSelector("div.portalMessage:nth-child(1)")).getText();
		assertTrue(fehlerMeldung.contains("Anmeldung fehlgeschlagen."));
	}

}
