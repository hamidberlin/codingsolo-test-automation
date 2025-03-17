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
                    
        // Arrange: Testumgebung vorbereiten
        
        // Login-Seite initialisieren und anmelden
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben("selenium42", "R5vxI0j60");
        loginPage.loginButtonAnklicken();
        
        // Navigation zur WebElemente-Testseite
        SeleniumHomePage homePage = new SeleniumHomePage(driver);
        homePage.btnMenuAusklappen(); // Menü öffnen
        homePage.seleniumTestLinkAnklicken(); // Link zur Testseite anklicken

        SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
        testAppPage.webElementeBeispielAnklicken(); // Link zur WebElemente-Seite anklicken
        
        SeleniumWebElementePage webElemente = new SeleniumWebElementePage(driver);
        
        // Act: Testaktionen durchführen
        webElemente.checkBox1Anklicken(); // Erste Checkbox anklicken
        webElemente.checkBox1Anklicken(); // Erste Checkbox erneut anklicken (zurücksetzen)
        
        webElemente.checkBox2Anklicken(); // Zweite Checkbox aktivieren
        webElemente.checkBox3Anklicken(); // Dritte Checkbox aktivieren
        
        // Assert: Überprüfung der Checkbox-Status
        assertEquals(webElemente.checkBox1StatusAuslesen(), false); // Erste Checkbox sollte deaktiviert sein
        assertEquals(webElemente.checkBox2StatusAuslesen(), true);  // Zweite Checkbox sollte aktiviert sein
        assertEquals(webElemente.checkBox3StatusAuslesen(), true);  // Dritte Checkbox sollte aktiviert sein
    }
}