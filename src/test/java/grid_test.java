import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class grid_test {
    //https://www.youtube.com/watch?v=XB550hmwzLM&t=2688s
    //https://www.youtube.com/watch?v=dfnVAAw6PV4&t=942s
    public String username = "dipesh.pandey42";
    public String accesskey = "N3wC1e34QR5CuKEq5n7PiNkATH9xYfFNpisZ4aVbRRAYHOClAe";
    public static RemoteWebDriver driver = null;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    boolean status = false;

    @BeforeClass
    public void SetGrid() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("browserName", "chrome");
        capabilities.setCapability("version", "133.0");
        capabilities.setCapability("platform", "win10"); // If this cap isn't specified, it will just get the any available one
        capabilities.setCapability("build", "newBuild");
        capabilities.setCapability("name", "New build");
        try {
            driver = new RemoteWebDriver(new URL("https://" + username + ":" + accesskey + gridURL), capabilities);
            System.out.println(driver.getCurrentUrl());
        } catch (MalformedURLException e) {
            System.out.println("Invalid grid URL");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void testSimple() throws Exception {
        System.out.println("Started");
        try {//Change it to production page
            driver.get("https://lambdatest.github.io/sample-todo-app/");
            //Let's mark done first two items in the list.
            driver.findElement(By.name("li1")).click();
            driver.findElement(By.name("li2")).click();
            // Let's add an item in the list.
            driver.findElement(By.id("sampletodotext")).sendKeys("Yey, Let's add it to list");
            driver.findElement(By.id("addbutton")).click();
            // Let's check that the item we added is added in the list.
            String enteredText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[6]/span")).getText();
            System.out.println(enteredText);
            if (enteredText.equals("Yey, Let's add it to list")) {
                status = true;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterClass
    public void tearDown() throws Exception {
        if (driver != null) {
            ((JavascriptExecutor) driver).executeScript("lambda-status=" + status);
            driver.quit();
        }
    }

}
