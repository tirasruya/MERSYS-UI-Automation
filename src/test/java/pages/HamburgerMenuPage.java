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
    @FindBy(xpath = "//*[text()='Trash']")
    public WebElement trash;

    @FindBy(xpath = "(//span[@class='mat-focus-indicator'])[16]")
    public WebElement receiversIcon;
    @FindBy(xpath = "//div[@class='mat-mdc-form-field-infix']/input")
    public WebElement addReceivers;
    @FindBy(xpath = "(//div[@class='mdc-checkbox'])[2]")
    public WebElement square;
    @FindBy(xpath = "//*[text()='Add & Close']")
    public WebElement addClose;
    @FindBy(css = "input[placeholder='Subject']")
    public WebElement subjectInput;
   // @FindBy(css = ".mce-content-body")
    @FindBy(css = "iframe[id^='tiny-angular']") // ID'si 'tiny-angular' ile BAŞLAYAN iframe'i bulur
    public WebElement messageIframe;

    @FindBy(css = "body#tinymce")
    public WebElement messageInputBody;
    public WebElement textArea;
    @FindBy(xpath = "//*[text()='Send']")
    public WebElement sendBtn;
    @FindBy(xpath = "//div[@class='ng-star-inserted']/div[1]")
    public WebElement sendedMsg;
    @FindBy(xpath = "//input[@type='checkbox']")
    public List<WebElement> receiverTableRows;
    @FindBy(css = "ms-browse-table tbody > tr")
    public List<WebElement> outboxTableRows;
    @FindBy(xpath = "//*[text()=' There is no data to show you right now! ']")
    public WebElement noDataMessage;


    @FindBy(xpath = "//ms-confirm-button[@id='ms-button-2']")
    public WebElement rubbish;
    @FindBy(xpath = "//button[@class='mdc-button mat-mdc-button-base mdc-button--outlined mat-mdc-outlined-button mat-accent']")
    public WebElement yes;
    @FindBy(css = "ms-delete-button[id='ms-delete-button-0']")
    public WebElement permanentDelete;
    @FindBy(xpath = "//*[text()=' Delete ']")
    public WebElement delete;
    @FindBy(xpath = "//ms-standard-button[@id='ms-standard-button-42']")
    public WebElement restore;

    public HamburgerMenuPage(WebDriver driver) {
        super(driver);
    }


}
