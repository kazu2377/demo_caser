package com.example.demo_caser.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AgeCalculator {
    /**
     * 生年月日から現在までの経過日数を計算
     * @param birthDate 生年月日（LocalDate形式）
     * @return 経過日数
     */
    public static long getDaysSinceBirth(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        return ChronoUnit.DAYS.between(birthDate, today);
    }

    /**
     * 文字列形式の生年月日から経過日数を計算
     * @param birthDateStr 生年月日（yyyy-MM-dd形式）
     * @return 経過日数
     */
    public static long getDaysSinceBirth(String birthDateStr) {
        LocalDate birthDate = LocalDate.parse(birthDateStr);
        return getDaysSinceBirth(birthDate);
    }

    /**
     * 年月日を個別に指定して経過日数を計算
     * @param year 年
     * @param month 月（1-12）
     * @param day 日
     * @return 経過日数
     */
    public static long getDaysSinceBirth(int year, int month, int day) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        return getDaysSinceBirth(birthDate);
    }
}