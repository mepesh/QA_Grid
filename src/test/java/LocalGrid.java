import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.UnexpectedException;

public class LocalGrid {
    WebDriver driver;

    @BeforeClass
    public void SetupLocalGrid() throws MalformedURLException {
        String url = "http://localhost:4444/wd/hub";
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.WIN10);
        cap.setBrowserName("chrome");
        driver = new RemoteWebDriver(new URL(url), cap);
    }
    @Test
    public void LoginUser(){
        driver.get("https://automationexercise.com/");
        WebElement home = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[1]/a"));
        String homeContent = home.getText();
        Assert.assertEquals(homeContent,"Home");

        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[4]/a")).click();

        WebElement welcome = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/h2"));
        String wc = welcome.getText();
        System.out.println(wc);
        Assert.assertEquals(wc,"Login to your account");

        WebElement email = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[2]"));
        email.sendKeys("dipesh.pandey42@gmail.com");

        WebElement password = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/input[3]"));
        password.sendKeys("dipesh");

        driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div[1]/div/form/button")).click();

        // WebElement verify = driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[10]/a"));
        // Assert.assertEquals(verify.getText(),"Logged in as dipesh");

        driver.findElement(By.xpath("//*[@id=\"header\"]/div/div/div/div[2]/div/ul/li[5]/a")).click();

        // WebElement deleteverify = driver.findElement(By.xpath("//*[@id=\"form\"]/div/div/div/h2"));
        // Assert.assertEquals(deleteverify.getText(),"ACCOUNT DELETED!");
    }
    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
