package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumTestForm1Page;

public class TestForm1CodingSoloSeleniumFirefox {
	
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
	 * Testfall: Ausfüllen und Absenden von TestForm1
	 * 
	 * Ziel: Überprüfen, ob das Formular korrekt ausgefüllt, gespeichert und die Erfolgsmeldung angezeigt wird.
	 * 
	 * Schritte:
	 * 1. Login auf der Startseite mit gültigen Zugangsdaten.
	 * 2. Navigation zur Testformular-Seite.
	 * 3. Eingabe der Formulardaten:
	 *    - Betreff
	 *    - Name
	 *    - Kursauswahl
	 *    - Firmenauswahl und Verschieben in der Liste
	 * 4. Speichern des Formulars.
	 * 
	 * Erwartetes Ergebnis:
	 * - Die Statusmeldung enthält den Text "Kurs Selenium Automatisierung".
	 * - Das erste Listenelement entspricht "Magazzini Alimentari Riuniti".
	 */

	@Test
	public void testForm1() {
		System.out.println("Starte Test Navigation");

		// Arrange: 
		
		// Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();
		
		// Navigation zum Formular
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testForm1Anklicken();
		
		// Starte Formular
		SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
		testForm1Page.betreffEingeben("Automatisierte Test");
		testForm1Page.nameEingeben("Dieter");
		
		testForm1Page.kursAuswaehlen("Selenium Automatisierung mit Dieter");
	
		testForm1Page.firmaInBox1Auswaehlen(new int[] {2, 4, 6});
		testForm1Page.firmenUerbernehmen();
		
		testForm1Page.firmaInBox2Auswaehlen(new int[] {2});
		testForm1Page.ausgewählteFirmenNachObenVerschieben();
		

		// Act
		testForm1Page.formularSpeichern();
	
		// Assert
		
		String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains("Kurs Selenium Automatisierung"));
		
		String erstesElement = testForm1Page.erstesListenElementAuslesen();
		assertEquals(erstesElement, "Magazzini Alimentari Riuniti");
	}
}
