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
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumTestForm1Page;

public class TestNavigationCodingSoloSeleniumFirefox {
	
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
	public void testNavigation() {
		System.out.println("Starte Test Navigation");
		//Aufbau eine UnitTests
		
		//Arrange
		//-LoginPage Webelemente
		/*
		driver.findElement(By.cssSelector("input.form-control[type='text']")).sendKeys("selenium42");
		driver.findElement(By.cssSelector("input.form-control[type='password']")).sendKeys("R5vxI0j60");
		driver.findElement(By.cssSelector("input.btn-primary")).click();	
		*/		
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
	
		
		//Act
		//-Navigation bis zum Formular
		/*
		driver.findElement(By.id("portaltab-burger-menu")).click();
		driver.findElement(By.linkText("Selenium Testapplikationen")).click();
		driver.findElement(By.linkText("Selenium Test Form1")).click();
		*/
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testForm1Anklicken();
		
		//Assert
		//-Überschrift der Formular mit der erwarteten Wert
		/*
		String erfolgsMeldung = driver.findElement(By.tagName("h1")).getText();
		assertEquals(erfolgsMeldung, "Selenium Test Form1");
		*/
		SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
		String erfolgsMeldung = testForm1Page.erstesListenElementAuslesen();
		assertEquals(erfolgsMeldung, "Selenium Test Form1");
		
	}

}
