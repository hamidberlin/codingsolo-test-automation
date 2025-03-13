package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumTestForm1Page;

@RunWith(Parameterized.class)
public class TestForm1ParameterizedSeleniumFirefox {
	
	WebDriver driver;
	
	String username;
	String userpassword;
	String betreff;
	String name;
	String kursTitel;
	int[] firmenBox1;
	int[] firmenBox2;
	String assert1;
	String assert2;
	
	
	
	public TestForm1ParameterizedSeleniumFirefox(String testName, String username, String userpassword, String betreff, String name,
			String kursTitel, int[] firmenBox1, int[] firmenBox2, String assert1, String assert2) {
		super();
		this.username = username;
		this.userpassword = userpassword;
		this.betreff = betreff;
		this.name = name;
		this.kursTitel = kursTitel;
		this.firmenBox1 = firmenBox1;
		this.firmenBox2 = firmenBox2;
		this.assert1 = assert1;
		this.assert2 = assert2;
	}


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
		
		loginPage.zugangsdatenEingeben(username , userpassword);
		loginPage.loginButtonAnklicken();
		
		// Navigation zum Formular
		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();
		
		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testForm1Anklicken();
		
		// Starte Formular
		SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
		testForm1Page.betreffEingeben(betreff);
		testForm1Page.nameEingeben(name);
		
		testForm1Page.kursAuswaehlen(kursTitel);
	
		testForm1Page.firmaInBox1Auswaehlen(firmenBox1);
		testForm1Page.firmenUerbernehmen();
		
		testForm1Page.firmaInBox2Auswaehlen(firmenBox2);
		testForm1Page.ausgewählteFirmenNachObenVerschieben();
		

		// Act
		testForm1Page.formularSpeichern();
	
		// Assert
		
		String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
		assertTrue(erfolgsMeldung.contains(assert1));
		
		String erstesElement = testForm1Page.erstesListenElementAuslesen();
		assertEquals(erstesElement, assert2);
	}
	
	@Parameters(name = "0")
	public static Collection<Object[]> provideTestData() throws Exception {
		Collection<Object[]> collection;
		
		Object[][] daten = {
				{"Test Form 1 Test 1 Firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 1", "Dieter", 
					"Selenium Automatisierung mit Dieter", new int[] {2, 4, 6}, new int[] {2}, 
					"Kurs Selenium Automatisierung", "Magazzini Alimentari Riuniti"},
				{"Test Form 1 Test 2 Firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 2", "Anna", 
						"Java Grundlagen Kurs mit Dieter", new int[] {2, 4, 6}, new int[] {2}, 
						"Java Grundlagen Kurs", "Magazzini Alimentari Riuniti"}
		};
		
		List<Object[]> listObjects = Arrays.asList(daten);
		collection = new ArrayList<Object[]>(listObjects);
		
		return collection;
		
	}
	
}
