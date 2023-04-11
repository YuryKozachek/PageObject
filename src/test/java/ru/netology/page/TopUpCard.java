package ru.netology.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;

public class TopUpCard {
    private final SelenideElement amountField = $("[data-test-id=amount] input");
    private final SelenideElement fromField = $("[data-test-id=from] input");
    private final SelenideElement toUpButton = $("[data-test-id=action-transfer]");

    public DashboardPage validTransfer(String amountTransfer, DataHelper.CardInfo cardInfo) {
        transferFromCard(amountTransfer, cardInfo);
        return new DashboardPage();
    }

    public void transferFromCard(String amountTransfer, DataHelper.CardInfo cardInfo) {
        amountField.setValue(amountTransfer);
        fromField.setValue(cardInfo.getNumberCard());
        toUpButton.click();
    }
}
