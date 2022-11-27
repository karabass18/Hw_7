package com.karabas18;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParamTests {
    @Test
    void detMirTest() {
        Configuration.holdBrowserOpen = true;
        open("https://detmir.ru");
        $(".e_6").$(byText("Поиск")).click();
        //$(byText("Поиск")).
    }
}
