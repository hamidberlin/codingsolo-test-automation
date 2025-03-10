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
import de.codingsolo.selenium.pages.SeleniumWebElementePage;

public class TestWebElementRadioButtonSeleniumFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
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
		System.out.println("Starte Test RadioButton");
		
		
		// Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
				
		//Navigation
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();

		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.webElementeBeispielAnklicken();
				
		SeleniumWebElementePage webElemente = new SeleniumWebElementePage(driver);
			
				
		webElemente.radioButton1Anklicken();
		assertEquals(webElemente.radioButton1getAttribute("value"), "radio1");
		
		webElemente.radioButton2Anklicken();
		assertEquals(webElemente.radioButton2getAttribute("value"), "radio2");
		
		webElemente.radioButton3Anklicken();
		assertEquals(webElemente.radioButton3getAttribute("value"), "radio3");
		
		
				
	}

}
