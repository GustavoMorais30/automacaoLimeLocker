package tests;

import org.junit.After;
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

        navegadorLime10.findElement(By.xpath("//*[@id=\"menu-6-606f601c8cc9f\"]/ul/li[4]/a")).click();
        navegadorLime10.findElement(By.xpath("//*[@id=\"category-products-grid\"]/ol/li[3]/div/div[2]/strong/a")).click();

        //Add to Wish List
        navegadorLime10.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[2]/a[1]")).click();
        navegadorLime10.findElement(By.xpath("/html/body/div[3]/aside[1]/div[2]/footer/button")).click();

        navegadorLime10.findElement(By.className("main-logo")).click();

        navegadorLime10.findElement(By.xpath("//*[@id=\"menu-6-606f601c8cc9f\"]/ul/li[5]")).click();
        navegadorLime10.findElement(By.xpath("//*[@id=\"category-products-grid\"]/ol/li[2]/div/div[2]/strong/a")).click();

        navegadorLime10.findElement(By.xpath("//*[@id=\"product_addtocart_form\"]/div[2]/a[1]")).click();
        navegadorLime10.findElement(By.xpath("/html/body/div[3]/aside[1]/div[2]/footer/button/span")).click();

        navegadorLime10.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[2]/a")).click();

        //Wishlist
//        navegadorLime10.findElement(By.xpath("/html/body/div[3]/header/div[1]/div/div/div[2]/div/ul/li[3]")).click();
        String messagemItensWishlist = navegadorLime10.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/a/div/div[7]/div[2]")).getText();
        assertEquals("2 itens", messagemItensWishlist);

        //Delete items from the wish list
        navegadorLime10.findElement(By.xpath("//*[@id=\"newsletter\"]")).sendKeys("  ");
        navegadorLime10.findElement(By.xpath("//*[@id=\"wishlist-sidebar\"]/li[1]/div/div/div[2]/div[2]/a")).click();

        navegadorLime10.findElement(By.xpath("//*[@id=\"newsletter\"]")).sendKeys("  ");
        navegadorLime10.findElement(By.xpath("//*[@id=\"wishlist-sidebar\"]/li[1]/div/div/div[2]/div[2]/a")).click();

        Thread.sleep(Long.parseLong("2000"));
        String messageRemoved = navegadorLime10.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/div[2]/div/div/div")).getText();
        assertEquals("Lençol (solteiro) foi removido da sua lista de desejos.", messageRemoved);

        navegadorLime10.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[3]/a/div/div[1]")).click();

        String noItems = navegadorLime10.findElement(By.xpath("//*[@id=\"wishlist-view-form\"]/div[1]/span")).getText();
        assertEquals("Você não tem nenhum item em sua lista de desejos.", noItems);

        navegadorLime10.findElement(By.className("main-logo")).click();

        navegadorLime10.findElement(By.xpath("//*[@id=\"menu-6-606f601c8cc9f\"]/ul/li[2]/a")).click();
        navegadorLime10.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[2]/div/div[1]/ol/li[2]/div/div/strong/a")).click();

        //click in cart
        navegadorLime10.findElement(By.id("product-addtocart-button")).click();

        //finish buy
        navegadorLime10.findElement(By.id("footer-cart-btn-checkout")).click();
        Thread.sleep(Long.parseLong("14000"));
        navegadorLime10.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button")).click();
        Thread.sleep(Long.parseLong("6000"));

        navegadorLime10.findElement(By.xpath("//*[@id=\"stripe_payments_checkout_card\"]")).click();
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
