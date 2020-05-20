package org.flipLearn.com;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;

public class FlipLearnTest extends BaseClass{

	@Test
	public void loginTest() throws Exception {
		
		//Verify that user is on correct page
		Assert.assertTrue(driver.getTitle().equals("Fliplearn"), "User is not on correct page.");
		System.out.println("User is on Fliplearn page.");
		
		//Enter mobile number
		WebElement mobileNUmber = driver.findElement(By.cssSelector("#mobileno"));
		mobileNUmber.sendKeys("9711235995");
		System.out.println("Mobile number entered.");
		
		//Click on GO
		WebElement goButton = driver.findElement(By.cssSelector("button[class*='goBtnLoginScreen']"));
		goButton.click();
		System.out.println("Click on Go button.");
		
		//Verify OTP on mobile
		
		
		//Enter learner profile details
		try {
			//Enter Learner's Name
			WebElement LearnerName = driver.findElement(By.cssSelector("#learnersName"));
			LearnerName.sendKeys("Hitesh Khanna");
			
			//Select Class
			WebElement LearnerClass = driver.findElement(By.xpath("(//select[@id='radiusSelect' and @name='selectClassName'])[2]"));
			Select selectClass = new Select(LearnerClass);
			selectClass.selectByIndex(5);
			
			//Select Board
			WebElement board = driver.findElement(By.xpath("(//select[@name='selectBoardName'])[2]"));
			Select selectBoard = new Select(board);
			selectBoard.selectByIndex(4);
			
			//Click on Continue
			WebElement continueButton = driver.findElement(By.cssSelector("button[class*='themeButtonFliplearn']"));
			continueButton.click();
		}
		catch(Exception ex) {
			System.out.println("Learner's Profile section not present on Page. " + ex.toString());
		}
		
		//Click on Mathematics
		WebElement mathematics = driver.findElement(By.xpath("//h4[text()='Mathematics']"));
		mathematics.click();
		Assert.assertTrue(driver.findElement(By.cssSelector("div[class*='otherAstronaut'] img")).getAttribute("src").contains("math"), "Mathematics page is not open.");
		System.out.println("Mathematics page is open.");
	}
}
