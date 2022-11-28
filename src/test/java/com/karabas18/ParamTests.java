package com.karabas18;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.awt.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class ParamTests {
    @BeforeAll
        //Configuration.holdBrowserOpen = true;
    static void setStart() {
        Configuration.browserSize = "1920x1080";
        open("https://sport-marafon.ru/");
    }

    @CsvSource(value = {
            "91163LAAFR| Ferrino| Pilier 3 Fr Orange",
            "91188LIIFR| Ferrino| Grit 2 Fr Light Grey",
            "13447| MSR| Habitude 4",
            "130259| Robens| Starlight 2 Green"
    },
    delimiter = '|')

    @ParameterizedTest(name = "Проверка наличия в выдаче поиска по коду {0} товара" +
                        " названий бренда {1} и модели {2}")
    void sportMarafonTest(String serchQuery, String expectedBrand, String expectedName) {
        $(".header__search").click();
        $("#head-search-input").setValue(serchQuery).pressEnter();
        $(".product-list__brand").shouldHave(text(expectedBrand));
        $(".product-list__name").shouldHave(text(expectedName));

       /* $(".header__search").click();
        $("#head-search-input").setValue("91163LAAFR").pressEnter();
        $(".product-list__name").shouldHave(text("Палатка Ferrino Pilier 3 Fr Orange"));
        $(".header__search").click();
        $("#head-search-input").setValue("13447").pressEnter();
        $(".product-list__brand").shouldHave(text("MSR"));
        $(".product-list__name").shouldHave(text("Палатка MSR Habitude 4"));
        /* open("https://detmir.ru");
        $(".e_6").$(byText("Поиск")).click();
        $(".s_9").setValue("Lego").pressEnter();
        switchTo().frame("fl-403933");

        $(".close").click();
        //switchTo().defaultContent();
        $(".hQ hU e_8").click();
        //$(".e_6").$(byText("Поиск")).click();
        // switchTo.Frame("fl-403933");
        //$(".hZ").click();
        //$(withText("Нажмите «Разрешить» в углу")).doubleClick();
        //$(".data-mutation").click()
        //$(".xH xL xK").scrollTo();
        // $(".widget js-widget").
        //$(byText("Поиск")).   .$(byText("Поиск"))*/
    }
}
