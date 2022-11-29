package com.karabas18;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParamTests {
    @BeforeAll
    static void setStart() {
        Configuration.browserSize = "1920x1080";
    }

    //
    //    @MethodSource
    //
    static Stream<Arguments> sportMarafonTestConsult() {
        return Stream.of(
                Arguments.of("Телефон", hidden),
                Arguments.of("WhatsApp", hidden),
                Arguments.of("Telegram", visible)
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка видимости поля nickname если выбран способ связи {0}")
    void sportMarafonTestConsult(String contact, Condition visibleOrHidden) {
        open("https://sport-marafon.ru/consultation/");
        $(byText("Какой отдел вас интересует?")).scrollIntoView(false);
        $(".custom-control-label").click();
        $(".form__select > .custom-select-container > .custom-select-opener").click();
        $("[data-value=\"" + contact + "\"]").click();
        $("#telegram-name").shouldBe(visibleOrHidden);
    }

    //
    //@ValueSource
    //
    @ValueSource(strings = {"Каталог", "Одежда", "Обувь", "Туризм", "Бег", "Альпинизм",
            "Путешествия", "Горные лыжи", "Сноуборд"})
    @ParameterizedTest(name = "Проверка соответствия названия раздела {0} " +
            "в верхнем меню сайта надписи в хлебных крошках")
    void sportMarafonTestMenu(String section) {
        open("https://sport-marafon.ru/");
        $(".shop-menu__wrap").$(byText(section)).click();
        $(".breadcrumbs__wrap").shouldHave(text(section));
    }

    //
    // @CsvSource
    //
    @CsvSource(value = {
            "91163LAAFR| Ferrino| Pilier 3 Fr Orange",
            "91188LIIFR| Ferrino| Grit 2 Fr Light Grey",
            "13447| MSR| Habitude 4",
            "130259| Robens| Starlight 2 Green"
    },
            delimiter = '|')

    @ParameterizedTest(name = "Проверка наличия в выдаче поиска по коду {0} товара" +
            " названия бренда {1} и модели {2}")
    void sportMarafonTestSearch(String searchQuery, String expectedBrand, String expectedName) {
        open("https://sport-marafon.ru/");
        $(".header__search").click();
        $("#head-search-input").setValue(searchQuery).pressEnter();
        $(".product-list__brand").shouldHave(text(expectedBrand));
        $(".product-list__name").shouldHave(text(expectedName));

    }
}
