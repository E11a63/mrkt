import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class yandex {
  ChromeDriver wd;

  public static boolean isAlertPresent(ChromeDriver wd) {
    try {
      wd.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  @BeforeMethod
  public void setUp() throws Exception {
    wd = new ChromeDriver();
    wd.manage().window().maximize();
  //1.1 Открыть браузер
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
  }

  @Test
  public void yandexMarket() throws InterruptedException {
    wd.manage().window().maximize();
    wd.get("https://yandex.ru/"); //2. Зайти на yandex.ru
    wd.findElement(By.xpath(".//a[5]")).click(); //3.	Перейти на Яндекс Маркет
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
wd.findElement(By.xpath(".//div[1]//li[2]/a")).click(); //4.	Выбрать раздел  Компьютеры html/body/div[1]/div[2]/noindex/ul/li[2]/a
    wd.findElement(By.xpath(".//div[4]/div[1]/div/div[1]/div/a[1]")).click(); //5.	 Выбрать раздел Планшеты
    // прокрутить страницу вниз и подождать
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    JavascriptExecutor jse = (JavascriptExecutor) wd;
    jse.executeScript("window.scrollBy(0,100000)", "");
    wd.findElementByXPath(".//div[1]//div[30]/div[2]/a"); //6.	Зайти в расширенный поиск
    if (!wd.findElement(By.xpath(".//div[1]//div[30]/div[2]/a")).isSelected()) {
      wd.findElement(By.xpath(".//div[1]//div[30]/div[2]/a")).click();
    }
    jse.executeScript("window.scrollBy(0,250)", "");
    wd.findElement(By.id("glf-pricefrom-var")).clear();
    // 7.	Задать параметр поиска от 20000 до 25000 рублей.
    wd.findElement(By.id("glf-pricefrom-var")).sendKeys("20000");
    wd.findElement(By.id("glf-priceto-var")).click();
    // подождать выборку
    wd.findElement(By.id("glf-priceto-var")).clear();
    wd.findElement(By.id("glf-priceto-var")).sendKeys("25000");
    wd.findElement(By.xpath(".//div[2]/div[2]/div/div[2]/button")).click();// 8.	Выбрать производителя Acer
    wd.findElement(By.xpath(".//div[2]/div[2]/div/span/span/input")).clear();
    wd.findElement(By.xpath(".//div[2]/div[2]/div/span/span/input")).sendKeys("Acer");
    wd.findElementByXPath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label"); //
    if (!wd.findElement(By.xpath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label")).isSelected()) {
      wd.findElement(By.xpath(".//div[1]//div[2]/div/div[1]/div/div[1]/span/label")).click();
    }
    wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    jse.executeScript("window.scrollBy(0,100000)", "");
    wd.findElement(By.xpath(".//div[1]//div[4]/a[2]")).click(); //9.	Применить условия поиска
    String fr = wd.findElement(By.xpath(".//div[1]//div[2]/div[4]/div[1]/div/a")).getText();//10.	Запомнить второй элемент в результатах поиска
    System.out.println(fr);
    wd.get("https://market.yandex.ru/"); //11.	В поисковую строку ввести запомненное значение.
    wd.findElement(By.id("header-search")).clear();
    wd.findElement(By.id("header-search")).sendKeys("\"" + fr + "\"");
    wd.findElement(By.xpath(".//button")).click();//12.	Найти и проверить, что наименование товара соответствует запомненному значению.
    String fr2 = wd.findElement(By.xpath(".//div[1]//div[4]/div[1]/div/a")).getText();
    Assert.assertEquals(fr, fr2);
  }


  @AfterClass
  public void tearDown() {
    wd.quit();
  }

}

