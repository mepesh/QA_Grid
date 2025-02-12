import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.BeforeTest;

import java.net.MalformedURLException;
import java.net.URL;

public class grid_test {

    @BeforeTest
    public void SetGrid() throws MalformedURLException {
        String url = "http://localhost:4444/wd/hub";
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setPlatform(Platform.WIN10);
        WebDriver driver = new RemoteWebDriver(new URL(url), cap);

    }

}
