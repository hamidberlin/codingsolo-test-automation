package de.codingsolo.selenium.test;

// Importiert die assertTrue-Methode für Testüberprüfungen
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

// Importiert die notwendigen JUnit- und Selenium-Bibliotheken
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

// Importiert die eigenen Konfigurations- und Page-Objekte
import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;

// Gibt an, dass die Klasse mit einem parametrisierten TestRunner ausgeführt wird
@RunWith(Parameterized.class)
public class TestLoginSeleniumFirefox {
    
    // WebDriver-Instanz für die Browsersteuerung
    private WebDriver driver;
    
    // Variablen für den Test (Browsername, Benutzername, Passwort)
    private final String browserName;
    private final String username;
    private final String password;

    /**
     * Konstruktor für die Parameterübergabe an den Test.
     * @param testName - Name des Tests (nicht verwendet, nur für die Ausgabe)
     * @param browserName - Name des Browsers, in dem der Test ausgeführt wird
     * @param username - Benutzername für den Login-Test
     * @param password - Passwort für den Login-Test
     */
    public TestLoginSeleniumFirefox(String testName, String browserName, String username, String password) {
        this.browserName = browserName;
        this.username = username;
        this.password = password;
    }

    /**
     * Die Methode wird vor jedem Testfall ausgeführt, um den WebDriver zu initialisieren und die Testumgebung vorzubereiten.
     */
    @Before
    public void setUp() {
        // Gibt in der Konsole aus, welcher Browser initialisiert wird.
        System.out.println("Initialisiere WebDriver für: " + browserName);
        
        // Holt eine WebDriver-Instanz für den angegebenen Browser (z. B. Firefox, Chrome).
        driver = DriverHelper.getDriver(browserName);
        
        // Maximiert das Browserfenster, um Probleme mit responsiven Layouts zu vermeiden.
        driver.manage().window().maximize();
        
        try {
            // Ruft die Basis-URL aus der Konfiguration ab (z. B. aus einer properties-Datei oder einer Konstanten).
            String baseUrl = Config.getBaseURL();
            
            // Prüft, ob die URL gültig ist (nicht null oder leer).
            if (baseUrl == null || baseUrl.isEmpty()) {
                throw new RuntimeException("Konfigurationsdatei ist leer oder ungültig");
            }

            // Öffnet die Webseite mit der abgerufenen URL.
            driver.get(baseUrl);
        } catch (Exception e) {
            // Falls ein Fehler auftritt (z. B. die URL kann nicht geladen werden), wird eine Exception geworfen.
            throw new RuntimeException("Fehler beim Laden der Konfiguration: " + e.getMessage(), e);
        }
    }

    /**
     * Die Methode wird nach jedem Testfall ausgeführt, um den WebDriver zu schließen und Ressourcen freizugeben.
     */
    @After
    public void tearDown() {
        // Prüft, ob der WebDriver noch existiert (nicht null).
        if (driver != null) {
            System.out.println("Test abgeschlossen - Browser schließen");
            
            // Schließt den Browser und beendet die WebDriver-Session.
            driver.quit();
        }
    }

    /**
     * Testfall für den Login-Prozess.
     * - Öffnet die Login-Seite.
     * - Gibt Benutzername und Passwort ein.
     * - Klickt auf den Login-Button.
     * - Überprüft, ob die Erfolgsmeldung „Willkommen!“ erscheint.
     */
    
    @Test
    public void testLogin() {
        System.out.println("Starte Test: Login erfolgreich");

        // Erstellt eine Instanz der Login-Seite
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        
        // Gibt die Zugangsdaten ein
        loginPage.zugangsdatenEingeben(username, password);
        
        // Klickt auf den Login-Button
        loginPage.loginButtonAnklicken();

        // Erstellt eine Instanz der Home-Seite nach dem Login
        SeleniumHomePage homepage = new SeleniumHomePage(driver);
        
        // Liest die Statusmeldung nach dem Login aus
        String erfolgsMeldung = homepage.statusMeldungAuslesen();
        
        // Überprüft, ob die Erfolgsmeldung „Willkommen!“ angezeigt wird.
        assertTrue("Login Erfolgreich!", erfolgsMeldung.contains("Willkommen!"));
    }

    /**
     * Bereitstellung der Testdaten für den parametrisierten Test.
     * Jede Zeile in der 2D-Array-List stellt einen Testfall dar.
     * @return Collection mit Testparametern (Testname, Browsername, Benutzername, Passwort)
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> provideTestData() {
        return Arrays.asList(new Object[][]{
            {"TestLogin Firefox", "firefox", "selenium42", "R5vxI0j60"}
        });
    }
}
