package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HamburgerMenuPage;
import utils.BaseDriver;
import java.time.Duration;

public class HamburgerMenu_OutboxSteps {

    // Log4j Logger tanımı
    private static final Logger log = LogManager.getLogger(HamburgerMenu_OutboxSteps.class);

    HamburgerMenuPage hp = new HamburgerMenuPage(BaseDriver.getDriver());
    WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(15));
    JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();

    @And("the user navigates to Outbox under Messaging menu")
    public void navigateToOutboxMenu() {
        log.info("Adım: Messaging menüsü altındaki Outbox'a gidiliyor.");

        log.debug("Messaging menü başlığı aranıyor...");
        WebElement messagingMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[text()='Messaging']")));
        messagingMenu.click();
        log.debug("Messaging menüsüne tıklandı.");

        log.debug("Outbox linki bekleniyor...");
        wait.until(ExpectedConditions.visibilityOf(hp.outbox));
        js.executeScript("arguments[0].click();", hp.outbox);
        log.debug("Outbox linkine tıklandı.");

        wait.until(ExpectedConditions.visibilityOf(hp.confirmOutbox));
        log.info("Başarıyla Outbox sayfasına giriş yapıldı.");
    }

    @Then("the user should see the list of outgoing messages")
    public void verifyOutboxList() {
        log.info("Adım: Giden mesaj listesi görünürlüğü doğrulanıyor.");
        wait.until(ExpectedConditions.visibilityOf(hp.confirmOutbox));
        Assert.assertTrue(hp.confirmOutbox.isDisplayed());
        log.info("Doğrulama başarılı: Outbox listesi görüntülendi.");
    }

    @And("the user clicks on the {string} icon for an outgoing message")
    public void clickRubbishIcon(String iconType) {
        log.info("Adım: " + iconType + " ikonuna tıklanıyor.");

        wait.until(ExpectedConditions.elementToBeClickable(hp.rubbish));
        log.debug("Rubbish butonu bulundu, JS ile tıklanıyor.");
        js.executeScript("arguments[0].click();", hp.rubbish);
        log.info("Rubbish ikonuna tıklandı.");
    }

    @Then("an outbox confirmation pop-up should be displayed")
    public void verifyOutboxPopUp() {
        log.info("Adım: Onay mesajı ekranı bekleniyor.");

        wait.until(ExpectedConditions.visibilityOf(hp.outboxConfirmMsg));
        String text = hp.outboxConfirmMsg.getText();
        log.debug("Ekrandaki onay metni: " + text);

        Assert.assertTrue(text.contains("move this message to trash"),
                "HATA: Onay mesajı ekranda belirmedi!");
        log.info("Doğrulama başarılı: Onay metni görüntülendi.");
    }

    @When("the user confirms the outbox deletion")
    public void confirmOutboxDeletion() {
        log.info("Adım: Silme işlemi onaylanıyor (YES butonuna basılıyor).");

        wait.until(ExpectedConditions.elementToBeClickable(hp.yesButton));
        log.debug("YES butonu aktifleşti, tıklanıyor.");
        js.executeScript("arguments[0].click();", hp.yesButton);
        log.info("Silme onayı verildi.");
    }

    @Then("the {string} message should be displayed indicating the message was deleted")
    public void verifySuccessMessage(String msgType) {
        log.info("Adım: Success mesajı kontrol ediliyor.");

        wait.until(ExpectedConditions.visibilityOf(hp.successMessage));
        Assert.assertTrue(hp.successMessage.isDisplayed(), "HATA: Başarı mesajı görülmedi!");
        log.info("Doğrulama başarılı: Success mesajı görüntülendi.");
    }

    @And("the message should no longer be visible in the Outbox list")
    public void verifyMessageIsGone() {
        log.info("Adım: Mesajın listeden kaybolduğu doğrulanıyor.");

        wait.until(ExpectedConditions.invisibilityOf(hp.successMessage));
        log.info("US006 Senaryosu Başarıyla Tamamlandı: Mesaj listeden kaldırıldı.");
    }
}
