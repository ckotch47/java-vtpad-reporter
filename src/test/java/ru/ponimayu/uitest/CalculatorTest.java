package ru.ponimayu.uitest;

import ru.vtpad.reporter.TestResultLoggerExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(TestResultLoggerExtension.class)
@DisplayName("My display name")
public class CalculatorTest {
    @Test
    @DisplayName("1 + 1 = 2")
    void addsTwoNumbersN() {
        Calculator calculator = new Calculator();
        assertEquals(2, calculator.add(1, 1), "1 + 1 should equal 2");
    }
}
