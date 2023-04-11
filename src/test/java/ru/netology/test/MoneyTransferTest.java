package ru.netology.test;

import lombok.val;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;

public class MoneyTransferTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @BeforeEach
    void setUp() {
        loginPage = open("http://localhost:9999", LoginPage.class);
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationCode = DataHelper.getVerificationCode();
        dashboardPage = verificationPage.validVerify(verificationCode);
    }

    @Test
    void shouldTransferMoneyFromFirstToSecondCard() {
        val firstCardInfo = DataHelper.getCardFirst();
        val secondCardInfo = DataHelper.getCardSecond();
        val firstCardBalance = dashboardPage.getCardBalance(0);
        val secondCardBalance = dashboardPage.getCardBalance(1);
        val amount = DataHelper.generationRandomAmount(firstCardBalance);
        val expectedBalanceFirstCard = firstCardBalance - amount;
        val expectedBalanceSecondCard = secondCardBalance + amount;
        val transferPage = dashboardPage.selectCardTransfer(secondCardInfo);
        dashboardPage = transferPage.validTransfer(String.valueOf(amount), firstCardInfo);
        val actualBalanceFirstCard = dashboardPage.getCardBalance(0);
        val actualBalanceSecondCard = dashboardPage.getCardBalance(1);
        Assertions.assertEquals(expectedBalanceFirstCard, actualBalanceFirstCard);
        Assertions.assertEquals(expectedBalanceSecondCard, actualBalanceSecondCard);
    }
}
