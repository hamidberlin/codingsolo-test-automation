package de.codingsolo.selenium.test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.Collection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import de.codingsolo.selenium.pages.*;

/**
 * Parametrisierter Selenium-Test für das Testformular 1.
 * Führt Login, Formulareingabe und Validierung mit verschiedenen Datensätzen durch.
 */
@RunWith(Parameterized.class)
public class TestForm1ParameterizedSeleniumFirefox {

    WebDriver driver;

    // Testdaten als Parameter
    String username;
    String userpassword;
    String betreff;
    String name;
    String kursTitel;
    int[] firmenBox1;
    int[] firmenBox2;
    String assert1;
    String assert2;

    /**
     * Konstruktor zur Initialisierung der Testdaten.
     */
    public TestForm1ParameterizedSeleniumFirefox(String testName, String username, String userpassword, String betreff, String name,
                                                  String kursTitel, int[] firmenBox1, int[] firmenBox2, String assert1, String assert2) {
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
     * Vorbereitungs-Methode, um den WebDriver zu starten und zur Testseite zu navigieren.
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
     * Aufräum-Methode, um den WebDriver nach dem Test zu schließen.
     */
    @After
    public void tearDown() throws Exception {
        System.out.println("Test abgeschlossen. - Aufräumen");
        driver.quit();
    }

    /**
     * Haupttestfall zur Überprüfung der Formularinteraktionen.
     */
    @Test
    public void testForm1() {
        System.out.println("Starte Test Navigation");

        // Login durchführen
        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, userpassword);
        loginPage.loginButtonAnklicken();

        // Zur Testformularseite navigieren
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

        // Validierung der Erfolgsmeldung und der Firmenliste
        String erfolgsMeldung = testForm1Page.statusMeldungAuslesen();
        assertTrue(erfolgsMeldung.contains(assert1));

        String erstesElement = testForm1Page.erstesListenElementAuslesen();
        assertEquals(erstesElement, assert2);
    }

    /**
     * Bereitstellung der Testdaten für die parametrisierten Tests.
     */
    @Parameters(name = "0")
    public static Collection<Object[]> provideTestData() {
        return Arrays.asList(new Object[][] {
            {"Test Form 1 Test 1 Firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 1", "Dieter", 
             "Selenium Automatisierung mit Dieter", new int[] {2, 4, 6}, new int[] {2}, 
             "Kurs Selenium Automatisierung", "Magazzini Alimentari Riuniti"},
            {"Test Form 1 Test 2 Firefox", "selenium42", "R5vxI0j60", "Parametrisierter Test 2", "Anna", 
             "Java Grundlagen Kurs mit Dieter", new int[] {2, 4, 6}, new int[] {2}, 
             "Java Grundlagen Kurs", "Magazzini Alimentari Riuniti"}
        });
    }
}