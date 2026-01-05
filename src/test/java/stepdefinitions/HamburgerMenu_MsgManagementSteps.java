package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HamburgerMenuPage;
import utils.BaseDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Duration;

public class HamburgerMenu_MsgManagementSteps {

    private static final Logger LOGGER = LogManager.getLogger(HamburgerMenu_MsgManagementSteps.class);

    // Assuming you have a Driver utility to get the singleton driver
    WebDriver driver = BaseDriver.getDriver();
    HamburgerMenuPage hp = new HamburgerMenuPage(driver);
    Actions actions = new Actions(driver);

    @When("the user clicks on the {string} icon")
    public void the_user_clicks_on_the_icon(String iconName) {
        LOGGER.info("Adım: Hamburger menü ikonuna tıklanıyor.");
        hp.clickElement(hp.hamburgerMenu);
    }

    @When("the user hovers over the {string} menu item")
    public void the_user_hovers_over_the_menu_item(String menuName) {
        LOGGER.info("Adım: {} menü öğesinin üzerine geliniyor (hover).", menuName);
        hp.wait.until(ExpectedConditions.visibilityOf(hp.messaging));
        actions.moveToElement(hp.messaging).perform();
    }

    @Then("the following sub-menu options should be visible:")
    public void verify_sub_menu_options(io.cucumber.datatable.DataTable dataTable) {
        LOGGER.info("Adım: Alt menü öğelerinin görünürlüğü doğrulanıyor.");

        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(15));

        Assert.assertTrue(hp.isDisplayed(hp.inbox));
        LOGGER.info("Görünürlük Doğrulandı: Inbox");
        Assert.assertTrue(hp.isDisplayed(hp.outbox));
        LOGGER.info("Görünürlük Doğrulandı: Outbox");

        wait.until(ExpectedConditions.visibilityOf(hp.trash));
        LOGGER.info("Trash öğesi görünür hale geldi.");

        Assert.assertTrue(hp.isDisplayed(hp.trash));
        LOGGER.info("Görünürlük Doğrulandı: Trash");

        // Logical check for the Naming Bug
        String actualText = hp.sendMessage.getText();
        LOGGER.info("Sistemden okunan buton metni: '{}'", actualText);
        System.out.println("Actual Text Found: " + actualText);
        // This will fail as intended to report the bug!
        // Eğer beklenen "New Message" değilse, assertion'dan önce loga bir uyarı düşelim
        if (!actualText.equals("New Message")) {
            LOGGER.warn("!!! BUG TESPİT EDİLDİ: Gereksinim 'New Message' bekliyor, ancak sistemde '{}' yazıyor.", actualText);
        }

        Assert.assertEquals("New Message", actualText);
    }

    @And("the user clicks on the {string} link")
    public void click_on_link(String linkName) {
        LOGGER.info(">>> Adım: '{}' bağlantısına tıklanıyor.", linkName);
        hp.clickElement(hp.sendMessage);
    }
}
