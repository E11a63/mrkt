import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ieYandex {
  InternetExplorerDriver wd;

  public static boolean isAlertPresent(InternetExplorerDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @BeforeMethod
  public void setUp() throws Exception {
    //1. Открыть браузер b развернуть на весь экран
    wd = new InternetExplorerDriver();
    wd.manage().window().maximize();
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test
  public void ieYandexMarket() throws InterruptedException {
    //2. Зайти на yandex.ru
    wd.manage().window().maximize();
    wd.get("https://yandex.ru/");
    //3.	Перейти на Яндекс Маркет
    wd.findElement(By.xpath(".//a[5]")).click();
    WebDriverWait wait = new WebDriverWait(wd, 30);
    WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[1]//li[2]/a")));
    //4.	Выбрать раздел  Компьютеры html/body/div[1]/div[2]/noindex/ul/li[2]/a
    wd.findElement(By.xpath(".//div[1]//li[2]/a")).click();
    WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[4]/div[1]/div/div[1]/div/a[1]")));
    //5.	 Выбрать раздел Планшеты
    wd.findElement(By.xpath(".//div[4]/div[1]/div/div[1]/div/a[1]")).click();
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    JavascriptExecutor jse = (JavascriptExecutor) wd;
    jse.executeScript("window.scrollBy(0,100000)", "");
   //6.	Зайти в расширенный поиск
    wd.findElementByXPath(".//div[1]//div[30]/div[2]/a");
    if (!wd.findElement(By.xpath(".//div[1]//div[30]/div[2]/a")).isSelected()) {
      wd.findElement(By.xpath(".//div[1]//div[30]/div[2]/a")).click();
    }
    jse.executeScript("window.scrollBy(0,250)", "");
    wd.findElement(By.id("glf-pricefrom-var")).clear();
    // 7.	Задать параметр поиска от 20000 до 25000 рублей.
    wd.findElement(By.id("glf-pricefrom-var")).sendKeys("20000");
    wd.findElement(By.id("glf-priceto-var")).click();
    wd.findElement(By.id("glf-priceto-var")).clear();
    wd.findElement(By.id("glf-priceto-var")).sendKeys("25000");
    // 8.	Выбрать производителя Acer
    wd.findElement(By.xpath(".//div[2]/div[2]/div/div[2]/button")).click();
    wd.findElement(By.xpath(".//div[2]/div[2]/div/span/span/input")).clear();
    wd.findElement(By.xpath(".//div[2]/div[2]/div/span/span/input")).sendKeys("Acer");
    wd.findElementByXPath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label"); //
    if (!wd.findElement(By.xpath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label")).isSelected()) {
      wd.findElement(By.xpath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label")).click();
    }
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    jse.executeScript("window.scrollBy(0,100000)", "");
    WebElement element3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//div[1]//div[4]/a[2]")));
    //9.	Применить условия поиска
    wd.findElement(By.xpath(".//div[1]//div[4]/a[2]")).click();
    //10.	Запомнить второй элемент в результатах поиска
    String Acer1 = wd.findElement(By.xpath(".//div[2]/div[4]/div[1]/div/a")).getText();
    System.out.println(Acer1);
    //11.	В поисковую строку ввести запомненное значение.
    wd.get("https://market.yandex.ru/");
    wd.findElement(By.id("header-search")).clear();
    wd.findElement(By.id("header-search")).sendKeys("\"" + Acer1 + "\"");
    wd.findElement(By.xpath(".//button")).click();//12.	Найти и проверить, что наименование товара соответствует запомненному значению.
    String Acer2 = wd.findElement(By.xpath(".//div[1]//div[4]/div[1]/div/a")).getText();
    Assert.assertEquals(Acer1, Acer2);
  }


  @AfterClass
  public void tearDown() {
    wd.quit();
  }

}

