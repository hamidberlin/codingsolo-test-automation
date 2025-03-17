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
    WebDriver driver;

    // Testparameter
    String browsername;
    String username;
    String userpassword;
    String betreff;
    String name;
    String kursTitel;
    int[] firmenBox1;
    int[] firmenBox2;
    String assert1;
    String assert2;

    // Konstruktor für die Testparameter
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
     * Initialisiert den WebDriver und öffnet die Testseite.
     */
    @Before
    public void setUp() throws Exception {
        System.out.println("Initialisiere Webdriver");
        driver = DriverHelper.getDriver(browsername);
        driver.manage().window().maximize();
        driver.get(Config.getBaseURL());
    }

    /**
     * Schließt den WebDriver nach dem Test.
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("Test abgeschlossen. - Aufräumen");
        driver.quit();
    }

    /**
     * Testfall: Formular ausfüllen und abschicken
     */
    @Test
    public void testForm1() {
        System.out.println("Starte Test zu TestForm1");

        // Login
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, userpassword);
        loginPage.loginButtonAnklicken();

        // Navigation zur Formularseite
        SeleniumHomePage homePage = new SeleniumHomePage(driver);
        homePage.btnMenuAusklappen();
        homePage.seleniumTestLinkAnklicken();
        
        SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
        testAppPage.testForm1Anklicken();

        // Formular ausfüllen
        SeleniumTestForm1Page testForm1Page = new SeleniumTestForm1Page(driver);
        testForm1Page.betreffEingeben(betreff);
        testForm1Page.nameEingeben(name);
        testForm1Page.kursAuswaehlen(kursTitel);
        testForm1Page.firmaInBox1Auswaehlen(firmenBox1);
        testForm1Page.firmenUerbernehmen();
        testForm1Page.firmaInBox2Auswaehlen(firmenBox2);
        testForm1Page.ausgewählteFirmenNachObenVerschieben();

        // Formular speichern
        testForm1Page.formularSpeichern();

        // Überprüfung der Erfolgsnachricht
        String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
        assertTrue(erfolgsMeldung.contains(assert1));

        // Überprüfung des ersten Listenelements
        String erstesElement = testForm1Page.erstesListenElementAuslesen();
        assertEquals(erstesElement, assert2);
    }

    /**
     * Parameterdaten für den Testlauf bereitstellen
     */
    @Parameters(name = "0")
    public static Collection<Object[]> provideTestData() throws Exception {
        Object[][] daten = {
            {"Test Form 1 Test 1 Firefox:", "firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 1", "Dieter",
                "Selenium Automatisierung mit Dieter", new int[] {2, 4, 6}, new int[] {2}, "Kurs Selenium Automatisierung", "Magazzini Alimentari Riuniti"},
            {"Test Form 1 Test 2 Chrome:", "chrome", "selenium42", "R5vxI0j60", "Parametrisierter Test 2", "Anna",
                "Java Grundlagen Kurs mit Dieter", new int[] {2, 4, 6}, new int[] {2}, "Java Grundlagen Kurs", "Magazzini Alimentari Riuniti"}
        };

        return new ArrayList<>(Arrays.asList(daten));
    }
}