package guru.qa;

import com.codeborne.selenide.CollectionCondition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class DuckDuckGoWebTest {
    @BeforeEach
    void setUp(){
        open("https://duckduckgo.com/");
    }

    @ValueSource (strings = {
            "Selenide", "Junit 5"
    })
    @ParameterizedTest(name = "Для поискового запроса {0} должен отдавать не пустой список карточек")
    @Tag("BLOCKER")
    void searchResultShouldNotBeEmpty(String searchQuery) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $$("[data-testid='mainline'] li[data-layout='organic']").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }

    @CsvFileSource(resources = "/test_data/DuckDuckGoWebTestData.csv")
    @ParameterizedTest(name = "Для поискового запроса {0} в первой карточке должна быть ссылка {1}")
    @Tag("BLOCKER")
    void searchResultShouldContainExpectedUrl(String searchQuery, String expectedLink) {
        $("#searchbox_input").setValue(searchQuery).pressEnter();
        $("[data-testid='mainline'] li[data-layout='organic']").shouldHave(text(expectedLink));
    }

    @Test
    @Tag("BLOCKER")
    @DisplayName("Для поискового запроса 'Selenide' должен показываться не пустой список фото")
    void successfulSearchPhotoTest() {
        $("#searchbox_input").setValue("selenide").pressEnter();
        //$("[href='/?origin=funnel_home_google&t=h_&q=selenide&ia=images&iax=images']").shouldBe(Condition.visible).click(); - Не понимаю почему не работает
        $(".aDtqDaYogch0DyrGTrX6").$(byText("Изображения")).click();
        $$(".SZ76bwIlqO8BBoqOLqYV").shouldBe(CollectionCondition.sizeGreaterThan(0));
    }
}
