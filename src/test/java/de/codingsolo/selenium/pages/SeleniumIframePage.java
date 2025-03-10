package de.codingsolo.selenium.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumIframePage {
	
	private WebDriver driver;

	@FindBy(id = "name")
	private WebElement inputName;

	
	@FindBy(id = "alertbtn")
	private WebElement btnAlert;


	public SeleniumIframePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	public void wechselZuIframe() {
		driver.switchTo().frame("iframe");
	}
	
	public void nameEintragen(String name) {
		inputName.sendKeys(name);
	}
	
	public void alarmBtnAnklickken() {
		btnAlert.click();
	}
	
	public String alarmNachrichtAuslesen() {
		return driver.switchTo().alert().getText();
	}

	public void alarmNachrichtSchliessen() {
		driver.switchTo().alert().accept();
	}
}
