package extensions;

import lombok.Getter;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import io.restassured.response.Response;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static com.codeborne.selenide.Selenide.*;
import org.openqa.selenium.Cookie;

public class AuthExtension implements BeforeEachCallback {
    private static final String USERNAME = "ivan";
    private static final String PASSWORD = "Qwertyqwerty123!";
    @Getter
    private static Response authResponse;

    public AuthExtension() {}

    @Override
    public void beforeEach(ExtensionContext context) {
        if (context.getTestMethod().isPresent() &&
                context.getTestMethod().get().isAnnotationPresent(WithAuth.class)) {
            authenticate();
            setCookies();
        }
    }

    private void authenticate() {
        String authData = String.format("{\"userName\":\"%s\",\"password\":\"%s\"}", USERNAME, PASSWORD);

        authResponse = given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(authData)
                .when()
                .post("/Account/v1/Login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();
    }

    private void setCookies() {
        open("/favicon.ico");
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
    }
}
