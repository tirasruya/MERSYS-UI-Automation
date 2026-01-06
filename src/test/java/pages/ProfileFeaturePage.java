package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class ProfileFeaturePage extends BasePage {

    @FindBy(xpath= "//span[@class='username mr-12']")
    public WebElement profileSettingsButton;

    @FindBy(xpath= "//span[text()='Settings']")
    public WebElement settingsButton;

    @FindBy(xpath = "//img[contains(@class, 'profile-image') and contains(@class, 'avatar')]")
    public WebElement uploadPicture;

    @FindBy(xpath = "//*[contains(text(),'MB') or contains(text(),'KB')]")
    public WebElement fileSizeText;

    @FindBy(xpath = "//button[.//span[contains(text(),'Upload')]]")
    public WebElement confirmUploadBtn;

    @FindBy(xpath = "//span[text()='Save']//ancestor::button")
    public WebElement saveBtn;

    @FindBy(xpath = "//div[contains(text(),'Success') or contains(text(),'successfully')]")
    public WebElement successMessage;

    @FindBy(css = "mat-select[formcontrolname='theme']")
    public WebElement themeDropdown;

    @FindBy(css = "mat-option span.mdc-list-item__primary-text")
    public List<WebElement> themeOptions;












    public ProfileFeaturePage(WebDriver driver) {
        super(driver);
    }
}
