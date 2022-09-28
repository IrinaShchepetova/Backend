package hw3;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.Matchers.equalTo;

public class PalindromeTest {
    private static Palindrome palindrome;

//    @BeforeAll
//    static void beforeAll() {
//        palindrome = new Palindrome();
//    }

    @Test
    @DisplayName("Базовый сценарий")
    void baseTest() {
        assertThat(Palindrome.isPalindrome("мадам"), equalTo(true));
    }

//    @Test
//    @DisplayName("Null")
//    void nullTest() {
//        assertThat(Palindrome.isPalindrome(null), equalTo(false));
//    }
//
//    @Test
//    @DisplayName("Пустая строка")
//    void emptyTest() {
//        assertThat(Palindrome.isPalindrome(""), equalTo(true));
//    }
    @Test
    @DisplayName("Пустая строка или null")
    void badDataTest() {
        assertThatThrownBy(() -> Palindrome.isPalindrome(""))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("String could not be empty");

        assertThatThrownBy(() -> Palindrome.isPalindrome(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("String could not be empty");
    }

    @ParameterizedTest
    @DisplayName("Позитивный тест")
    @ValueSource(strings = {
            "дед",
            "довод",
            "доход",
            "заказ",
            "казак",
            "кок",
            "комок",
            "око",
            "потоп",
            "радар"
    })
    void paramTest(String input) {
        assertThat(Palindrome.isPalindrome(input), equalTo(true));
    }

    @ParameterizedTest
    @DisplayName("Негативный тест")
    @ValueSource(strings = {
            "кукла",
            "пылесос"
    })
    void failTest(String input) {
        assertThat(Palindrome.isPalindrome(input), equalTo(false));
    }


}
