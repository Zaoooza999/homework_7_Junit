package guru.qa;

import com.codeborne.selenide.Configuration;
import guru.qa.data.Name;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests {
    @ValueSource(strings = {
            "Max", "Ivan"
    })
    @ParameterizedTest(name = "Отображение первого имени пользователя {0} в форме авторизации")
    void submittingFormContainsCorrectFirstName(String firstName) {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue("Ivanov");
        $$(".custom-radio").findBy(text("Male")).click();
        $("#userNumber").setValue("9876543210");
        $("#submit").shouldBe(visible).shouldBe(enabled).click();
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " Ivanov"));
    }

    @CsvSource( value = {
            "Max, Maximov",
            "Ivan, Ivanov"
    })
    @ParameterizedTest(name = "Отображение полного имени пользователя {0} {1} в форме авторизации")
    void submittingFormContainsCorrectFullName(String firstName, String lastname) {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastname);
        $$(".custom-radio").findBy(text("Male")).click();
        $("#userNumber").setValue("9876543210");
        $("#submit").shouldBe(visible).shouldBe(enabled).click();
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " +lastname));
    }

    @CsvFileSource (resources = "/test_data/PracticeFormTestsData.csv")
    @ParameterizedTest(name = "Отображение полного имени пользователя {0} {1} в форме авторизации")
    void submittingFormContainsCorrectFullNameWithData(String firstName, String lastname) {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastname);
        $$(".custom-radio").findBy(text("Male")).click();
        $("#userNumber").setValue("9876543210");
        $("#submit").shouldBe(visible).shouldBe(enabled).click();
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(firstName + " " +lastname));
    }

    @EnumSource(Name.class)
    @ParameterizedTest(name = "Отображение полного имени пользователя {0} в форме авторизации")
    void submittingFormContainsCorrectFullNameWithEnum(Name name) {
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(name.name());
        $("#lastName").setValue(name.lastName);
        $$(".custom-radio").findBy(text("Male")).click();
        $("#userNumber").setValue("9876543210");
        $("#submit").shouldBe(visible).shouldBe(enabled).click();
        $(".table").$(byText("Student Name")).sibling(0).shouldHave(text(name.name() + " " + name.lastName));
    }

}
