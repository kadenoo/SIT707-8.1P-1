package web.service;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginServiceTest {

    private void sleep(long sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoginSuccess() {
        Assert.assertTrue(LoginService.login("ahsan", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginFailure_InvalidCredentials() {
        Assert.assertFalse(LoginService.login("invalid_username", "invalid_password", "1990-01-01"));
    }

    @Test
    public void testLoginFailure_NullUsername() {
        Assert.assertFalse(LoginService.login(null, "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginFailure_NullPassword() {
        Assert.assertFalse(LoginService.login("ahsan", null, "1990-01-01"));
    }

    @Test
    public void testLoginFailure_NullDOB() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", null));
    }

    @Test
    public void testLoginFailure_EmptyUsername() {
        Assert.assertFalse(LoginService.login("", "ahsan_pass", "1990-01-01"));
    }

    @Test
    public void testLoginFailure_EmptyPassword() {
        Assert.assertFalse(LoginService.login("ahsan", "", "1990-01-01"));
    }

    @Test
    public void testLoginFailure_EmptyDOB() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", ""));
    }

    @Test
    public void testLoginFailure_InvalidDOBFormat() {
        Assert.assertFalse(LoginService.login("ahsan", "ahsan_pass", "01-01-1990"));
    }

    @Test
    public void testFunctionalLogin() {
    	System.setProperty("webdriver.chrome.driver", "C:\\Users\\KADENO\\Documents\\chrome\\chromedriver-win64\\chromedriver.exe");


        WebDriver driver = new ChromeDriver();
        System.out.println("Driver info: " + driver);

        driver.navigate().to(
                "C:\\Users\\KADENO\\Documents\\chrome\\one");
        sleep(5);

        WebElement ele = driver.findElement(By.id("username"));
        ele.clear();
        ele.sendKeys("ahsan");

        ele = driver.findElement(By.id("passwd"));
        ele.clear();
        ele.sendKeys("ahsan_pass");

        ele = driver.findElement(By.cssSelector("[type=submit]"));
        ele.submit();

        sleep(5);

        String title = driver.getTitle();
        System.out.println("Title: " + title);

        Assert.assertEquals(title, "success");

        driver.close();
    }
}
