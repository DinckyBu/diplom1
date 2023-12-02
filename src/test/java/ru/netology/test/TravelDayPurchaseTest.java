package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.StartPage;
import ru.netology.data.DataHelper;
import ru.netology.data.SQLHelper;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TravelDayPurchaseTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void teardown() {
        SQLHelper.
                cleanDatabase();
    }

    @BeforeEach
    void setup() {
        open("http://localhost:8080");
    }
    // Positive tests

    @Test
    @Order(1)
    @DisplayName("All fields are filled with valid data(PaymentPage, RUS)")
    void paymentFormShouldHaveAllValidFieldRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(2)
    @DisplayName("All fields are filled with valid data(PaymentPage, ENG)")
    void paymentFormShouldHaveAllValidFieldENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationGood();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(3)
    @DisplayName("All fields are filled with valid data(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditGate = start.buyInCredit();

        CreditGate.setCardNumber(DataHelper.getValidWorkingCard());
        CreditGate.setMonth(DataHelper.getValidMonth());
        CreditGate.setYear(DataHelper.getValidYear());
        CreditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        CreditGate.setCvc(DataHelper.getValidCVCCode());
        CreditGate.buttonContinueClick();
        CreditGate.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(4)
    @DisplayName("All fields are filled with valid data(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldENG() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditGate = start.buyInCredit();

        CreditGate.setCardNumber(DataHelper.getValidWorkingCard());
        CreditGate.setMonth(DataHelper.getValidMonth());
        CreditGate.setYear(DataHelper.getValidYear());
        CreditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        CreditGate.setCvc(DataHelper.getValidCVCCode());
        CreditGate.buttonContinueClick();
        CreditGate.checkNotificationGood();
        String actual = SQLHelper.getCreditStatus();
        String expected = "APPROVED";
        assertEquals(expected, actual);
    }
    @Test //bug
    @Order(5)
    @DisplayName("All fields are filled with valid data and invalid card(PaymentGate, RUS)")
    void paymentFormShouldHaveAllValidFieldBlockedCardRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidBlockedCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test //bug
    @Order(6)
    @DisplayName("All fields are filled with valid data and invalid card(PaymentGate, ENG)")
    void paymentFormShouldHaveAllValidFieldBlockedCardENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidBlockedCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test
    @Order(7) //bug
    @DisplayName("All fields are filled with valid data and invalid card(CreditPage, RUS)")
    void creditFormShouldHaveAllValidFieldBlockedCardRUS() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditGate = start.buyInCredit();

        CreditGate.setCardNumber(DataHelper.getValidBlockedCard());
        CreditGate.setMonth(DataHelper.getValidMonth());
        CreditGate.setYear(DataHelper.getValidYear());
        CreditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        CreditGate.setCvc(DataHelper.getValidCVCCode());
        CreditGate.buttonContinueClick();
        CreditGate.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    @Test //bug
    @Order(8)
    @DisplayName("All fields are filled with valid data and invalid card(CreditPage, ENG)")
    void creditFormShouldHaveAllValidFieldBlockedCardENG() {
        StartPage start = new StartPage();
        start.startPage();
        var CreditGate = start.buyInCredit();

        CreditGate.setCardNumber(DataHelper.getValidBlockedCard());
        CreditGate.setMonth(DataHelper.getValidMonth());
        CreditGate.setYear(DataHelper.getValidYear());
        CreditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        CreditGate.setCvc(DataHelper.getValidCVCCode());
        CreditGate.buttonContinueClick();
        CreditGate.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        String expected = "DECLINED";
        assertEquals(expected, actual);
    }

    // negative tests
    @Test //bug
    @Order(1)
    @DisplayName("All fields are empty(PaymentGate)")
    void paymentFormShouldHaveAllEmptyField() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test //bug
    @Order(2)
    @DisplayName("All fields are empty(CreditGate)")
    void CreditFormShouldHaveAllEmptyField() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(3)
    @DisplayName("Number card empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(4)
    @DisplayName("Number card empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyNumberCardRestFieldValidENg() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(5)
    @DisplayName("Number card empty,rest fields are filled with valid data(CreditGate)")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(6)
    @DisplayName("Number card empty,rest fields are filled with valid data(CreditGate)")
    void CreditFormShouldHaveEmptyNumberCardRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(7)
    @DisplayName("Month empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyMonthRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(8)
    @DisplayName("Month empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyMonthRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(9)
    @DisplayName("Month empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyMonthRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(10)
    @DisplayName("Month empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyMonthRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(11)
    @DisplayName("Year empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(12)
    @DisplayName("Year empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(13)
    @DisplayName("Year empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(14)
    @DisplayName("Year empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(15)
    @DisplayName("Cardholder empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyCardholderRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(16)
    @DisplayName("Cardholder empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyCardholderRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(17)
    @DisplayName("CVC code empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyCVCRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(18)
    @DisplayName("CVC code empty,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveEmptyCVCRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationRequiredField();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(19)
    @DisplayName("CVC code empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyCVCRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(20)
    @DisplayName("CVC code empty,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveEmptyCVCRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationRequiredField();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(21)
    @DisplayName("Invalid card number,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getInvalidFullCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(22)
    @DisplayName("Invalid card number,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getInvalidFullCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationError();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(23)
    @DisplayName("Invalid card number,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getInvalidFullCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(24)
    @DisplayName("Invalid card number,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buy();


        creditGate.setCardNumber(DataHelper.getInvalidFullCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationError();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(25)
    @DisplayName("Invalid short card number,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidShortCardNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getInvalidShortCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(26)
    @DisplayName("Invalid short card number,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidShortCardNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getInvalidShortCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(27)
    @DisplayName("Invalid short card number,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidShortCardNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getInvalidShortCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(28)
    @DisplayName("Invalid short card number,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidShortCardNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();


        creditGate.setCardNumber(DataHelper.getInvalidShortCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(29)
    @DisplayName("Invalid month above year,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidMonthAboveYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();


        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(30)
    @DisplayName("Invalid month above year,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidMonthAboveYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidMonthAboveYear());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(31)
    @DisplayName("Invalid month above year,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidMonthAboveYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(32)
    @DisplayName("Invalid month above year,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidMonthAboveYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidMonthAboveYear());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(33)
    @DisplayName("Invalid previous month,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidPreviousMonthRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationCardExpired();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(34)
    @DisplayName("Invalid previous month,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidPreviousMonthRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidPreviousMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationCardExpired();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(35)
    @DisplayName("Invalid previous month,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidPreviousMonthRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidPreviousMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationCardExpired();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(36)
    @DisplayName("Invalid previous month,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidPreviousMonthRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidPreviousMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationCardExpired();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(37)
    @DisplayName("Invalid month zero,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidMonthZeroRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidMonthZero());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorOverSymbols();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(38)
    @DisplayName("Invalid month zero,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidMonthZeroRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getInvalidMonthZero());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorOverSymbols();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(39)
    @DisplayName("Invalid month zero,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidMonthZeroRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidMonthZero());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorOverSymbols();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(40)
    @DisplayName("Invalid month zero,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidMonthZeroRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getInvalidMonthZero());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorOverSymbols();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(41)
    @DisplayName("Invalid year above range,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidYearAboveRangeRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getInvalidYearAboveRange());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(42)
    @DisplayName("Invalid year above range,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidYearAboveRangeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getInvalidYearAboveRange());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(43)
    @DisplayName("Invalid year above range,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidYearAboveRangeRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getInvalidYearAboveRange());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(44)
    @DisplayName("Invalid year above range,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidTearAboveRangeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getInvalidYearAboveRange());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(45)
    @DisplayName("Invalid year below range,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidYearBelowRangeRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getInvalidYearBelowRange());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationCardExpired();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(46)
    @DisplayName("Invalid year below range,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidYearBelowRangeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getInvalidYearBelowRange());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationCardExpired();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(47)
    @DisplayName("Invalid year below range,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidYearBelowRangeRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getInvalidYearBelowRange());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationCardExpired();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(48)
    @DisplayName("Invalid year below range,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidYearBelowRangeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getInvalidYearBelowRange());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationCardExpired();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(49)
    @DisplayName("Current year,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveCurrentYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getCurrentYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(50)
    @DisplayName("Current year,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveCurrentYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getCurrentYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(51)
    @DisplayName("Current year,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveCurrentYearRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getCurrentYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(52)
    @DisplayName("Current year,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveCurrentYearRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getCurrentYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationIncorrectExpirationDate();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(53)
    @DisplayName("Invalid cardholder only name,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOnlyNameRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(54)
    @DisplayName("Invalid cardholder only name,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOnlyNameRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(55)
    @DisplayName("Invalid cardholder only name,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOnlyNameRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(56)
    @DisplayName("Invalid cardholder only name,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOnlyNameRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(57)
    @DisplayName("Invalid cardholder only surname,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOnlySurnameRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlySurnameRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(58)
    @DisplayName("Invalid cardholder only surname,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOnlySurnameRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlySurnameENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(59)
    @DisplayName("Invalid cardholder only surname,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOnlySurnameRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlySurnameRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(60)
    @DisplayName("Invalid cardholder only surname,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOnlySurnameRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlySurnameENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(61)
    @DisplayName("Invalid cardholder one letter,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOneLetterRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getOneLetterRUS());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(62)
    @DisplayName("Invalid cardholder one letter,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderOneLetterRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getOneLetterENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(63)
    @DisplayName("Invalid cardholder one letter,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOneLetterRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getOneLetterRUS());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(64)
    @DisplayName("Invalid cardholder one letter,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderOneLetterRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getOneLetterENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorFullNameCardholder();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(65)
    @DisplayName("Invalid cardholder with hyphen,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderWithHyphenRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderWithHyphenRus());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(66)
    @DisplayName("Invalid cardholder with hyphen,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderWithHyphenRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderWithHyphenENG());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(67)
    @DisplayName("Invalid cardholder with hyphen,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderWithHyphenRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderWithHyphenRus());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(68)
    @DisplayName("Invalid cardholder with hyphen,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderWithHyphenRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderWithHyphenENG());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(69)
    @DisplayName("Invalid cardholder,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderRestFieldValidCN() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderCN());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(70)
    @DisplayName("Invalid cardholder,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderRestFieldValidCN() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderCN());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(71)
    @DisplayName("Invalid cardholder random symbol,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderWithRandomSymbolRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getRandomSymbol());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(72)
    @DisplayName("Invalid cardholder with random symbol,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderWithRandomSymbolRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getRandomSymbol());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(73)
    @DisplayName("Invalid cardholder random number,rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCardHolderWithRandomNumberRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getRandomNumber());
        paymentGate.setCvc(DataHelper.getValidCVCCode());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(74)
    @DisplayName("Invalid cardholder with random number,rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCardHolderWithRandomNumberRestFieldValid() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getRandomNumber());
        creditGate.setCvc(DataHelper.getValidCVCCode());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationErrorInvalidConditions();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(75)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCVCCodeTwoNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(76)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCVCCodeTwoNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        paymentGate.setCvc(DataHelper.getInvalidCVCCodeTwoNumber());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(77)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCVCCodeTwoNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getInvalidCVCCodeTwoNumber());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertNull(actual);
    }

    @Test
    @Order(78)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCVCCodeTwoNumberRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        creditGate.setCvc(DataHelper.getInvalidCVCCodeTwoNumber());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(79)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCVCCodeOneNumberRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getValidCardHolderRus());
        paymentGate.setCvc(DataHelper.getInvalidCVCCodeOneNumber());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(80)
    @DisplayName("Invalid CVC code? rest fields are filled with valid data(PaymentGate)")
    void paymentFormShouldHaveInvalidCVCCodeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var paymentGate = start.buy();

        paymentGate.setCardNumber(DataHelper.getValidWorkingCard());
        paymentGate.setMonth(DataHelper.getValidMonth());
        paymentGate.setYear(DataHelper.getValidYear());
        paymentGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        paymentGate.setCvc(DataHelper.getInvalidCVCCodeOneNumber());
        paymentGate.buttonContinueClick();
        paymentGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getPaymentStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(81)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCVCCodeRestFieldValidRus() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getValidCardHolderRus());
        creditGate.setCvc(DataHelper.getInvalidCVCCodeOneNumber());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }

    @Test
    @Order(82)
    @DisplayName("Invalid CVC code, rest fields are filled with valid data(CreditGate)")
    void creditFormShouldHaveInvalidCVCCodeRestFieldValidENG() {
        StartPage start = new StartPage();
        start.startPage();
        var creditGate = start.buyInCredit();

        creditGate.setCardNumber(DataHelper.getValidWorkingCard());
        creditGate.setMonth(DataHelper.getValidMonth());
        creditGate.setYear(DataHelper.getValidYear());
        creditGate.setCardHolder(DataHelper.getInvalidCardHolderOnlyNameENG());
        creditGate.setCvc(DataHelper.getInvalidCVCCodeOneNumber());
        creditGate.buttonContinueClick();
        creditGate.checkNotificationInvalidFormat();
        String actual = SQLHelper.getCreditStatus();
        assertEquals(null, actual);
    }
}
