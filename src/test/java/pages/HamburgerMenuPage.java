package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class HamburgerMenuPage extends BasePage {

    @FindBy(xpath = "(//span[@class='mdc-button__label'])[6]")
    public WebElement hamburgerMenu;
    @FindBy(xpath = "//*[text()='Messaging']")
    public WebElement messaging;
    @FindBy(xpath = "//*[text()='Send Message']")
    public WebElement sendMessage;
    @FindBy(xpath = "//*[text()='Inbox']")
    public WebElement inbox;
    @FindBy(xpath = "//*[text()='Outbox']")
    public WebElement outbox;
    @FindBy(xpath = "//button[@role='menuitem']//span[text()='Trash']")
    public WebElement trash;

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[16]")
    public WebElement receiversIcon;
    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix']/input")
    public WebElement addReceivers;
    @FindBy(xpath = "//*[text()='Add & Close']")
    public WebElement addClose;
    @FindBy(css = "input[placeholder='Subject']")
    public WebElement subjectInput;
    @FindBy(css = "iframe[id^='tiny-angular']") // ID'si 'tiny-angular' ile BAŞLAYAN iframe'i bulur
    public WebElement messageIframe;

    @FindBy(css = "body#tinymce")
    public WebElement messageInputBody;
    public WebElement textArea;
    @FindBy(xpath = "//*[text()='Send']")
    public WebElement sendBtn;
    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> receiverTableRows;
    @FindBy(css = "ms-browse-table tbody > tr")
    public List<WebElement> outboxTableRows;
    @FindBy(xpath = "//*[text()=' There is no data to show you right now! ']")
    public WebElement noDataMessage;




    @FindBy(xpath = "//*[text()=' Delete ']")
    public WebElement deleteButon;
    @FindBy(xpath = "//ms-standard-button[@icon='trash-restore']//button")
    public List<WebElement> restoreButtonList;
    @FindBy(xpath = "//ms-delete-button//button")
    public List<WebElement> trashCanButtonList;
    @FindBy(xpath = "//div[contains(text(),'delete')]")
    public WebElement deleteMsg;
    @FindBy(xpath = "//div[contains(text(),'Success') or contains(text(),'successfully')]")
    public WebElement successMessage;
    @FindBy(xpath = "(//button[@confirm and contains(@class, 'error')])[1]")
    public WebElement rubbish;
    @FindBy(xpath = "//button[@type='submit']")
    public WebElement yesButton;
    @FindBy(xpath = "//*[contains(text(),'move') and contains(text(),'trash')]")
    public WebElement outboxConfirmMsg;
    @FindBy(xpath = "//span[contains(text(),'Outbox')]")
    public WebElement confirmOutbox;






    public HamburgerMenuPage(WebDriver driver) {
        super(driver);
    }


}
