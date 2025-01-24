package selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Iterator;
import java.util.Set;

public class EbayTest {
    public static void main(String[] args) throws InterruptedException {
        String ebayUrl = "https://www.ebay.com/";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get(ebayUrl);
        driver.findElement(By.xpath("//input[@id='gh-ac']")).sendKeys("book");
        driver.findElement(By.xpath("//button[@id='gh-search-btn']")).click();
        Thread.sleep(500);
        driver.findElement(By.xpath("//div[@id='srp-river-results']/ul/li[1]/div/div/following-sibling::div/a")).click();

        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        Iterator<String> I1 = s.iterator();

        while (I1.hasNext()) {

            String child_window = I1.next();
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);

                driver.findElement(By.xpath("//a[@id='atcBtn_btn_1']")).click();
                WebElement cartCount = driver.findElement(By.xpath("//span[@class='gh-cart__icon']/span"));
                int itemCount = Integer.parseInt(cartCount.getText());
                if (itemCount > 0) {
                    System.out.println("Test Passed - Item added to the cart successfully.");
                } else {
                    System.out.println("Test Failed - Cart is empty.");
                }
                driver.quit();
            }
        }
    }
}
