package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private static final ElementsCollection cards = $$(".list__item div");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement heading = $("[data-test-id=dashboard] ");


    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public int getCardBalance(int index) {
        val text = cards.get(index).getText();
        return extractBalance(text);
    }

    public TopUpCard selectCardTransfer(DataHelper.CardInfo cardInfo) {
        cards.find(text(cardInfo.getNumberCard().substring(15))).$("button").click();
        return new TopUpCard();
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }

}
