package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

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

    @FindBy(xpath = "//ms-button[@id='ms-button-0']//button")
    public WebElement receivers;
    @FindBy(xpath = "(//div[@class='mdc-checkbox'])[2]")
    public WebElement square;
    @FindBy(xpath = "//*[text()='Add & Close']")
    public WebElement addClose;
    @FindBy(xpath = "//input[@id='ms-text-field-0']")
    public WebElement subject;
    @FindBy(xpath = "//*[text()='Send']")
    public WebElement send;
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
