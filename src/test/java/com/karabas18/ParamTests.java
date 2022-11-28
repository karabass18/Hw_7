package com.karabas18;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.awt.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ParamTests {
    @BeforeAll
    //Configuration.holdBrowserOpen = true;
    static void setStart() {
        Configuration.holdBrowserOpen = true;
        Configuration.browserSize = "1920x1080";
       // open("https://sport-marafon.ru/");
    }
    @Test
    void sportMarafonTestConsult(){
        open("https://sport-marafon.ru/consultation/");
        $(".form__item").scrollIntoView(true);
        $(".custom-control-label").click();
        $(".form__select > .custom-select-container > .custom-select-opener").click();
        $("[data-value=\"Telegram\"]").click();

        Condition v =visible;
        $("#telegram-name").shouldBe(v);
                //$(withText("Туристическое снаряжение")).doubleClick();
    }


    @Disabled
    @ValueSource(strings = {"Каталог", "Одежда", "Обувь", "Туризм", "Бег", "Альпинизм",
            "Путешествия", "Горные лыжи", "Сноуборд"})
    @ParameterizedTest(name = "Проверка соответствия названия раздела {0} " +
            "в верхнем меню сайта надписи в хлебных крошках")
    void sportMarafonTestMenu(String section) {
        $(".shop-menu__wrap").$(byText(section)).click();
        $(".breadcrumbs__wrap").shouldHave(text(section));
    }

    @CsvSource(value = {
            "91163LAAFR| Ferrino| Pilier 3 Fr Orange",
            "91188LIIFR| Ferrino| Grit 2 Fr Light Grey",
            "13447| MSR| Habitude 4",
            "130259| Robens| Starlight 2 Green"
    },
            delimiter = '|')
    @Disabled
    @ParameterizedTest(name = "Проверка наличия в выдаче поиска по коду {0} товара" +
            " названий бренда {1} и модели {2}")
    void sportMarafonTestSearch(String searchQuery, String expectedBrand, String expectedName) {
        $(".header__search").click();
        $("#head-search-input").setValue(searchQuery).pressEnter();
        $(".product-list__brand").shouldHave(text(expectedBrand));
        $(".product-list__name").shouldHave(text(expectedName));

    }


}
