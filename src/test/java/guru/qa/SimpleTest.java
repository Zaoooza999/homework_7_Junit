package guru.qa;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

@DisplayName("Класс с простыми тестами")
public class SimpleTest {
    @AfterEach
    void afterEach(){
        Selenide.closeWebDriver();
    }

    @Disabled
    @DisplayName("Первый простой тест")
    @Test
    void simpleTest(){
        System.out.println("1");
    }

    @DisplayName("Первый простой тест1")
    @Test
    void simpleTest1(){
        System.out.println("2");
    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                // с первым запуском тест получит в виде аргументов строки и список
                Arguments.of("first string", List.of(42, 13)),
                // со вторым запуском уже другую строку и список
                Arguments.of("second string", List.of(1, 2))
        );
    }

    // Аннотация поставщика данных, помним про имя
    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    // Метод теста. В этом случае просто выводит в консоль аргументы
    void methodSourceExampleTest(String string, List<Integer> list) {
        System.out.println(string + " and list: " + list);
    }

}