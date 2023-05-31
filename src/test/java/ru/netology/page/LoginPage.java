package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.page;

public class LoginPage {
    @FindBy(css = "[data-test-id=login] input")
    private  SelenideElement loginField;
    @FindBy(css = "[data-test-id=password] input")
    private  SelenideElement passwordField;
    @FindBy(css = "[data-test-id='action-login']")
    private  SelenideElement loginButton;
    @FindBy(css = "[data-test-id='error-notification']")
    private  SelenideElement error;

    public void verifyErrorNotificationVisibility() {
        error.shouldBe(visible);
    }

    public VerifyPage validLogin(DataHelper.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
        return page(VerifyPage.class);
    }

    public void clean() {
        loginField.doubleClick().sendKeys(Keys.BACK_SPACE);
        passwordField.doubleClick().sendKeys(Keys.BACK_SPACE);
    }

    public void getError() {
        error.shouldHave(text("Ошибка! " + "Неверно указан логин или пароль"));
        error.shouldBe(Condition.visible);
    }

    public void getBlock() {
        error.shouldHave(text("Ошибка! " + "Пользователь заблокирован"));
        error.shouldBe(Condition.visible);
    }
}
