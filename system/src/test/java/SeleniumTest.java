import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {
    public static void main(String[] args) {
        WebDriver driver;
        driver = new ChromeDriver();
        driver.navigate().to("http://www.baidu.com");
    }
}
