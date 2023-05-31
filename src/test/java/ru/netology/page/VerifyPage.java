package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.support.FindBy;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.page;

public class VerifyPage {
    @FindBy(css = "[data-test-id=code] input")
    private SelenideElement code;
    @FindBy(css = "[data-test-id=action-verify]")
    private SelenideElement verifyButton;
    @FindBy(css = ".notification__content")
    private SelenideElement error;


    public void verifyPageVisibility() {
        code.shouldBe(visible);
    }

    public void verifyErrorNotificationVisibility() {
        error.shouldBe(visible);
    }

    public DashboardPage validVerify(String verifyCode) {
        verify(verifyCode);
        return page (DashboardPage.class);
    }

    public void verify(String verifyCode) {
        code.setValue(verifyCode);
        verifyButton.click();
    }
}
