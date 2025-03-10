package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumIframePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;

public class TestIFrameSeleniumFirefox {
	
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
	 * Testfall: Interaktion mit einem iFrame.
	 * 
	 * Schritte:
	 * 1. Login mit gültigen Zugangsdaten.
	 * 2. Navigation zur iFrame-Testseite.
	 * 3. Wechsel in den iFrame.
	 * 4. Eingabe eines Namens und Auslösen eines Alerts.
	 * 5. Überprüfung der Alarmnachricht.
	 * 6. Schließen des Alerts.
	 * 
	 * Erwartetes Ergebnis:
	 * - Die Alarmnachricht enthält den eingegebenen Namen.
	 */

	@Test
	public void testIFrame() {
		System.out.println("Starte Test iFrame");
		
		// Arrange
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
		
		// Navigation
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.iframeTestAnklicken();
		
		SeleniumIframePage iFramePage = new SeleniumIframePage(driver);
		iFramePage.wechselZuIframe();

						
		// Act
		iFramePage.nameEintragen("Max");
		iFramePage.alarmBtnAnklickken();
		
	
		// Assert
		
		assertTrue(iFramePage.alarmNachrichtAuslesen().contains("Max"));
		
		iFramePage.alarmNachrichtSchliessen();

	
		
	}

}
