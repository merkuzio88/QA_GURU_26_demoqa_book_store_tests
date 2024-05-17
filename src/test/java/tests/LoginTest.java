package tests;
import extensions.AuthExtension;
import extensions.WithAuth;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ProfilePage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;

@ExtendWith(AuthExtension.class)
public class LoginTest extends TestBase {

    ProfilePage profilePage = new ProfilePage();

    @Test
    @Tag("login_test")
    @DisplayName("User authorization")
    @WithAuth
    void successfulLoginTest() {
        step("User authorization check", () -> {
            open("/profile");
            profilePage.checkUserName(profilePage.name);
        });
    }
}