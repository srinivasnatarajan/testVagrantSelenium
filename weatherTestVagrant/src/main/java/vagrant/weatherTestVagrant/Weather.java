package vagrant.weatherTestVagrant;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Hello world!
 *
 */
public class Weather 
{
	public static List<WebElement> getDetailsFromWebsite(WebDriver driver) 
	{
		driver.get("https://www.ndtv.com/news");
		try {
			WebElement alert = driver.findElement(By.xpath("//a[text()='No Thanks']"));
			if(alert.isDisplayed()) {
			alert.click();
			}
		}catch (Exception e) {
			System.out.println("Alert window not found, Proceeding");
		}
		//Alert alert = driver.switchTo().alert();
		//alert.dismiss();
		
		WebElement more = driver.findElement(By.id("h_sub_menu"));
		if(more.isDisplayed() && more.isEnabled())
			more.click();
		WebElement weather = driver.findElement(By.xpath("//a[text()='WEATHER']"));
		if(weather.isDisplayed() && weather.isEnabled())
			weather.click();
		WebElement search = driver.findElement(By.className("infoHolder"));
		/*
		 * if(search.isDisplayed()) search.click(); search.sendKeys("Bengaluru");
		 */
		
		WebElement selectBangalore = driver.findElement(By.id("Bengaluru"));
		if(!selectBangalore.isSelected()) {
			selectBangalore.click();
		}
		
		List<WebElement> tempDetails = driver.findElements(By.xpath("//div[text()='Bengaluru']//../div/span"));
		
		return tempDetails;
	}
}
