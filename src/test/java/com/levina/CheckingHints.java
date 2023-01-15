package com.levina;

import com.codeborne.selenide.Configuration;
import com.levina.domain.AddressParameters;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CheckingHints {

    @BeforeAll
    static void beforeAll() {
        Configuration.startMaximized = true;
        Configuration.baseUrl = "https://dadata.ru/suggestions";
    }

    @ValueSource(strings = {
            "Мария Мал",
            "Надежда Ил",
            "Анна Ива"
    })

    @ParameterizedTest(name = "Checking hints by name: {0}")
    void nameSearch(String value) {
        open("/#name");
        $("#fullname-input").setValue(value);
        $(".suggestions-suggestions", 3).shouldHave(text(value));
        ;
    }

    @CsvSource(value = {
            "Максим; Мал",
            "Антон; Ил",
            "Екатерина; Ива",
            "Анастасия; Бор"
    }, delimiter = ';')

    @ParameterizedTest(name = "Search by first and last name")
    void searchByCsvSource(String name, String surname) {
        open("/#name");
        $("#fullname-input").setValue(name + " " + surname);
        $(".suggestions-suggestions", 3).shouldHave(text(name));
        ;
    }

    @EnumSource(AddressParameters.class)
    @ParameterizedTest(name = "Address suggestion check {0}")
    void searchByEnumSource(AddressParameters address) {
        open("/#address");
        $("#address-input").setValue(address.getDescription());
        $(".suggestions-suggestions", 2).shouldHave(text(address.getDescription()));
    }


    static Stream<String> searchByMethodSource() {
        return Stream.of("ИП Иванов", "ИП Петров", "ИП Сидоров");
    }

    @MethodSource()
    @ParameterizedTest(name = "Organization search {0}")
    void searchByMethodSource(String organization) {
        open("/#party");
        $("#party-input").setValue(organization);
        $(".suggestions-suggestions", 3).shouldHave(text(organization));
    }
}
