import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FirstTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.gecko.driver","D:\\Selenium\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    @Test
    public void gotoEdaUaPage() throws InterruptedException {

        driver.get("https://eda.ua/");

        // Login as current/existing user
        driver.findElement(By.xpath("//a[@class='user-panel-auth__link'][@title='Вход']")).click();
        driver.findElement(By.xpath("//form[@class='form-horizontal log-form active']//input[@id='user_models_User_email']")).sendKeys("viktorbibik@ukr.net");
        driver.findElement(By.xpath("//form[@class='form-horizontal log-form active']//input[@id='user_models_User_password']")).sendKeys("deadmananor_1");
        driver.findElement(By.xpath("//form[@class='form-horizontal log-form active']//input[@class='btn btn-primary btn-lg']")).click();


        // Ввод адресса доставки/enter delivery address
        Thread.sleep(10000);
        driver.get("https://eda.ua/");
        driver.findElement(By.xpath("//a[@class='location__get-location2']")).click(); // Нажать на значек определения адресса
        Thread.sleep(10000);
        driver.findElement(By.xpath("//span[@class='twitter-typeahead']//input[@id='address']")).sendKeys("sdvвамваи3424");
        driver.findElement(By.xpath("//div[@class='tab-pane active']//button[@class='btn btn-lg btn-block btn-primary']")).click();

        // Найти товар в поиске, выбрать, заказать, добавить в корзину, нажать на корзину/find item in search, choose, order, add to cart
        Thread.sleep(6000);
        driver.get("https://eda.ua/");

        driver.findElement(By.xpath("//div[text()='Заказывайте доставку еды онлайн']//preceding::input[3]")).sendKeys("Пицца Бергамо");
        driver.findElement(By.xpath("//div[text()='Заказывайте доставку еды онлайн']//preceding::input[3]")).sendKeys(Keys.RETURN);

        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='tab-pane active']//span[@class='btn btn-primary btn-sm pull-right']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='modal-content']//button[@class='btn btn-primary btn-xs-block cart-control test']")).click();
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='col-sm-8 col-md-5']//span[@class='header__basket-icon ic-basket']")).click();

        // Увеличить количество товара в корзине/increase items count
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='modal-dialog']//input[@class='form-control cart-control-el counter__value']")).sendKeys("5");
        driver.findElement(By.xpath("//div[@class='modal-dialog']//input[@class='form-control cart-control-el counter__value']")).sendKeys(Keys.RETURN);

        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='modal-body cart-modal']//a[@class='btn btn-primary issue-order']")).click();

        //Финальная стадия оформления заказа/the final stage - add personal data
        Thread.sleep(10000);
        driver.findElement(By.xpath("//div[@class='box-inner perforation-right']//input[@id='order_models_Order_customer_name']")).sendKeys("!#@$#%$$%^^%&^@#%#$@!"); // name
        driver.findElement(By.xpath("//input[@id='order_models_Order_customer_phone']")).sendKeys("1111111111"); // phone
        //driver.findElement(By.xpath("//input[@id='email-field']")).sendKeys("sdvdsvsdvbsd@fdbdfb.dfbdfb"); // email, only if we work as unregistered user
        driver.findElement(By.xpath("//input[@id='customer_building']")).sendKeys("@!$@#%#%$^%&$%#%"); // house
        driver.findElement(By.xpath("//input[@id='order_models_Order_customer_apartment']")).sendKeys("$#%#$%^$^$@^"); // house number
        driver.findElement(By.xpath("//input[@class='btn btn-primary btn-lg btn-xs-block btn-checkout']")).click(); // confirm personal data

        Thread.sleep(10000);
        String body = driver.findElement(By.xpath("//div[@class='page-message page-message_simple']//div[@class='page-message__title h2']")).getText();
        Assert.assertEquals(body, "Ваш заказ успешно принят!");
    }

    /*@AfterClass
    public void tearDown() {

        driver.close();

    }*/

}
