package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumHomePage {
	
	private WebDriver driver;
	
	// Statusmeldung
	@FindBy(css = ".portalMessage")
	private WebElement statusMeldung;
	
	// Menu Button
	@FindBy(id = "portaltab-burger-menu")
	private WebElement btnMenu;
	
	// Link aus dem Seitenmenu
	@FindBy(linkText = "Selenium Testapplikationen")
	private WebElement linkSideMenuSeleniumTestApp;

	public SeleniumHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}


	public String statusMeldungAuslesen() {
		return statusMeldung.getText();
	}
	
	public void btnMenuAusklappen() {
		btnMenu.click();
	}
	
	public void seleniumTestLinkAnklicken() {
		linkSideMenuSeleniumTestApp.click();
	}
	
	

}
