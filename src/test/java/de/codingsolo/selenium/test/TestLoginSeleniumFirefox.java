package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;

/**
 * Testfall: Erfolgreicher Login
 * 
 * Dieser Test überprüft, ob ein Benutzer sich erfolgreich mit gültigen
 * Zugangsdaten einloggen kann.
 * 
 * Vorbereitung:
 * - Die Login-Seite wird aufgerufen.
 * - Benutzername und Passwort werden eingegeben.
 * 
 * Schritte:
 * 1. Benutzername und Passwort eingeben.
 * 2. Login-Button anklicken.
 * 
 * Erwartetes Ergebnis:
 * - Die Startseite wird angezeigt.
 * - Eine Statusmeldung mit dem Text "Willkommen!" erscheint.
 * 
 * @author 
 * @version 
 * @since 
 */

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
