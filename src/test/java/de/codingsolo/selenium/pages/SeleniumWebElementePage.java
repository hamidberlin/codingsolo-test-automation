package de.codingsolo.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SeleniumWebElementePage {
	
	private WebDriver driver;
	
	@FindBy(id = "checkBoxOption1")
	private WebElement checkBox1;
	
	@FindBy(id = "checkBoxOption2")
	private WebElement checkBox2;
	
	@FindBy(id = "checkBoxOption3")
	private WebElement checkBox3;

	public SeleniumWebElementePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void checkBox1Anklicken() {
		checkBox1.click();
	}
	
	public void checkBox2Anklicken() {
		checkBox2.click();
	}
	
	public void checkBox3Anklicken() {
		checkBox3.click();
	}
	
	public boolean checkBox1StatusAuslesen() {
		return checkBox1.isSelected();
	}
	
	public boolean checkBox2StatusAuslesen() {
		return checkBox2.isSelected();
	}
	
	public boolean checkBox3StatusAuslesen() {
		return checkBox3.isSelected();
	}

}
