package com.practice.seleniumintro;

import java.util.List;
import java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class EndToEnd {

//	In this code we are using Thread to wait and watch how the flow goes
	public static void main(String[] args) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter no of adult: ");
		int adult = sc.nextInt();
		System.out.println("Enter no of child: ");
		int child = sc.nextInt();
		
		
//		System.setProperty("webdriver.chrome.driver", "C:\\\\Ashutosh Selen\\\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/dropdownsPractise/");
		driver.manage().window().maximize();
		driver.findElement(By.id("ctl00_mainContent_rbtnl_Trip_0")).click();
		
		if(driver.findElement(By.id("Div1")).getDomAttribute("style").contains("0.5")) {
			System.out.println("One-Way Trip");
			Assert.assertTrue(true);
		}
		else {
			Assert.assertTrue(false);
		}
		
//		origin
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value= \"PAT\"]")).click();
		Thread.sleep(1000L);
		
//		destination
		driver.findElement(By.id("ctl00_mainContent_ddl_destinationStation1_CTXT")).click();
		driver.findElement(By.xpath("//div[@Id=\"glsctl00_mainContent_ddl_destinationStation1_CTNR\"] //a[@value=\"MAA\"]")).click();
//		Thread.sleep(2000);
		
////		selecting data
//		Thread.sleep(5000);
//
//		String targetMonth = "March";
//		String targetYear  = "2026";
//		String targetDay   = "15";
//
//		// Open calendar
//		driver.findElement(By.id("ctl00_mainContent_view_date1")).click();
//
//		// navigate to correct month and year
//		while (true) {
//		    String month = driver.findElement(By.cssSelector(".ui-datepicker-month")).getText();
//		    System.out.println("Month is:" +month);
//		    String year  = driver.findElement(By.cssSelector(".ui-datepicker-year")).getText();
//		    System.out.println("Year is:" +year);
//
//		    if (month.equalsIgnoreCase(targetMonth) && year.equals(targetYear)) {
//		        break;
//		    }
//
//		    driver.findElement(By.cssSelector(".ui-datepicker-next")).click();
//		}
//
//		// Select day
//		List<WebElement> dates = driver.findElements(By.cssSelector("a.ui-state-default"));
//
//		for (WebElement date : dates) {
//		    if (date.getText().equals(targetDay)) {
//		    System.out.println("The selected date is:" +date.getText());
//		        date.click();
//		        break;
//		    }
//		}
		
//		Selecting discount based
		System.out.println(driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());
		driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).click();
		System.out.println(driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected());
	
		Thread.sleep(2000);
		driver.findElement(By.id("divpaxinfo")).click();
		Thread.sleep(2000L);
//		driver.findElement(By.xpath("//span[@id='hrefIncAdt']")).click();
//		driver.findElement(By.id("hrefIncAdt")).click();
		for(int i = 1; i<adult; i++) {
			driver.findElement(By.id("hrefIncAdt")).click();
		}
		
		if(driver.findElement(By.cssSelector("input[id*=\"SeniorCitizenDiscount\"]")).isSelected() && child > 0) {
//			System.out.println("Sorry! Child ticket cannot be booked as you have selected Senior Citzen discount😊");
			throw new InterruptedException("Cannot book child ticket if Senior Citizen discount is on.");
		}
		else {
			while(child> 0) {
				driver.findElement(By.id("hrefIncChd")).click();
				child--;
			}
		}
		
		driver.findElement(By.id("btnclosepaxoption")).click();
		
//		Selecting Currency
//		dropdown with select tag - static dropdown
		WebElement selectDropdown  = driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency"));
		Select dropdown = new Select(selectDropdown);
		dropdown.selectByIndex(3);
		System.out.println(dropdown.getFirstSelectedOption().getText());
		
		driver.findElement(By.xpath("//span //input[@type=\"submit\"]")).click();
		System.out.println("Searched Successful");
		
		sc.close();
	}
}