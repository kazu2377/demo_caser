package com.example.demo_caser;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DateTimeException;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.example.demo_caser.util.AgeCalculator;

class AgeCalculatorTest {

    @Test
    @DisplayName("現在日付での基本的なテスト")
    void testCurrentDate() {
        LocalDate today = LocalDate.now();
        assertEquals(0, AgeCalculator.getDaysSinceBirth(today));
    }

    @ParameterizedTest
    @DisplayName("境界値テスト - 年")
    @CsvSource({
        "1900,1,1",    // 最小年付近
        "1999,12,31",  // 世紀末
        "2000,1,1",    // 世紀始め
        "2023,12,31"   // 現在付近
    })
    void testYearBoundaries(int year, int month, int day) {
        long days = AgeCalculator.getDaysSinceBirth(year, month, day);
        assertTrue(days > 0, "過去の日付なので正の値になるはず");
    }

    @ParameterizedTest
    @DisplayName("境界値テスト - 月")
    @CsvSource({
        "2000,1,1",     // 年始め
        "2000,1,31",    // 月末（31日）
        "2000,2,29",    // うるう年2月
        "2000,12,31"    // 年末
    })
    void testMonthBoundaries(int year, int month, int day) {
        long days = AgeCalculator.getDaysSinceBirth(year, month, day);
        assertTrue(days > 0, "過去の日付なので正の値になるはず");
    }

    @ParameterizedTest
    @DisplayName("うるう年のテスト")
    @CsvSource({
        "2000,2,29",    // うるう年
        "2004,2,29",    // うるう年
        "2100,2,28"     // うるう年ではない世紀年
    })
    void testLeapYears(int year, int month, int day) {
        assertDoesNotThrow(() -> {
            AgeCalculator.getDaysSinceBirth(year, month, day);
        });
    }

    @Test
    @DisplayName("文字列形式の日付テスト")
    void testStringDateFormat() {
        // 正常系
        assertDoesNotThrow(() -> {
            AgeCalculator.getDaysSinceBirth("2000-01-01");
        });

        // 異常系
        assertThrows(Exception.class, () -> {
            AgeCalculator.getDaysSinceBirth("invalid-date");
        });
        assertThrows(Exception.class, () -> {
            AgeCalculator.getDaysSinceBirth("2000/01/01");
        });
    }

    @ParameterizedTest
    @DisplayName("無効な日付のテスト")
    @CsvSource({
        "2000,2,30",    // 2月30日は存在しない
        "2001,2,29",    // 非うるう年の2月29日
        "2000,4,31",    // 4月31日は存在しない
        "2000,13,1"     // 13月は存在しない
    })
    void testInvalidDates(int year, int month, int day) {
        assertThrows(DateTimeException.class, () -> {
            AgeCalculator.getDaysSinceBirth(year, month, day);
        });
    }

    @Test
    @DisplayName("未来の日付のテスト")
    void testFutureDates() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        long days = AgeCalculator.getDaysSinceBirth(futureDate);
        assertTrue(days < 0, "未来の日付なので負の値になるはず");
    }

    @ParameterizedTest
    @DisplayName("同値クラステスト - 通常の日付")
    @CsvSource({
        "2000,6,15",    // 普通の日付
        "2000,6,16",    // 連続した日付
        "2000,6,17"     // 連続した日付
    })
    void testEquivalenceClass(int year, int month, int day) {
        long days = AgeCalculator.getDaysSinceBirth(year, month, day);
        assertTrue(days > 0, "過去の日付なので正の値になるはず");
    }

    @Test
    @DisplayName("LocalDate、文字列、個別指定の結果一致テスト")
    void testDifferentInputMethods() {
        LocalDate date = LocalDate.of(2000, 1, 1);
        String dateStr = "2000-01-01";
        
        long daysFromDate = AgeCalculator.getDaysSinceBirth(date);
        long daysFromString = AgeCalculator.getDaysSinceBirth(dateStr);
        long daysFromYMD = AgeCalculator.getDaysSinceBirth(2000, 1, 1);

        assertEquals(daysFromDate, daysFromString, "LocalDateと文字列の結果が一致すべき");
        assertEquals(daysFromDate, daysFromYMD, "LocalDateと年月日指定の結果が一致すべき");
    }

    @Test
    @DisplayName("null入力のテスト")
    void testNullInput() {
        assertThrows(NullPointerException.class, () -> {
            AgeCalculator.getDaysSinceBirth((LocalDate) null);
        });
        assertThrows(NullPointerException.class, () -> {
            AgeCalculator.getDaysSinceBirth((String) null);
        });
    }

    @ParameterizedTest
    @DisplayName("月末日のテスト")
    @CsvSource({
        "2000,1,31",    // 31日ある月
        "2000,4,30",    // 30日ある月
        "2000,2,29",    // うるう年2月
        "2001,2,28"     // 非うるう年2月
    })
    void testMonthEndDays(int year, int month, int day) {
        assertDoesNotThrow(() -> {
            AgeCalculator.getDaysSinceBirth(year, month, day);
        });
    }
}