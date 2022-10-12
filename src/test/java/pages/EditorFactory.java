package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import tests.BaseTest;
import java.io.File;
import java.time.Duration;
import java.util.Set;

@SuppressWarnings("Unused")
public class EditorFactory extends BaseTest {
    private Wait<WebDriver> wait;

    @FindBy(xpath="*//button[@id='yeniGidenEvrakForm:leftTab:uiRepeat:1:cmdbutton']")
    WebElement editorBtn;
    @FindBy(xpath="//*[@id='cke_yeniGidenEvrakForm:ckeditorInstance_window1']//*//iframe")
    WebElement editorField;
    @FindBy(xpath="*//button[@id='yeniGidenEvrakForm:leftTab:uiRepeat:2:cmdbutton']")
    WebElement attachmentBtn;
    @FindBy(id="yeniGidenEvrakForm:evrakEkTabView:dosyaAciklama")
    WebElement attachmentText;
    @FindBy(id="yeniGidenEvrakForm:evrakEkTabView:fileUploadButtonA_input")
    WebElement attachFileBtn;
    @FindBy(id="yeniGidenEvrakForm:evrakEkTabView:dosyaAdi")
    WebElement attachedFileName;
    @FindBy(id="yeniGidenEvrakForm:evrakEkTabView:dosyaEkleButton")
    WebElement attachBtn;
    @FindBy(id="yeniGidenEvrakForm:rightTab:uiRepeat:2:cmdbutton")
    WebElement signBtn;
    @FindBy(id="imzalaForm:imzalaRadio")
    WebElement sImzaBtn;
    @FindBy(id="imzalaForm:sayisalImzaConfirmDialogOpener")
    WebElement imzalaBtn;
    @FindBy(id="imzalaForm:sayisalImzaConfirmForm:sayisalImzaEvetButton")
    WebElement imzalaEvetBtn;
    @FindBy(xpath="*//div[@class='lobibox-notify-msg' and @aria-label='İşlem başarılıdır!']")
    WebElement imzalaOnayMsg;




    public EditorFactory(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        wait= new WebDriverWait(driver, Duration.ofSeconds(120));
    }

    public void editFile() throws Exception {
        wait.until(ExpectedConditions.elementToBeClickable(editorBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(editorField)).click();
        editorField.sendKeys("\n Bu bir test metnidir, \n Saygılarımla");
    }

    public void attachFile() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(attachmentBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(attachmentText)).click();
        attachmentText.sendKeys("Test File Attached");
        File file = new File("src/main/resources/test.docx");
        attachFileBtn.sendKeys(file.getAbsolutePath());
        Thread.sleep(3000);
        Assert.assertEquals(attachedFileName.getText(), file.getName());
        wait.until(ExpectedConditions.elementToBeClickable(attachBtn)).click();
    }

    public void signFile() throws Exception{
        wait.until(ExpectedConditions.elementToBeClickable(signBtn)).click();
        lutfenBekleyiniz();
        closeWindow();
        wait.until(ExpectedConditions.elementToBeClickable(sImzaBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(imzalaBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(imzalaEvetBtn)).click();
        lutfenBekleyiniz();
        wait.until(ExpectedConditions.elementToBeClickable(imzalaOnayMsg));
        Assert.assertEquals(imzalaOnayMsg.getText(),"İşlem başarılıdır!");
        Thread.sleep(1000);
    }

    public void closeWindow(){
        Set <String> windows = driver.getWindowHandles();
        String mainwindow = driver.getWindowHandle();
        for (String handle: windows)
        {
            driver.switchTo().window(handle);
            System.out.println("switched to "+driver.getTitle()+"  Window");
            String pagetitle = driver.getTitle();
            if(pagetitle.equalsIgnoreCase("Yardım | Türksat A.Ş.EBYS"))
            {
                driver.close();
                System.out.println("Closed the  '"+pagetitle+"' Tab now ...");
            }
        }
        driver.switchTo().window(mainwindow);
    }
}
