package tests;

import api.AuthorizationApi;
import api.BooksApi;
import extensions.AuthExtension;
import extensions.WithAuth;
import models.AddBookModel;
import models.DeleteBookModel;
import models.IsbnModel;
import models.LoginResponseModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import pages.ProfilePage;

import java.util.ArrayList;
import java.util.List;

import static io.qameta.allure.Allure.step;

@ExtendWith(AuthExtension.class)
public class BooksTest extends TestBase {

    AuthorizationApi authorizationApi = new AuthorizationApi();
    LoginResponseModel loginResponse = authorizationApi.login(loginRequestModel);
    BooksApi booksApi = new BooksApi();
    ProfilePage profilePage = new ProfilePage();
    IsbnModel isbnModel = new IsbnModel();
    String isbnData = "9781449325862";
    List<IsbnModel> isbnList = new ArrayList<>();
    AddBookModel booksList = new AddBookModel();
    DeleteBookModel deleteBook = new DeleteBookModel();

    @Test
    @Tag("books_test")
    @DisplayName("Delete book from profile")
    @WithAuth
    void deleteBookFromProfile() {
        step("Delete all books from profile", () -> {
            booksApi.deleteAllBooks(loginResponse);
        });

        step("Add book to profile", () -> {
            isbnModel.setIsbn(isbnData);
            isbnList.add(isbnModel);

            booksList.setUserId(loginResponse.getUserId());
            booksList.setCollectionOfIsbns(isbnList);

            booksApi.addBook(loginResponse, booksList);
        });

        step("Delete book from profile", () -> {
            deleteBook.setUserId(loginResponse.getUserId());
            deleteBook.setIsbn(isbnData);

            booksApi.deleteBook(loginResponse, deleteBook);
        });

        step("Check book deletion", () -> {
            profilePage.openProfilePage()
                    .checkUserName(profilePage.name)
                    .bookMissing(profilePage.nameBook)
                    .linkForBookMissing();
        });
    }
}