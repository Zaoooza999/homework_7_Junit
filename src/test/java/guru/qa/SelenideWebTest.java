package guru.qa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import guru.qa.data.Language;
import guru.qa.data.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class SelenideWebTest {
    @BeforeEach
    void setUp(){
        open("https://selenide.org/");
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
    }

    @AfterEach
    void afterEach(){
        Selenide.closeWebDriver();
    }

    @EnumSource(Language.class)
    @ParameterizedTest
    void selenideSiteShoulDisplayCorrectText(Language language){//не понимаю почему не работает
        $$("#languages a").findBy(text(language.name())).click();
        $("h3").shouldHave(text(language.description));
    }

static Stream<Arguments> selenideSiteShoulDisplayCorrectButtons(){
        return Stream.of(
                Arguments.of(Language.EN,
                        List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes"),
                        new Person("Dima",34)
                ),
                Arguments.of(Language.RU,
                        List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"),
                        new Person("Viktor",29)
                )
        );
}

    @MethodSource
    @ParameterizedTest
    void selenideSiteShoulDisplayCorrectButtons(Language language, List<String> expectedButtons, Person person){
        $$("#languages a").findBy(text(language.name())).click();
        $$(".main-menu-pages a").filter(visible)
                .shouldHave(texts(expectedButtons));
    }

}
