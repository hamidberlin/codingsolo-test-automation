package de.codingsolo.selenium.test;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TestDragDropCodingSoloSeleniumFirefox {
	
	WebDriver driver;

	@Before
	public void setUp() throws Exception {
		System.out.println("Initialisiere Webdriver");
		System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.get("https://seleniumkurs.codingsolo.de");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("Test abgeschlossen. - Aufräumen");
		driver.quit(); // Nutze quit statt close!
	}

	@Test
	public void testDragAndDrop() {
		System.out.println("Starte Test Navigation");

		// Arrange: 
		
		// Login
		driver.findElement(By.cssSelector("input.form-control[type='text']")).sendKeys("selenium42");
		driver.findElement(By.cssSelector("input.form-control[type='password']")).sendKeys("R5vxI0j60");
		driver.findElement(By.cssSelector("input.btn-primary")).click();
		
		// Navigation zum Formular
		driver.findElement(By.id("portaltab-burger-menu")).click();
		driver.findElement(By.linkText("Selenium Testapplikationen")).click();
		driver.findElement(By.linkText("Drag and Drop Beispiel")).click();
		
		// Starte Drag and Drop
		WebElement drgLogoRed = driver.findElement(By.id("red-logo"));
		WebElement drgLogoGreen = driver.findElement(By.id("green-logo"));
		
		WebElement drgLogoBlue = driver.findElement(By.id("blue-logo"));
		
		WebElement drgLogoWhite = driver.findElement(By.id("white-logo"));
		
		WebElement drpBox = driver.findElement(By.id("droppable"));
		
		Actions action = new Actions(driver);

		// Act
		
		action.dragAndDrop(drgLogoGreen, drpBox).build().perform();
		action.dragAndDrop(drgLogoGreen, drpBox).build().perform();	
		
		
		action.dragAndDropBy(drgLogoBlue, 250, 20).build().perform();
		
		action.clickAndHold(drgLogoWhite).build().perform();
		
		action.moveByOffset(250, 45).perform();
		
		action.release(drgLogoWhite).perform();
		
			
		// Assert: Erfolgsmeldung prüfen
		
		String erfolgsMeldung = driver.findElement(By.cssSelector("#droppable > p")).getText();
		assertTrue(erfolgsMeldung.contains("white-logo"));
	
	}
}
