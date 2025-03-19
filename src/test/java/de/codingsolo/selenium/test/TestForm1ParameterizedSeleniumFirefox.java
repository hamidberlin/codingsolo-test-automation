package de.codingsolo.selenium.test;

// Importiert benötigte JUnit- und Selenium-Bibliotheken
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.*;

// Aktiviert parametrisierten Testlauf mit JUnit
@RunWith(Parameterized.class)
public class TestForm1ParameterizedSeleniumFirefox {

    // WebDriver-Instanz für den Browser
    private WebDriver driver;

    // Testparameter für den parametrisierten Testlauf
    String browsername;
    String username;
    String userpassword;
    String betreff;
    String name;
    String kursTitel;
    int[] firmenBox1;
    int[] firmenBox2;
    String assert1; // Erwartete Erfolgsnachricht
    String assert2; // Erwarteter Wert des ersten Listenelements

    /**
     * Konstruktor für den parametrisierten Test.
     * Die Testparameter werden in der Methode `provideTestData()` definiert.
     */
    public TestForm1ParameterizedSeleniumFirefox(String testName, String browsername, String username, String userpassword, String betreff, String name,
                                                 String kursTitel, int[] firmenBox1, int[] firmenBox2, String assert1, String assert2) {
        this.browsername = browsername;
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
     * Vor jedem Testlauf wird der WebDriver initialisiert und die Testseite geöffnet.
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("Initialisiere Webdriver");
        driver = DriverHelper.getDriver(browsername); // Holt den WebDriver für den angegebenen Browser
        driver.manage().window().maximize(); // Maximiert das Browserfenster
        driver.get(Config.getBaseURL()); // Navigiert zur Basis-URL aus der Konfigurationsdatei
    }

    /**
     * Nach jedem Testlauf wird der WebDriver geschlossen.
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("Test abgeschlossen. - Aufräumen");
        driver.quit(); // Beendet die Browser-Session
    }

    /**
     * Testfall: Formular ausfüllen und abschicken.
     * 
     * Schritte:
     * 1. Anmeldung mit gültigen Zugangsdaten.
     * 2. Navigation zur Formularseite.
     * 3. Eingabe von Daten ins Formular.
     * 4. Speicherung des Formulars.
     * 5. Überprüfung der Erfolgsnachricht und des ersten Listenelements.
     */
    @Test
    public void testForm1() {
        System.out.println("Starte Test zu TestForm1");

        // **Schritt 1: Login**
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, userpassword);
        loginPage.loginButtonAnklicken();

        // **Schritt 2: Navigation zur Formularseite**
        SeleniumHomePage homePage = new SeleniumHomePage(driver);
        homePage.btnMenuAusklappen();
        homePage.seleniumTestLinkAnklicken();
        
        SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
        testAppPage.testForm1Anklicken();

        // **Schritt 3: Formular ausfüllen**
        SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
        testForm1Page.betreffEingeben(betreff); // Betreff eingeben
        testForm1Page.nameEingeben(name); // Name eingeben
        testForm1Page.kursAuswaehlen(kursTitel); // Kurs auswählen
        testForm1Page.firmaInBox1Auswaehlen(firmenBox1); // Firmen in Box 1 auswählen
        testForm1Page.firmenUerbernehmen(); // Firmen in Box 2 übernehmen
        testForm1Page.firmaInBox2Auswaehlen(firmenBox2); // Firmen in Box 2 auswählen
        testForm1Page.ausgewählteFirmenNachObenVerschieben(); // Firmen nach oben verschieben

        // **Schritt 4: Formular speichern**
        testForm1Page.formularSpeichern();

        // **Schritt 5: Überprüfung der Erfolgsnachricht**
        String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
        assertTrue(erfolgsMeldung.contains(assert1));

        // Überprüfung des ersten Listenelements
        String erstesElement = testForm1Page.erstesListenElementAuslesen();
        assertEquals(erstesElement, assert2);
    }

    /**
     * Methode zur Bereitstellung von Testdaten für den parametrisierten Test.
     * Jeder Datensatz enthält:
     * - Browsername
     * - Login-Daten
     * - Formularwerte
     * - Erwartete Ergebnisse
     */
    @Parameters(name = "{0}")
    public static Collection<Object[]> provideTestData() throws Exception {
        Object[][] daten = {
            // Testfall 1: Firefox-Browser mit Testdaten
            {"Test Form 1 Test 1 Firefox:", "firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 1", "Dieter",
                "Selenium Automatisierung mit Dieter", new int[] {2, 4, 6}, new int[] {2}, "Kurs Selenium Automatisierung", "Magazzini Alimentari Riuniti"},

//            // Testfall 2: Chrome-Browser mit Testdaten
//            {"Test Form 1 Test 2 Chrome:", "chrome", "selenium42", "R5vxI0j60", "Parametrisierter Test 2", "Anna",
//                "Java Grundlagen Kurs mit Dieter", new int[] {2, 4, 6}, new int[] {2}, "Java Grundlagen Kurs", "Magazzini Alimentari Riuniti"}
        };

        // Konvertiert das Array in eine Collection, die von JUnit verwendet wird
        return new ArrayList<>(Arrays.asList(daten));
    }
}
