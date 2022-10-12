package pages;

import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import tests.BaseTest;
import org.json.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.time.Duration;

public class LoginFactory extends BaseTest {
    private Wait<WebDriver> wait;

    @FindBy(xpath="//div[@id='menu']//li//a[contains(text(),'Demo')]")
    WebElement demoBtn;

    @FindBy(id="parolaSertifikaAccordion:uForm:txtUKullaniciAdi")
    WebElement userNameInput;

    @FindBy(id="loginUSifre")
    WebElement passwordInput;

    @FindBy(id="parolaSertifikaAccordion:uForm:girisYapButton")
    WebElement loginBtn;

    @FindBy(id="topMenuForm2:ust:0:ustMenuEleman")
    WebElement evrakIslemleriBtn;

    @FindBy(id="topMenuForm2:ust:0:ust:0:ust:2:ust")
    WebElement evrakOlusturBtn;

    @FindBy(xpath="//div[@id='birimlerimMenusuContainer']//*[contains(text(),'TEST DEPARTMENT')]")
    WebElement testDepartment;

    @FindBy(id="tasviyeSuresiGecmisUyariMesajiDialogEvetBtn")
    WebElement uyariOkBtn;


    public LoginFactory (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void login() throws Exception{

        InputStream is = new FileInputStream("src/main/resources/userInfo.json");
        String jsonTxt = IOUtils.toString(is, "UTF-8");
        JSONObject obj = new JSONObject(jsonTxt);
        String userName = obj.getJSONObject("user").getString("username");
        String password = obj.getJSONObject("user").getString("password");
        wait.until(ExpectedConditions.elementToBeClickable(demoBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(userNameInput)).click();
        userNameInput.sendKeys(userName);
        wait.until(ExpectedConditions.elementToBeClickable(passwordInput)).click();
        passwordInput.sendKeys(password);
        loginBtn.click();
        lutfenBekleyiniz();
        try {
            Thread.sleep(5000);
            if(driver.findElement(By.id("tasviyeSuresiGecmisUyariMesajiDialogEvetBtn")).isDisplayed()){
                wait.until(ExpectedConditions.elementToBeClickable(uyariOkBtn)).click();
                lutfenBekleyiniz();
            }
        } catch (Exception e) {
            wait.until(ExpectedConditions.elementToBeClickable(testDepartment)).click();
            lutfenBekleyiniz();
            wait.until(ExpectedConditions.elementToBeClickable(uyariOkBtn)).click();
            lutfenBekleyiniz();
        }

    }

    public void goToPage() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(evrakIslemleriBtn));
        evrakIslemleriBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(evrakOlusturBtn)).click();
        lutfenBekleyiniz();
    }
}
