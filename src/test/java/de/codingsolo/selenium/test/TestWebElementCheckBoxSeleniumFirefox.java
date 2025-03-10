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
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;
import de.codingsolo.selenium.pages.SeleniumWebElementePage;

public class TestWebElementCheckBoxSeleniumFirefox {
	
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
	 * Testfall: Interaktion mit Checkboxes auf der WebElemente Beispielseite.
	 * 
	 * Schritte:
	 * 1. Login mit gültigen Zugangsdaten.
	 * 2. Navigation zur WebElemente Beispielseite.
	 * 3. Klicken auf die erste Checkbox, um ihren Status zu ändern, und erneut klicken, um ihren Status zurückzusetzen.
	 * 4. Klicken auf die zweite und dritte Checkbox.
	 * 5. Überprüfung des Status der Checkboxes.
	 * 
	 * Erwartetes Ergebnis:
	 * - Die erste Checkbox sollte nach zweimaligem Klicken den Status "unchecked" (false) haben.
	 * - Die zweite Checkbox sollte nach einmaligem Klicken den Status "checked" (true) haben.
	 * - Die dritte Checkbox sollte nach einmaligem Klicken ebenfalls den Status "checked" (true) haben.
	 */


	@Test
	public void testCheckBox() {
		System.out.println("Starte Test CheckBox der WebElemente Beispielseite");
				
		//Arrange
		
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

						
		//Act
		webElemente.checkBox1Anklicken();
		webElemente.checkBox1Anklicken();
		
		webElemente.checkBox2Anklicken();
		webElemente.checkBox3Anklicken();
		
		//Assert
		assertEquals(webElemente.checkBox1StatusAuslesen(), false);
		assertEquals(webElemente.checkBox2StatusAuslesen(), true);
		assertEquals(webElemente.checkBox3StatusAuslesen(), true);
		
	}

}
