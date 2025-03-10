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
	
	/**
	 * Setzt die Testumgebung auf, initialisiert den Firefox WebDriver und öffnet die Testseite.
	 * 
	 * @throws Exception wenn die Initialisierung fehlschlägt
	 */

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/opt/homebrew/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	/**
	 * Schließt den WebDriver nach dem Test.
	 * 
	 * @throws Exception wenn das Schließen fehlschlägt
	 */
	
	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.close();
	}
	
	/**
	 * Testfall: Interaktion mit RadioButtons auf der WebElemente Beispielseite.
	 * 
	 * Schritte:
	 * 1. Login mit gültigen Zugangsdaten.
	 * 2. Navigation zur WebElemente Beispielseite.
	 * 3. Klicken auf den ersten Radio-Button und Überprüfung des Attributwerts.
	 * 4. Klicken auf den zweiten Radio-Button und Überprüfung des Attributwerts.
	 * 5. Klicken auf den dritten Radio-Button und Überprüfung des Attributwerts.
	 * 
	 * Erwartetes Ergebnis:
	 * - Der erste Radio-Button sollte nach dem Anklicken das Attribut "value" mit dem Wert "radio1" haben.
	 * - Der zweite Radio-Button sollte nach dem Anklicken das Attribut "value" mit dem Wert "radio2" haben.
	 * - Der dritte Radio-Button sollte nach dem Anklicken das Attribut "value" mit dem Wert "radio3" haben.
	 */


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
