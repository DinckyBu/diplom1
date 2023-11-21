package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentGate {
    private final SelenideElement heading = $$(".heading").findBy(Condition.text("Оплата по карте"));
    SelenideElement form = $(".form");

    private SelenideElement cardNumberField = form.$("input[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = form.$("input[placeholder='08']");
    private final SelenideElement yearField = form.$("input[placeholder='22']");
    private final SelenideElement cardHolderField = form.$("input_type_text");
    private final SelenideElement cvcField = form.$("input[placeholder='999']");
    private final SelenideElement buttonContinue = form.$$("button").findBy(Condition.text("Продолжить"));
    private final SelenideElement notificationGood = form.$(".notification_status_ok");
    private final SelenideElement notificationError = form.$("notification_status_error");

    public void PaymentGate() {
        heading.shouldBe(Condition.visible);
    }

    public void setCardNumber(DataHelper.cardNumber number) {
        cardHolderField.setValue(String.valueOf(number));
    }

    public void setMonth(String month) {
        monthField.setValue(String.valueOf(month));
    }

    public void setYear(String year) {
        yearField.setValue(String.valueOf(year));
    }

    public void setCardHolder(String cardHolder) {
        cardHolderField.setValue(cardHolder);
    }

    public void setCvc(String Cvc) {
        cvcField.setValue(Cvc);
    }

    public void buttonContinueClick() {
        buttonContinue.click();
    }

    public void checkNotificationGood() {
        notificationGood.shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Успешно" + "Операция одобрена Банком"));
    }

    public void checkNotificationError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(15)).shouldHave(Condition.exactText("Ошибка" + "Ошибка! Банк отказал в проведении операции."));
    }

    public void checkNotificationInvalidFormat() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверный формат"));
    }

    public void checkNotificationRequiredField() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    public void checkNotificationIncorrectExpirationDate() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }

    public void checkNotificationCardExpired() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Срок действия карты истек"));
    }

    public void checkNotificationErrorFullNameCardholder() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Укажите имя и фамилию полностью"));
    }

    public void checkNotificationErrorInvalidConditions() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Поле может содержать только кириллицу, латиницу и пробел"));
    }

    public void checkNotificationErrorOverSymbols() {
        $(".input__sub").shouldBe(Condition.visible).shouldHave(Condition.exactText("Неверно указан срок действия карты"));
    }
}
