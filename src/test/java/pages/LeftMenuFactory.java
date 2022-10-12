package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import java.time.Duration;

public class LeftMenuFactory extends BaseTest {
    private Wait<WebDriver> wait;

    @FindBy(xpath="//*[contains(text(),'İşlem Yaptıklarım')]")
    WebElement islemYaptiklarimMenu;
    @FindBy(xpath="//div[@id='leftMenuForm:leftMenuIslemYaptiklarim']//*[contains(text(),'İmzaladıklarım')]")
    WebElement islemYaptiklarimImzaladiklarimMenu;
    @FindBy(xpath="//table[@id='mainInboxForm:inboxDataTable:0:evrakTable']//*//tr[1]//*//h3")
    WebElement konuLabel;
    @FindBy(xpath="//table[@id='mainInboxForm:inboxDataTable:0:evrakTable']//*//tr[2]//div")
    WebElement gidecegiYerLabel;
    @FindBy(xpath="//table[@id='mainInboxForm:inboxDataTable:0:evrakTable']//*//tr[3]//div[@class='searchText']")
    WebElement dateTodayLabel;
    @FindBy(xpath="//h3[contains(text(),'Birim Evrakları')]")
    WebElement departmentFilesMenu;
    @FindBy(xpath="//*[contains(text(),'Teslim Alınmayı Bekleyenler')]")
    WebElement waitingForReceive;

    public LeftMenuFactory(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void signedFiles() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(islemYaptiklarimMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(islemYaptiklarimImzaladiklarimMenu)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(konuLabel));
        Assert.assertEquals(konuLabel.getText(),"Konu: Tüzükler");
        wait.until(ExpectedConditions.elementToBeClickable(gidecegiYerLabel));
        Assert.assertEquals(gidecegiYerLabel.getText(),"Gideceği Yer: Alt Test Birimi(G)");
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime now = LocalDateTime.now();
        wait.until(ExpectedConditions.elementToBeClickable(dateTodayLabel));
        Assert.assertEquals((dateTodayLabel.getText().split(": ")[1]).split(" /")[0],dtf.format(now));
    }

    public void filesWaitingForReceive() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(departmentFilesMenu)).click();
        wait.until(ExpectedConditions.elementToBeClickable(waitingForReceive)).click();
        lutfenBekleyiniz();
        //Ekler görüntülenemediği için teste devam edilemedi
    }
}
