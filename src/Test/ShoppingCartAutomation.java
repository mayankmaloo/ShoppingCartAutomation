package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import junit.framework.Assert;

public class ShoppingCartAutomation {

	
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		// Create a new Web Driver for Firefox Browser
		WebDriver driver = new FirefoxDriver();
		driver.get("http://www.build.com/");
		
		//Add one Suede Kohler K­6626­6U to the cart
		WebDriverWait wait = new WebDriverWait(driver, 60);
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search_txt")));
		driver.findElement(By.id("search_txt")).sendKeys("Suede Kohler K­6626­6U");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.className("addToCartWrap")).click();
		
		//Add one Cashmere Kohler K­6626­6U to the cart
		driver.findElement(By.id("headerSearchInput")).sendKeys("Cashmere Kohler K­6626­6U");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.className("addToCartWrap")).click();
		
		//Add two Kohler K­6066­ST to the cart
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement element1 = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.id("headerSearchInput")));
		driver.findElement(By.id("headerSearchInput")).sendKeys("Kohler K­6066­ST");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.id("qtyselected")).clear();
		driver.findElement(By.id("qtyselected")).sendKeys("2");
		driver.findElement(By.className("addToCartWrap")).click();
		
		//Begin Checkout Flow
		driver.findElement(By.id("checkoutButtonWrap")).click();
		
		//Assert that the CA Tax is correct
		WebElement element2 = driver.findElement(By.id("subtotalamount"));
		Double subtotalamount = Double.parseDouble(element2.getText().replaceAll("\\$","").replaceAll("\\,",""));
		Double expectedTax;
		expectedTax = Math.floor((subtotalamount*0.075)*100)/100;
		
		WebElement element3 = driver.findElement(By.id("taxAmount"));
		Double actualTax = Double.parseDouble(element3.getText().replaceAll("\\$","").replaceAll("\\,",""));
		
		Assert.assertEquals(expectedTax, actualTax);
		{
			System.out.println("The Actual Tax "+ actualTax +" and Expected Tax "+ expectedTax +" are equal");
		}
		
		//Assert that the Grand Total is correct
		Double expectedGrandTotal;
		expectedGrandTotal = Math.floor((subtotalamount + actualTax)*100)/100;
		
		WebElement element4 = driver.findElement(By.id("grandtotalamount"));
		Double grandtotalamount = Double.parseDouble(element4.getText().replaceAll("\\$","").replaceAll("\\,",""));
		
		Assert.assertEquals(expectedGrandTotal, grandtotalamount);
		{
			System.out.println("The Actual Grand Total "+ grandtotalamount +" and Expected Grand Total "+ expectedGrandTotal +" are equal");
		}
		
		//Checkout
		WebDriverWait wait2 = new WebDriverWait(driver, 60);
		WebElement element5 = wait2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("a[class='button primary icon-secure']")));
		driver.findElement(By.cssSelector("a[class='button primary icon-secure']")).click();
		
		//Guest Login
		driver.findElement(By.id("guest-login")).click();
		
		//Shipping Address 
		driver.findElement(By.id("shippingfirstname")).sendKeys("Mike");
		driver.findElement(By.id("shippinglastname")).sendKeys("Cutler");
		driver.findElement(By.id("shippingaddress1")).sendKeys("ABC Bear Paw");
		driver.findElement(By.id("shippingpostalcode")).sendKeys("92604");
		driver.findElement(By.id("shippingcity")).sendKeys("Irvine");
		
		Select select = new Select(driver.findElement(By.id("shippingstate_1")));
		select.selectByVisibleText("California");
		
		driver.findElement(By.id("shippingphonenumber")).sendKeys("5627860013");
		
		driver.findElement(By.id("emailAddress")).sendKeys("abc@gmail.com");
		
		//Payment Card Details
		driver.findElement(By.id("creditCardNumber")).sendKeys("4111111111111111");
		
		Select selectMonth = new Select(driver.findElement(By.id("creditCardMonth")));
		selectMonth.selectByVisibleText("01");
		
		Select selectYear = new Select(driver.findElement(By.id("creditCardYear")));
		selectYear.selectByVisibleText("2017");
		
		driver.findElement(By.id("creditcardname")).sendKeys("Mike Cutler");

		driver.findElement(By.id("creditCardCVV2")).sendKeys("123");
		
		//Review Order
		WebDriverWait wait3 = new WebDriverWait(driver, 60);
		WebElement reviewOrder = wait3.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[class='button primary js-checkout-review']")));
		driver.findElement(By.cssSelector("input[class='button primary js-checkout-review']")).click();
		
	}

}
