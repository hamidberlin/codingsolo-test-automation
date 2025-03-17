package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import de.codingsolo.selenium.configuration.Config;
import de.codingsolo.selenium.configuration.DriverHelper;
import de.codingsolo.selenium.pages.SeleniumDragDropPage;
import de.codingsolo.selenium.pages.SeleniumHomePage;
import de.codingsolo.selenium.pages.SeleniumLoginPage;
import de.codingsolo.selenium.pages.SeleniumTestApplikationenPage;

@RunWith(Parameterized.class)
public class TestDragDropCodingSoloSeleniumFirefox {
	
	private WebDriver driver;
    private final String browserName;
	

    
	public TestDragDropCodingSoloSeleniumFirefox(String browserName) {
		this.browserName = browserName;
	}
	

    @Before
    public void setUp() {
        System.out.println("Initialisiere WebDriver für: " + browserName);
        driver = DriverHelper.getDriver(browserName);
        driver.manage().window().maximize();
        
        try {
            String baseUrl = Config.getBaseURL();
            if (baseUrl == null || baseUrl.isEmpty()) {
                throw new RuntimeException("Konfigurationsdatei ist leer oder ungültig");
            }
            driver.get(baseUrl);
        } catch (Exception e) {
            throw new RuntimeException("Fehler beim Laden der Konfiguration: " + e.getMessage(), e);
        }
    }

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.quit(); 
	}
	

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
	
	 @Parameterized.Parameters(name = "0")
	    public static Collection<Object[]> provideTestData() {
	        return Arrays.asList(new Object[][]{
	            {"firefox"}
	        });
	    }
}
