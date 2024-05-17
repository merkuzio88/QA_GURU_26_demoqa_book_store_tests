package api;

import models.AddBookModel;
import models.DeleteBookModel;
import models.LoginResponseModel;

import static api.spec.BookUserSpec.*;
import static io.restassured.RestAssured.given;

public class BooksApi {

    public void deleteAllBooks(LoginResponseModel loginResponse) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .queryParam("UserId", loginResponse.getUserId())
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(bookDeleteResponseSpec);
    }

    public void addBook(LoginResponseModel loginResponse, AddBookModel booksList) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(booksList)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(bookAddResponseSpec);
    }

    public void deleteBook(LoginResponseModel loginResponse, DeleteBookModel deleteBookModel) {
        given(bookRequestSpec)
                .header("Authorization", "Bearer " + loginResponse.getToken())
                .body(deleteBookModel)
                .when()
                .delete("/BookStore/v1/Book")
                .then()
                .spec(bookDeleteResponseSpec);
    }
}