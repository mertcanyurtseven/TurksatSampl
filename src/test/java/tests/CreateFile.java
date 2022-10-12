package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Wait;
import org.testng.annotations.Test;
import pages.EditorFactory;
import pages.FileFactory;
import pages.LeftMenuFactory;
import pages.LoginFactory;

public class CreateFile extends BaseTest {
    private Wait<WebDriver> wait;
    private WebDriver driver;

    @Test
    public void createFile() throws Exception{
        this.driver = super.driver;
        LoginFactory login = new LoginFactory(driver);
        FileFactory file = new FileFactory(driver);
        EditorFactory editor = new EditorFactory(driver);
        LeftMenuFactory leftMenu = new LeftMenuFactory(driver);
        login.login();
        login.goToPage();
        file.fillFileFields();
        editor.editFile();
        editor.attachFile();
        editor.signFile();
        leftMenu.signedFiles();
        leftMenu.filesWaitingForReceive();
    }
}
