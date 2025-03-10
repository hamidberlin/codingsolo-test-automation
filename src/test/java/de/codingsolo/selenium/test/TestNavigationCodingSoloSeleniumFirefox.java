package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumTestForm1Page;

public class TestNavigationCodingSoloSeleniumFirefox {
	
	WebDriver driver;
	
	/**
	 * Initialisiert den WebDriver und öffnet die Testseite.
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
		driver.quit();
	}
	
	/**
	 * Testfall: Navigation durch die Applikation
	 * 
	 * Ziel: Überprüfen, ob die Navigation von der Startseite zur Testform-Seite funktioniert.
	 * 
	 * Schritte:
	 * 1. Login auf der Startseite mit gültigen Zugangsdaten.
	 * 2. Menü ausklappen und zur Testapplikationen-Seite navigieren.
	 * 3. TestForm1 aufrufen.
	 * 
	 * Erwartetes Ergebnis:
	 * - Die Überschrift auf der TestForm1-Seite lautet "Selenium Test Form1".
	 */

	@Test
	public void testNavigation() {
		System.out.println("Starte Test Navigation");
	
		
		// Arrange

		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
		
		// Act

		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testForm1Anklicken();
		
		// Assert
	
		SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
		
		String erfolgsMeldung = testForm1Page.ueberschriftAuslesen();
		assertEquals(erfolgsMeldung, "Selenium Test Form1");
		
	}

}
