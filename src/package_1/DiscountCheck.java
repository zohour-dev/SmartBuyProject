package package_1;

import java.lang.Math;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;


public class DiscountCheck {

	public WebDriver driver;
	
	@BeforeTest
	
	public void OpenWebsite() {
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();
		
	}
	
	@Test
	
	public void CheckDiscount() {
		// Get the discount percentage
		String percentage = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[1]")).getText();
		System.out.println("===================");
		System.out.println("Discount Percentage: " + percentage);
		System.out.println("===================");
		
		// Get the old price
		String oldPrice = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[2]")).getText();
		System.out.println("===================");
		System.out.println("Old Price: " + oldPrice);
		System.out.println("===================");
		
		// Get the current price
		String currentPrice = driver.findElement(By.xpath("//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[2]/div/div[2]/div[2]/div/div/span[3]")).getText();
		System.out.println("===================");
		System.out.println("Current Price: " + currentPrice);
		System.out.println("===================");
		
		// convert all fetched strings to double
		double discountPercentageInNumber = Double.parseDouble(percentage.replace("%", "").trim());
		System.out.println("Percentage in number: " + discountPercentageInNumber);
		
		double oldPriceInNumber = Double.parseDouble(oldPrice.replace("JOD", "").trim());
		System.out.println("old price in number: " + oldPriceInNumber);
		
		double currentPriceInNumber = Double.parseDouble(currentPrice.replace("JOD", "").trim());
		System.out.println("current price in number: " + currentPriceInNumber);
		///////////////////////////////////////
		
		// Calculate the expected price after discount
		double expectedPriceAfterDiscount = oldPriceInNumber - Math.round(oldPriceInNumber * (discountPercentageInNumber / 100));
		System.out.println("===================");
		System.out.println("Expected new price: " + expectedPriceAfterDiscount);
		System.out.println("===================");
		
		// Compare the expected price with the actual one
		Assert.assertEquals(currentPriceInNumber, expectedPriceAfterDiscount);
	}
}
