package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTestApplikationenPage {
	
	private WebDriver driver;
	
	// Menu Button
	@FindBy(id = "portaltab-burger-menu")
	private WebElement btnMenu;
	
	// Link Test Form 1
	@FindBy(linkText = "Selenium Test Form1")
	private WebElement linkSeleniumTestForm1;

	public SeleniumTestApplikationenPage(WebDriver driver) {
		super();
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void btnMenuAusklappen() {
		btnMenu.click();
	}
	
	public void testForm1Anklicken() {
		linkSeleniumTestForm1.click();
	}
	
	
	

}
