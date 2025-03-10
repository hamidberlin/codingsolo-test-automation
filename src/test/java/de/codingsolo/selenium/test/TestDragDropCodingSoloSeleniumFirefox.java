package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import de.codingsolo.selenium.pages.SeleniumDragDropPage;
import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;

public class TestDragDropCodingSoloSeleniumFirefox {
	
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
		driver.quit(); 
	}
	
	/**
	 * Testfall: Drag-and-Drop-Interaktion auf der Drag-and-Drop-Seite.
	 * 
	 * Schritte:
	 * 1. Login mit gültigen Zugangsdaten.
	 * 2. Navigation zur Drag-and-Drop-Testseite.
	 * 3. Verschieben des weißen Logos in das Zielbox.
	 * 4. Verschieben des blauen Logos in das Zielbox.
	 * 5. Verschieben des roten Logos an eine benutzerdefinierte Position.
	 * 6. Manuelles Verschieben des grünen Logos in das Zielbox.
	 * 7. Verschieben aller Logos in das Zielbox.
	 * 8. Überprüfung der Erfolgsmeldung, ob alle Logos korrekt platziert wurden.
	 * 
	 * Erwartetes Ergebnis:
	 * - Die Erfolgsmeldung enthält den Text "coding-logo", was anzeigt, dass alle Logos korrekt verschoben wurden.
	 */

	@Test
	public void testDragDrop() {
		System.out.println("Starte Test Drag and Drop");

		// Arrange

		// Login
		SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
		loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
		loginPage.loginButtonAnklicken();

		// Navigation zum Formular

		SeleniumHomePage homePage = new SeleniumHomePage(driver);
		homePage.btnMenuAusklappen();
		homePage.seleniumTestLinkAnklicken();

		SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
		testAppPage.testDragDropAnklicken();

		// Starte Drag and Drop
		SeleniumDragDropPage dragDropPage = new SeleniumDragDropPage(driver);

		// Act

		dragDropPage.moveWhiteLogoToBox();
		dragDropPage.moveBlueLogoToBox();

		dragDropPage.moveRedLogoToPoint(250, 0);

		dragDropPage.moveGreenLogoToBoxManuell();

		dragDropPage.moveAllLogosToBox();

		// Assert

		String erfolgsMeldung = dragDropPage.printStatusMessage();
		assertTrue(erfolgsMeldung.contains("coding-logo"));

	}
}
