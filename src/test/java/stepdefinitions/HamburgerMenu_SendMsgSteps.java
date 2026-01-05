package stepdefinitions;

import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.HamburgerMenuPage;
import pages.SubmissionPage;
import pages.base.BasePage;
import utils.BaseDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HamburgerMenu_SendMsgSteps {
    private static final Logger LOGGER = LogManager.getLogger(HamburgerMenu_SendMsgSteps.class);
    HamburgerMenuPage hp = new HamburgerMenuPage(BaseDriver.getDriver());
    SubmissionPage submissionPage = new SubmissionPage(BaseDriver.getDriver());

    // NOT: Duplicate hatası almamak için Hamburger Menu tıklama
    // ve Messaging hover adımlarını buradan kaldırdım.
    // O adımları diğer StepDef sınıfın hallediyor.

    @Then("a text editor pop-up should be displayed")
    public void a_text_editor_pop_up_should_be_displayed() {
        LOGGER.info("Adım: Mesaj editörü penceresi doğrulanıyor.");
        Assert.assertTrue(hp.isDisplayed(hp.receiversIcon), "HATA: Mesaj editörü açılamadı!");
    }

    @And("the user should see and click the {string} icon")
    public void the_user_should_see_and_click_the_icon(String iconName) {
        LOGGER.info("Adım: '{}' ikonuna tıklanıyor.", iconName);
        hp.clickElement(hp.receiversIcon);
    }

    @When("the user types {string} in the receiver field")
    public void the_user_types_in_the_receiver_field(String searchKey) {
        LOGGER.info("Adım: Alıcı alanına '{}' yazılıyor.", searchKey);
        hp.sendKeysToElement(hp.addReceivers, searchKey);
        hp.wait(2);
    }

    @Then("the system should display the results for {string}")
    public void theSystemShouldDisplayTheResultsFor(String receiverType) {
        LOGGER.info("Adım: {} için sonuçlar kontrol ediliyor.", receiverType);
        WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(10));

        if (receiverType.equalsIgnoreCase("Teacher")) {
            // --- TEACHER İÇİN NORMAL AKIŞ ---
            try {
                wait.until(driver -> hp.receiverTableRows.size() > 0);
                LOGGER.info("Başarılı: Teacher listesi dolu.");
            } catch (Exception e) {
                Assert.fail("HATA: Teacher listesi boş geldi!");
            }
        }
        else if (receiverType.equalsIgnoreCase("Student")) {
            // --- STUDENT İÇİN BUG TESPİT MANTIĞI ---
            try {
                // "There is no data..." yazısı çıkana kadar bekle
                wait.until(ExpectedConditions.visibilityOf(hp.noDataMessage));

                // Yazı görüldüğü an bu blok çalışır ve testi burada patlatır (İstediğin Bug Raporu)
                if (hp.noDataMessage.isDisplayed()) {
                    LOGGER.error("!!! BUG TESPİT EDİLDİ: Student araması 'No data' döndürüyor!");
                    throw new AssertionError("!!! BUG REPORT: Student araması yapıldığında 'There is no data to show you right now!' mesajı alındı. Sistem veri döndürmüyor!");
                }
            } catch (Exception e) {
                // Eğer 10 saniye içinde "No data" yazısı ÇIKMAZSA,
                // demek ki liste dolu gelmiştir, teste devam edebiliriz.
                if (hp.receiverTableRows.size() > 0) {
                    LOGGER.info("Student listesi dolu geldi, teste devam ediliyor.");
                } else {
                    Assert.fail("Student araması ne liste döndürdü ne de 'No data' mesajı verdi.");
                }
            }
        }
    }
    @And("the user selects a random available receiver")
    public void theUserSelectsARandomAvailableReceiver() {
        LOGGER.info("Adım: Listeden rastgele bir alıcı seçiliyor.");

        // Güvenlik için tekrar liste kontrolü (Teacher'da dolu gelecek)
        if (hp.receiverTableRows.size() > 0) {
            // Rastgele bir index belirle
            int randomIndex = (int) (Math.random() * hp.receiverTableRows.size());
            WebElement selectedElement = hp.receiverTableRows.get(randomIndex);

            LOGGER.info("Seçilen alıcı ismi: {}", selectedElement.getText());

            WebDriverWait wait = new WebDriverWait(BaseDriver.getDriver(), Duration.ofSeconds(10));

            // JavaScript ile en sağlam tıklamayı yapıyoruz
            JavascriptExecutor js = (JavascriptExecutor) BaseDriver.getDriver();
            js.executeScript("arguments[0].click();", selectedElement);

            wait.until(ExpectedConditions.elementToBeClickable(hp.addClose));

            js.executeScript("arguments[0].click();", hp.addClose);
            LOGGER.info("Add & Close butonuna tıklandı.");
        } else {
            Assert.fail("Seçilecek alıcı bulunamadı, liste boş!");
        }
    }

    @And("the user enters a subject as {string}")
    public void the_user_enters_a_subject_as(String subjectText) {
        LOGGER.info("Adım: Konu giriliyor: {}", subjectText);
        hp.sendKeysToElement(hp.subjectInput, subjectText);
    }

    @And("the user types {string} in the text editor")
    public void the_user_types_in_the_text_editor(String content) {
        LOGGER.info("Adım: Mesaj içeriği yazılıyor.");
        BaseDriver.getDriver().switchTo().frame(hp.messageIframe);

        // 2. Metni yazıyoruz (hp.textContent locator'ının .mce-content-body olduğunu varsayıyorum)
        hp.sendKeysToElement(hp.messageInputBody, content);

        // 3. Ana sayfaya geri dönüyoruz (Send butonu ana sayfada çünkü)
        BaseDriver.getDriver().switchTo().defaultContent();
    }

    @And("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String buttonName) {
        LOGGER.info("Adım: '{}' butonuna tıklanıyor.", buttonName);
        hp.clickElement(hp.sendBtn);
    }

    @Then("a {string} notification should be displayed")
    public void a_notification_should_be_displayed(String expectedMessage) {
        LOGGER.info("Adım: Başarı bildirimi kontrol ediliyor.");
        Assert.assertTrue(
                submissionPage.isDraftSuccessMessageDisplayed(),
                "Draft success message not displayed"
        );
    }

    @And("the user clicks on the {string} link and then {string} link")
    public void theUserClicksOnTheLinkAndThenLink(String arg0, String arg1) {

        hp.clickElement(hp.messaging);

        // 3. Outbox linki görünür ve tıklanabilir olana kadar bekle, sonra tıkla
        // (hp.outbox);
        hp.wait(3);
        hp.clickElement(hp.outbox);
    }


    @Then("the message with subject {string} should be visible in the Outbox list")
    public void the_message_with_subject_should_be_visible_in_the_outbox_list(String subjectText) {
        LOGGER.info("Adım: Outbox'ta '{}' konusu aranıyor.", subjectText);
        hp.wait(15);
        boolean isfound = hp.outboxTableRows.stream()
                .anyMatch(row -> row.getText().contains(subjectText));
        Assert.assertTrue(isfound, "Gönderilen mesaj Outbox'ta bulunamadı!");
    }


}
