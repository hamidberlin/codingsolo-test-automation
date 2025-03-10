package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;

public class TestLoginSeleniumFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.quit();
	}

	@Test
	public void testLogin() {
		System.out.println("Starte Test Login Erfolgreich");
		
		//Arrange
		
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		
		//Act
		
		loginPage.loginButtonAnklicken();
		
		//Assert
		
		SeleniumHomePage homepage= new SeleniumHomePage(driver);
		
		String erfolgsMeldung = homepage.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains("Willkommen!"));
	}

}
