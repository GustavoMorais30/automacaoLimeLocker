package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;

public class loginAndBuy {
    private WebDriver navegadorLime10;

    @Before
    public void setUp() {
        //Open navigator
        System.setProperty("webdriver.chrome.driver", "C:\\javaAutomação\\chromedriver_win32\\chromedriver89.exe");
        navegadorLime10 = new ChromeDriver();

        //Open site the Lime10.com
        navegadorLime10.get("https://lime10.com/");

    }
    @Test
    public void testLoginAndBuy() throws InterruptedException {
        //access account in lime10
        navegadorLime10.findElement(By.id("authorization-trigger")).click();

        WebElement submit = navegadorLime10.findElements(By.xpath("//fieldset//div//button[@type=\"submit\"]")).get(1);
        submit.click();
        //validate E-mail error message
        String messageErrorEmail = navegadorLime10.findElement(By.id("login[username]-error")).getText();
        assertEquals("ESTE É UM CAMPO OBRIGATÓRIO.", messageErrorEmail);

        //validate password error message
        String messageErrorPassword = navegadorLime10.findElement(By.id("login[password]-error")).getText();
        assertEquals("ESTE É UM CAMPO OBRIGATÓRIO.", messageErrorPassword);


        WebElement email = navegadorLime10.findElements(By.xpath("//*[@id=\"cdz-login-form-dropdown\"]/div/div/form/fieldset/div[1]/div/input")).get(1);
        email.sendKeys("testautomation");

        WebElement password = navegadorLime10.findElements(By.xpath("//*[@id=\"cdz-login-form-dropdown\"]/div/div/form/fieldset/div[2]/div/input")).get(1);
        password.sendKeys("Boio");
        submit.click();

        String massegeErrorEmailValid = navegadorLime10.findElement(By.xpath("//div[@id=\"login[username]-error\"]")).getText();
        assertEquals("POR FAVOR INSIRA UM ENDEREÇO DE E-MAIL VÁLIDO (EX: EXEMPLO@DOMINIO.COM).", massegeErrorEmailValid);

        String messageErrorPasswordValid = navegadorLime10.findElement(By.xpath("//div[@id=\"login[password]-error\"]")).getText();
        assertEquals("POR FAVOR INSIRA 6 OU MAIS CARACTERES. OS ESPAÇOS INICIAIS OU FINAIS SERÃO IGNORADOS.", messageErrorPasswordValid);

        email.clear();
        password.clear();

        email.sendKeys("testautomation@mailinator.com");
        password.sendKeys("Boioi@2020");

        submit.click();
        navegadorLime10.findElement(By.className("main-logo")).click();

        //select one or more products and add to cart
        Thread.sleep(Long.parseLong("6000"));
        navegadorLime10.findElement(By.linkText("Bobó de Frango")).click();

        //click in cart
        navegadorLime10.findElement(By.id("product-addtocart-button")).click();

        //finish buy
        navegadorLime10.findElement(By.id("footer-cart-btn-checkout")).click();
        Thread.sleep(Long.parseLong("14000"));
        navegadorLime10.findElement(By.xpath("//*[@id=\"stripe_checkout_card\"]/div[1]/label/span[2]")).click();
        Thread.sleep(Long.parseLong("6000"));

        navegadorLime10.findElement(By.xpath("//*[@id=\"stripe_checkout_card\"]/div[2]/div[4]/div/button/span")).click();
        Thread.sleep(Long.parseLong("9000"));
        //Payments
        navegadorLime10.findElement(By.xpath("//*[@id=\"cardNumber\"]")).sendKeys("4242 4242 4242 4242");
        navegadorLime10.findElement(By.xpath("//*[@id=\"cardExpiry\"]")).sendKeys("12/30");
        navegadorLime10.findElement(By.xpath("//*[@id=\"cardCvc\"]")).sendKeys("123");
        navegadorLime10.findElement(By.xpath("//*[@id=\"billingName\"]")).sendKeys("Test Automation");

        navegadorLime10.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/div/form/div[2]/div[4]/button/div[3]")).click();

        navegadorLime10.quit();
    }
}
