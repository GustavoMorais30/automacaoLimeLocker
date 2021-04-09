package tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertEquals;

public class newUsersTest {
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
    public void testCreateNewUserLime10() throws InterruptedException {

        // Accessing the Registration screen
        navegadorLime10.findElement(By.className("register-link")).click();

        //Validated the "first name" field
        navegadorLime10.findElement(By.id("firstname")).sendKeys("Test");

        // Subscribe or not to the "NewsLetter" box
        navegadorLime10.findElement(By.id("lastname")).sendKeys("Automation");

        // Assine ou não a caixa "NewsLetter"
        navegadorLime10.findElement(By.id("is_subscribed")).click();

        // Date of birth
        navegadorLime10.findElement(By.id("dob")).sendKeys("30/07/2000");

        //CPF
        WebDriver forDevs = new ChromeDriver();
        forDevs.get("https://www.4devs.com.br/gerador_de_pessoas");
        forDevs.findElement(By.id("bt_gerar_pessoa")).click();

        //Copying some random cpf and email from 4devs
        String cpfAleatory = forDevs.findElement(By.id("cpf")).getText();

        String emailAleatory = forDevs.findElement(By.cssSelector("div[id=\"email\"")).getText();
        forDevs.close();

        navegadorLime10.findElement(By.id("taxvat")).sendKeys(cpfAleatory);

        //Zip code
        navegadorLime10.findElement(By.id("zip")).sendKeys("06665080");

        //Cell Phone Number
        navegadorLime10.findElement(By.id("telephone")).sendKeys("11947792154");

        //cellular phone
        navegadorLime10.findElement(By.id("fax")).sendKeys("11974105482");

        //Number*
        navegadorLime10.findElement(By.id("street_2")).sendKeys("240");

        //Bairro
        navegadorLime10.findElement(By.id("street_4")).sendKeys("Cohab");

        //E-mail*
        navegadorLime10.findElement(By.id("email_address")).sendKeys(emailAleatory);

        //Senha de 6 caracteres para exibir uma notificação
        navegadorLime10.findElement(By.id("password")).sendKeys("Gustavo");

        String menssergerErrorPassword = navegadorLime10.findElement(By.id("password-error")).getText();
        Assert.assertEquals("Comprimento mínimo deste campo deve ser igual ou maior que 8 caracteres. Espaços a esquerda e a direita serão ignorados.", menssergerErrorPassword);

        navegadorLime10.findElement(By.id("password")).clear();
        //Senha*
        navegadorLime10.findElement(By.id("password")).sendKeys("Gustavo123");
        //Confirmar Senha*
        navegadorLime10.findElement(By.id("password-confirmation")).sendKeys("Gustavo123");

        //Click on the Register button
         navegadorLime10.findElement(By.cssSelector("button[title='Cadastre-se']")).click();

        Thread.sleep(Long.parseLong("3000"));
        //account creation message validated
        navegadorLime10.findElement(By.xpath("//div[@data-ui-id=\"message-success\"]"));
    }
    @After
        public void tearDown(){
        navegadorLime10.quit();
    }
}