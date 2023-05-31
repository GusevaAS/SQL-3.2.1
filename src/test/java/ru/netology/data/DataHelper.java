package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private static Faker faker = new Faker(new Locale("en"));

    private DataHelper() {
    }

    public static AuthInfo getUser() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static String getRandomLogin() {
        return faker.name().username();
    }

    public static String getRandomPassword() {
        return faker.internet().password();
    }
    public static AuthInfo getRandomUser() {
        return  new AuthInfo(getRandomLogin(), getRandomPassword());
    }

    public static VerifyCode getRandomCode() {
        return new VerifyCode(faker.numerify("######"));
    }

    @Value
    public static class AuthInfo {
        private final String login;
        private final String password;
    }

    @Value
    public static class VerifyCode {
        private final String code;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class AuthCode {
        private String id;
        private String user_id;
        private String code;
        private String created;
    }
}
