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

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.*;

@RunWith(Parameterized.class)
public class TestForm3ParameterizedSeleniumFirefox {

    private WebDriver driver;
    private String browsername, username, userpassword, bezeichnung, kennung, anschriftVer, strasseVer, telefonnummerVer, plzVer, ortVer;
    private String auswahl1, nachname, vorname, geburtsdatum, strassePerson, telefonnummerPerson, plzPerson, ortPerson, assert1, assert2;

    public TestForm3ParameterizedSeleniumFirefox(String testName, String browsername, String username, String userpassword,
                                                 String bezeichnung, String kennung, String anschriftVer, String telefonnummerVer, String strasseVer,
                                                 String plzVer, String ortVer, String auswahl1, String nachname, String vorname, String geburtsdatum,
                                                 String telefonnummerPerson, String strassePerson, String plzPerson, String ortPerson, String assert1,
                                                 String assert2) {
        this.browsername = browsername;
        this.username = username;
        this.userpassword = userpassword;
        this.bezeichnung = bezeichnung;
        this.kennung = kennung;
        this.anschriftVer = anschriftVer;
        this.telefonnummerVer = telefonnummerVer;
        this.strasseVer = strasseVer;
        this.plzVer = plzVer;
        this.ortVer = ortVer;
        this.auswahl1 = auswahl1;
        this.nachname = nachname;
        this.vorname = vorname;
        this.geburtsdatum = geburtsdatum;
        this.telefonnummerPerson = telefonnummerPerson;
        this.strassePerson = strassePerson;
        this.plzPerson = plzPerson;
        this.ortPerson = ortPerson;
        this.assert1 = assert1;
        this.assert2 = assert2;
    }

    /**
     * Die Methode wird vor jedem Testfall ausgeführt, um den WebDriver zu initialisieren und die Testumgebung vorzubereiten.
     */
    @Before
    public void setUp() {
        // Gibt in der Konsole aus, welcher Browser initialisiert wird.
        System.out.println("Initialisiere WebDriver für: " + browsername);
        
        // Holt eine WebDriver-Instanz für den angegebenen Browser (z. B. Firefox, Chrome).
        driver = DriverHelper.getDriver(browsername);
        
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

    @Test
    public void testForm3() {
        System.out.println("Starte Test zu TestForm3");

        SeleniumLoginPage loginPage = new SeleniumLoginPage(driver);
        loginPage.zugangsdatenEingeben(username, userpassword);
        loginPage.loginButtonAnklicken();

        SeleniumHomePage homePage = new SeleniumHomePage(driver);
        homePage.btnMenuAusklappen();
        homePage.seleniumTestLinkAnklicken();

        SeleniumTestApplikationenPage testAppPage = new SeleniumTestApplikationenPage(driver);
        testAppPage.linkTestForm3Anklicken();

        SeleniumTestForm3Page testForm3Page = new SeleniumTestForm3Page(driver);
        
        // Act
        testForm3Page.formularVersicherungBezeichnungEingaben(bezeichnung);
        testForm3Page.formularVersicherungKennungEingaben(kennung);
        testForm3Page.formularVersicherungAnschriftEingaben(anschriftVer);
        testForm3Page.formularVersicherungStrasseEingaben(strasseVer);
        testForm3Page.formularVersicherungTelefonEingaben(telefonnummerVer);        
        testForm3Page.formularVersicherungPLZEingaben(plzVer);
        testForm3Page.formularVersicherungOrtEingaben(ortVer);

        testForm3Page.selectAuswahl1Eingaben(auswahl1);

        testForm3Page.formularPersonenNachnameEingaben(nachname);
        testForm3Page.formularPersonenVornameEingaben(vorname);
        testForm3Page.formularPersonenGeburtsdatumEingaben(geburtsdatum);
        testForm3Page.formularPersonenTelefonnummerEingaben(telefonnummerPerson);
        testForm3Page.formularPersonenStrasseEingaben(strassePerson);
        testForm3Page.formularPersonenPLZEingaben(plzPerson);
        testForm3Page.formularPersonenOrtEingaben(ortPerson);

        testForm3Page.btnSubmitAnklicken();

        // Assert
        
        String erfolgsMeldung = testForm3Page.statusText1Auslesen();
		assertEquals(erfolgsMeldung, "Hallo Mustermann, Danke das du das Formular TestForm ausgefüllt hast.");
//        assertTrue(testForm3Page.statusText1Auslesen().contains(assert1));
//        assertEquals(testForm3Page.statusText2Auslesen(), assert2);
    }

    @Parameters(name = "{0}")
    public static Collection<Object[]> provideTestData() {
        return Arrays.asList(new Object[][]{
                {"TestForm3 Firefox", "firefox", "selenium42", "R5vxI0j60", "TestForm", "985566",
                        "Max-Muster-Versicherung-Strasse", "555555", "Max Strasse 1", "40255", "Hamburg",
                        "Arbeitnehmer", "Mustermann", "Max", "05.08.1980", "089-8958658", "Muster Strasse. 10",
                        "80565", "München", "Erfolgreich gesendet", "Bestätigung erhalten"},

//                {"TestForm3 Chrome", "chrome", "selenium42", "R5vxI0j60", "TestForm", "985566",
//                        "Max-Muster-Versicherung-Strasse.1", "040-555555555", "Max Strasse 1", "40255", "Hamburg",
//                        "Arbeitnehmer", "Mustermann", "Max", "05.08.1980", "089-8958658", "Muster Strasse. 10",
//                        "80565", "München", "Erfolgreich gesendet", "Bestätigung erhalten"}
        });
    }
}
