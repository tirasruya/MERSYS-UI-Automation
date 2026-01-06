package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HamburgerMenuPage;
import utils.BaseDriver;
import java.time.Duration;

public class HamburgerMenu_ManagingTrashSteps {

    HamburgerMenuPage hp = new HamburgerMenuPage(BaseDriver.getDriver());
    WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();

    @When("the user navigates to {string} under {string}")
    public void the_user_navigates_to_under(String child, String parent) {
        WebElement messagingMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(text(),'Messaging')]")));
        messagingMenu.click();
        wait.until(ExpectedConditions.visibilityOf(hp.trash));
        js.executeScript("arguments[0].click();", hp.trash);
    }

    @When("the user clicks on the {string} icon for a deleted message")
    public void click_restore_icon(String iconType) {
        // Restore butonunun gelmesini bekle ve ilkine tıkla
        wait.until(driver -> !hp.restoreButtonList.isEmpty());
        js.executeScript("arguments[0].click();", hp.restoreButtonList.get(0));
    }

    @When("the user clicks on the {string} icon to permanently delete a message")
    public void click_delete_icon(String string) {
        // Çöp kutusu (silme) butonunun gelmesini bekle ve ilkine tıkla
        wait.until(driver -> !hp.trashCanButtonList.isEmpty());
        js.executeScript("arguments[0].click();", hp.trashCanButtonList.get(0));
    }

    @Then("the message should be removed from the list successfully")
    @Then("the message should no longer be visible in the Trash list")
    @And("the message should be completely removed from the system")
    public void verify_success_status() {
        // Senin belirlediğin "Success" textini içeren locator'ın görünmesini bekle
        wait.until(ExpectedConditions.visibilityOf(hp.successMessage));

        // Mesajın gerçekten göründüğünü doğrula
        Assert.assertTrue(hp.successMessage.isDisplayed(), "HATA: Başarı mesajı (Success) görüntülenemedi!");

        // Konsola bilgi yazdır (Log takibi için)
        System.out.println("Doğrulama Başarılı: " + hp.successMessage.getText());
    }

    @Then("a confirmation pop-up should be displayed with a message")
    public void aConfirmationPopUpShouldBeDisplayedWithAMessage() {
        wait.until(ExpectedConditions.visibilityOf(hp.deleteMsg));
    }

    @When("the user confirms the permanent deletion")
    public void the_user_confirms_the_permanent_deletion() {
        // Onay penceresindeki Delete butonuna tıkla
        wait.until(ExpectedConditions.elementToBeClickable(hp.deleteButon));
        js.executeScript("arguments[0].click();", hp.deleteButon);
    }
}
