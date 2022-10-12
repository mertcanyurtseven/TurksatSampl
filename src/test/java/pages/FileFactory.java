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

import java.time.Duration;

public class FileFactory extends BaseTest {
    private Wait<WebDriver> wait;

    @FindBy(id="evrakBilgileriContainerDiv")
    WebElement containerDiv;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:treeButton")
    WebElement docTreeBtn;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:lovTree:1']/span/span[1]")
    WebElement genelElement;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:lovTree:1_0']/span/span[1]")
    WebElement mevzuatElement;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:1:konuKoduLov:lovTree:1_0_1']")
    WebElement tuzukElement;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:3:konuTextArea")
    WebElement subjectText;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:treeButton")
    WebElement folderTreeBtn;
    @FindBy(xpath = "//*[@id='yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:lovTree:0']/span/span[1]")
    WebElement testBirimElement;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:lovTree:0_1']/span/span[1]")
    WebElement klasorlerElement;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:4:eklenecekKlasorlerLov:lovTree:0_1_0']")
    WebElement folderGenelElement;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:18:geregiLov:treeButton")
    WebElement geregiTreeBtn;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:18:geregiLov:lovTree:0']/span/span[1]")
    WebElement geregiBirimElement;
    @FindBy(xpath="//*[@id='yeniGidenEvrakForm:evrakBilgileriList:18:geregiLov:lovTree:0_1']")
    WebElement altTestBirimiElement;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:21:onayAkisiEkle")
    WebElement onayAkisiEkleBtn;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:21:akisAdimLov:LovSecilenTable:0:selectOneMenu")
    WebElement secilenCombo;
    @FindBy(xpath="//option[@value='IMZALAMA']")
    WebElement secilenComboImzalamaOption;
    @FindBy(id="yeniGidenEvrakForm:evrakBilgileriList:21:anlikAkisKullanButton")
    WebElement imzaciKullanBtn;




    public FileFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void fillFileFields() throws Exception {
        selectSubject();
        selectFolder();
        selectGeregi();
        selectImzaci();
    }

    public void selectSubject() throws Exception {
        //Sabit yapıldı, id kısmındaki indexler generic yapılarak dinamik bir test yapılabilir
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(docTreeBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(genelElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(mevzuatElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(tuzukElement)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(subjectText));
        Assert.assertEquals(subjectText.getText(),"Tüzükler");
    }
    public void selectFolder() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(folderTreeBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(testBirimElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(klasorlerElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(folderGenelElement)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(containerDiv)).click();
        lutfenBekleyiniz();
    }
    public void selectGeregi() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(geregiTreeBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(geregiBirimElement)).click();
        wait.until(ExpectedConditions.elementToBeClickable(altTestBirimiElement)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(containerDiv)).click();
        lutfenBekleyiniz();
    }
    public void selectImzaci() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(onayAkisiEkleBtn)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secilenCombo)).click();
        wait.until(ExpectedConditions.elementToBeClickable(secilenComboImzalamaOption)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(imzaciKullanBtn)).click();
        lutfenBekleyiniz();
    }
}
