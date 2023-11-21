package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {

    // Поле номер карты
    public static cardNumber getValidWorkingCard() {
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4441");
        return cardNumber;
    }

    public static cardNumber getValidBlockedCard() {
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4442");
        return cardNumber;
    }

    public static cardNumber getInvalidFullCard() {
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4444");
        return cardNumber;
    }

    public static cardNumber getInvalidShortCard() {
        cardNumber cardNumber = new cardNumber("4444 4444 4444 4");
        return cardNumber;
    }


    // Поле месяц
    public static String getValidMonth() {

        DateTimeFormatter to = DateTimeFormatter.ofPattern("MM");
        String month = LocalDate.now().format(to);
        return month;
    }

    public static String getInvalidMonthAboveYear() {
        String month = String.valueOf((Math.random() * (86)) + 13);
        return month;
    }

    public static String getInvalidMonthZero() {
        String month = "00";
        return month;
    }

    public static String getInvalidPreviousMonth() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("MM");
        String month = LocalDate.now().minusMonths(1).format(to);
        return month;
    }


    // Поле год
    public static String getValidYear() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("YY");
        int date = Integer.parseInt(LocalDate.now().format(to));
        String year = String.valueOf((Math.random() * (5)) + date);
        return year;
    }

    public static String getCurrentYear(){
        DateTimeFormatter to = DateTimeFormatter.ofPattern("YY");
        String year = LocalDate.now().format(to);
        return year;
    }

    public static String getInvalidYearAboveRange() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("YY");
        int date = 5 + Integer.parseInt(LocalDate.now().format(to));
        int rangeDate = 99 - date;
        String year = String.valueOf((Math.random() * (rangeDate)) + date);
        return year;
    }

    public static String getInvalidYearBelowRange() {
        DateTimeFormatter to = DateTimeFormatter.ofPattern("YY");
        int date = Integer.parseInt(LocalDate.now().format(to)) -1;
        String year = String.valueOf((Math.random() * (date)) + 0);
        return year;
    }


    //    Поле владелец
    public static String getValidCardHolderRus() {
        Faker faker = new Faker(new Locale("ru"));

        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return holder;
    }

    public static String getValidCardHolderENG() {
        Faker faker = new Faker(new Locale("en"));

        String holder = faker.name().firstName() + " " + faker.name().lastName();
        return holder;
    }

    public static String getInvalidCardHolderOnlyNameRus() {
        Faker faker = new Faker(new Locale("ru"));

        String holder = faker.name().firstName();
        return holder;
    }

    public static String getInvalidCardHolderOnlyNameENG() {
        Faker faker = new Faker(new Locale("en"));

        String holder = faker.name().firstName();
        return holder;
    }

    public static String getInvalidCardHolderOnlySurnameRus() {
        Faker faker = new Faker(new Locale("ru"));

        String holder = faker.name().lastName();
        return holder;
    }

    public static String getInvalidCardHolderOnlySurnameENG() {
        Faker faker = new Faker(new Locale("en"));

        String holder = faker.name().lastName();
        return holder;
    }

    public static String getOneLetterRUS() {
        Faker faker = new Faker(new Locale("ru"));
        String randomLetter = faker.lorem().characters(1);
        return randomLetter;
    }

    public static String getOneLetterENG() {
        Faker faker = new Faker(new Locale("en"));
        String randomLetter = faker.lorem().characters(1);
        return randomLetter;
    }


    public static String getInvalidCardHolderWithHyphenRus() {
        Faker faker = new Faker(new Locale("ru"));

        String holder = faker.name().firstName() + "-" + faker.name().lastName();
        return holder;
    }

    public static String getInvalidCardHolderWithHyphenENG() {
        Faker faker = new Faker(new Locale("en"));

        String holder = faker.name().firstName() + "-" + faker.name().lastName();
        return holder;
    }

    public static String getInvalidCardHolderCN() {
        Faker faker = new Faker(new Locale("zh_CN"));

        String holder = faker.name().firstName();
        return holder;
    }

    public static String getRandomSymbol() {
        var alphabet = "!@#$%^&*()~`№;:?[]{}/?|";
        var randomIndex = Math.floor(Math.random() * alphabet.length());
        var randomSymbol = String.valueOf(alphabet.charAt((int) randomIndex));
        return randomSymbol;
    }

    public static String getRandomNumber() {
        String randomNumber = String.valueOf(Math.random() * (9999));
        return randomNumber;
    }

    //    ПОЛЕ CVC/CVV
    public static String getValidCVCCode() {
        String code = String.valueOf(Math.random() * (899) + 100);
        return code;
    }

    public static String getInvalidCVCCodeOneNumber() {
        int number = (int) (Math.random() * (8) + 1);
        String code = String.valueOf(number);
        return code;
    }

    public static String getInvalidCVCCodeTwoNumber() {
        int number = (int) Math.random() * (89) + 10;
        String code = String.valueOf(number);
        return code;
    }


    @Value

    public static class cardNumber {
        String card;
    }
}