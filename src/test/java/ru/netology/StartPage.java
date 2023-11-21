package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class StartPage {
    private final SelenideElement heading = $(".heading").shouldBe(Condition.text("Путешествие дня"));
    private final SelenideElement buttonBuy = $(".button").shouldBe(Condition.text("Купить"));
    private final SelenideElement buttonBuyInCredit = $(".button").shouldBe(Condition.text("Купить в кредит")) ;
    public void startPage() {
        heading.shouldBe(Condition.visible);
    }
    public PaymentGate buy(){
        buttonBuy.click();
        return new PaymentGate();
    }
    public CreditGate buyInCredit(){
        buttonBuyInCredit.click();
        return new CreditGate();
    }
}
