package api;

import models.LoginRequestModel;
import models.LoginResponseModel;

import static api.spec.LoginUserSpec.loginRequestSpec;
import static api.spec.LoginUserSpec.loginResponseSpec;
import static io.restassured.RestAssured.given;

public class AuthorizationApi {
    public LoginResponseModel login(LoginRequestModel loginRequestModel) {
        return given(loginRequestSpec)
                .body(loginRequestModel)
                .when()
                .post("/Account/v1/Login")
                .then()
                .spec(loginResponseSpec)
                .statusCode(200)
                .extract().as(LoginResponseModel.class);
    }
}