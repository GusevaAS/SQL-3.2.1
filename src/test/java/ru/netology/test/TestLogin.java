package ru.netology.test;

import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;
import ru.netology.page.LoginPage;


import static com.codeborne.selenide.Selenide.clearBrowserCookies;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.data.SQLHelper.cleanDB;

public class TestLogin {

    @AfterAll
    static void teardown() {
        cleanDB();
    }

    @Test
    void shouldSuccessLogin() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getUser();
        var verifyPage = loginPage.validLogin(authInfo);
        verifyPage.verifyPageVisibility();
        var verifyCode = SQLHelper.getVerifyCode();
        verifyPage.validVerify(verifyCode.getCode());
    }
    @Test
    void shouldInvalidVerifyCode() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getUser();
        var verifyPage = loginPage.validLogin(authInfo);
        verifyPage.verifyPageVisibility();
        var verifyCode = DataHelper.getRandomCode();
        verifyPage.verify(verifyCode.getCode());
        verifyPage.verifyErrorNotificationVisibility();
    }

    @Test
    void shouldRandomUser() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = DataHelper.getRandomUser();
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisibility();
    }

    @Test
    void shouldRandomPass() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authInfo = new DataHelper.AuthInfo(DataHelper.getUser().getLogin(), DataHelper.getRandomPassword());
        loginPage.validLogin(authInfo);
        loginPage.verifyErrorNotificationVisibility();
    }

    @Test
    void shouldPassThreeTimes() {
        var loginPage = open("http://localhost:9999", LoginPage.class);
        var authUserOne = new DataHelper.AuthInfo(DataHelper.getUser().getLogin(),
                DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authUserOne);
        loginPage.getError();
        loginPage.clean();
        clearBrowserCookies();
        var authUserTwo = new DataHelper.AuthInfo(DataHelper.getUser().getLogin(),
                DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authUserTwo);
        loginPage.getError();
        loginPage.clean();
        clearBrowserCookies();
        var authUserThree = new DataHelper.AuthInfo(DataHelper.getUser().getLogin(),
                DataHelper.getRandomUser().getPassword());
        loginPage.validLogin(authUserThree);
        loginPage.getBlock();
    }
}
