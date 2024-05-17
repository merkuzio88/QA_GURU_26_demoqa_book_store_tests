package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ProfilePage {
    SelenideElement userName = $("#userName-value"),
            link = $("[href='/profile?book=9781449325862']"),
            booksList = $(".rt-tbody");

    public String name = "ivan",
            nameBook = "Git Pocket Guide";

    public ProfilePage openProfilePage() {
        open("/profile");
        return this;
    }

    public ProfilePage checkUserName(String value) {
        userName.shouldHave(text(value));
        return this;
    }

    public ProfilePage bookMissing(String value) {
        booksList.shouldNotHave(text(value));
        return this;
    }

    public void linkForBookMissing() {
        link.shouldHave(hidden);
    }
}