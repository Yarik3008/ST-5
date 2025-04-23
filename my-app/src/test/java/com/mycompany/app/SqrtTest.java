package com.mycompany.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class SqrtTest {

    // Тесты для конструктора
    @Test
    @DisplayName("Конструктор должен инициализировать поле arg")
    public void constructorShouldInitializeArg() {
        double expected = 4.0;
        Sqrt sqrt = new Sqrt(expected);
        assertEquals(expected, sqrt.arg, "Поле arg должно быть инициализировано значением 4.0");
    }

    // Тесты для метода average
    @ParameterizedTest
    @CsvSource({
        "2.0, 4.0, 3.0",
        "0.0, 0.0, 0.0",
        "-2.0, 2.0, 0.0",
        "10.0, 20.0, 15.0",
        "1.5, 2.5, 2.0"
    })
    @DisplayName("average должен правильно вычислять среднее значение")
    public void averageShouldCalculateCorrectly(double x, double y, double expected) {
        Sqrt sqrt = new Sqrt(1.0); // значение не важно для теста average
        assertEquals(expected, sqrt.average(x, y), 0.0000001, 
                "Среднее значение " + x + " и " + y + " должно быть " + expected);
    }

    // Тесты для метода good
    @Test
    @DisplayName("good должен возвращать true для точного корня")
    public void goodShouldReturnTrueForPreciseSquareRoot() {
        Sqrt sqrt = new Sqrt(4.0);
        assertTrue(sqrt.good(2.0, 4.0), "2.0 * 2.0 = 4.0 с высокой точностью");
    }

    @Test
    @DisplayName("good должен возвращать false для неточного значения")
    public void goodShouldReturnFalseForImpreciseValue() {
        Sqrt sqrt = new Sqrt(4.0);
        assertFalse(sqrt.good(1.9, 4.0), "1.9 * 1.9 не равно 4.0 с требуемой точностью");
    }

    @Test
    @DisplayName("good должен работать с малыми значениями в пределах допустимой дельты")
    public void goodShouldWorkWithSmallValuesWithinDelta() {
        Sqrt sqrt = new Sqrt(0.0001);
        assertTrue(sqrt.good(0.01, 0.0001), "0.01 * 0.01 = 0.0001 с высокой точностью");
    }

    // Тесты для метода iter
    @Test
    @DisplayName("iter должен возвращать точное значение, если начальное приближение хорошее")
    public void iterShouldReturnGuessWhenGuessIsPrecise() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(2.0, sqrt.iter(2.0, 4.0), 0.0000001, 
                "iter должен вернуть 2.0, когда уже имеем точное значение");
    }

    @Test
    @DisplayName("iter должен корректно находить корень из 4")
    public void iterShouldFindCorrectSquareRootOfFour() {
        Sqrt sqrt = new Sqrt(4.0);
        assertEquals(2.0, sqrt.iter(1.0, 4.0), 0.0000001, 
                "Корень из 4 должен быть 2.0");
    }

    @Test
    @DisplayName("iter должен корректно находить корень из 9")
    public void iterShouldFindCorrectSquareRootOfNine() {
        Sqrt sqrt = new Sqrt(9.0);
        assertEquals(3.0, sqrt.iter(1.0, 9.0), 0.0000001, 
                "Корень из 9 должен быть 3.0");
    }

    @Test
    @DisplayName("iter должен корректно находить корень из 2")
    public void iterShouldFindCorrectSquareRootOfTwo() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(Math.sqrt(2.0), sqrt.iter(1.0, 2.0), 0.0000001, 
                "Корень из 2 должен быть приблизительно 1.414...");
    }

    // Тесты для метода calc
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 4.0, 9.0, 16.0, 25.0, 36.0, 49.0, 64.0, 81.0, 100.0})
    @DisplayName("calc должен корректно вычислять корни из идеальных квадратов")
    public void calcShouldComputeCorrectSquareRootsOfPerfectSquares(double value) {
        Sqrt sqrt = new Sqrt(value);
        assertEquals(Math.sqrt(value), sqrt.calc(), 0.0000001, 
                "Квадратный корень из " + value + " должен быть " + Math.sqrt(value));
    }

    @Test
    @DisplayName("calc должен корректно вычислять корень из 2")
    public void calcShouldCalculateSquareRootOfTwo() {
        Sqrt sqrt = new Sqrt(2.0);
        assertEquals(Math.sqrt(2.0), sqrt.calc(), 0.0000001, 
                "Корень из 2 должен быть приблизительно 1.414...");
    }

    @Test
    @DisplayName("calc должен корректно вычислять корень из очень маленького числа")
    public void calcShouldCalculateSquareRootOfSmallNumber() {
        double value = 0.0001;
        Sqrt sqrt = new Sqrt(value);
        assertEquals(Math.sqrt(value), sqrt.calc(), 0.0000001, 
                "Корень из 0.0001 должен быть 0.01");
    }

    @Test
    @DisplayName("calc должен корректно вычислять корень из большого числа")
    public void calcShouldCalculateSquareRootOfLargeNumber() {
        double value = 1000000.0;
        Sqrt sqrt = new Sqrt(value);
        assertEquals(Math.sqrt(value), sqrt.calc(), 0.0000001, 
                "Корень из 1000000 должен быть 1000");
    }

    @Test
    @DisplayName("calc должен обрабатывать нулевой аргумент")
    public void calcShouldHandleZero() {
        Sqrt sqrt = new Sqrt(0.0);
        assertEquals(0.0, sqrt.calc(), 0.0000001, 
                "Корень из 0 должен быть 0");
    }

    // Дополнительные краевые случаи для метода calc
    @Test
    @DisplayName("calc должен корректно вычислять корень из нецелого числа")
    public void calcShouldCalculateSquareRootOfNonIntegerNumber() {
        double value = 2.25;
        Sqrt sqrt = new Sqrt(value);
        assertEquals(1.5, sqrt.calc(), 0.0000001, 
                "Корень из 2.25 должен быть 1.5");
    }

    @Test
    @DisplayName("calc должен выбрасывать ArithmeticException для отрицательного аргумента")
    public void calcShouldThrowExceptionForNegativeArgument() {
        Sqrt sqrt = new Sqrt(-1.0);
        assertThrows(ArithmeticException.class, () -> sqrt.calc(), 
                "Метод calc должен выбрасывать исключение для отрицательного аргумента");
    }

    // Проверка комбинаций методов
    @Test
    @DisplayName("delta должна обеспечивать требуемую точность")
    public void deltaShouldProvideRequiredPrecision() {
        Sqrt sqrt = new Sqrt(2.0);
        double result = sqrt.calc();
        assertTrue(Math.abs(result * result - 2.0) < sqrt.delta, 
                "Результат в квадрате должен отличаться от исходного числа меньше чем на дельту");
    }
}