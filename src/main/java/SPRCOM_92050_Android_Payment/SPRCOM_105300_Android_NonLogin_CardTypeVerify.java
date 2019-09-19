package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.*;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105300 My Sprint App Android - NonLogin Payment")
public class SPRCOM_105300_Android_NonLogin_CardTypeVerify extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 3, dataProvider = "CardNumber",
            dataProviderClass = Data.Payment_CardOptions.class)
    @Description("Test the function of payment making with different types of card (Card Number Support)")
    @Severity(SeverityLevel.TRIVIAL)
    @Story("SPRCOM-105306 Payment Test with card types(Number)")
    public void SPRCOM_105306(String cardNum, String cardType, String cardCountry) throws Exception
    {
        SPRCOM_105306_Step1();
        SPRCOM_105306_Step2();
        SPRCOM_105306_Step3(cardNum, cardType);
        SPRCOM_105306_Step4();
        SPRCOM_105306_Step5();
        SPRCOM_105306_Step6();
        SPRCOM_105306_Step7();
        SPRCOM_105306_Step8(cardNum, cardType, cardCountry);
        SPRCOM_105306_Step9();
    }

    @Step("1. Tap the button 'Make a payment' on the MainPage")
    private void SPRCOM_105306_Step1() throws Exception
    {
        saveTextLog_Allure_er("Payment page is displayed");
        findByResourceID_Click(
                30,
                "com.sprint.care.beta:id/action_button",
                true,
                "Connection failed - No 'Make a payment' button showed");
    }

    @Step("2. Tap payment method and tap ‘Add a new payment method’ and ‘OK’")
    private void SPRCOM_105306_Step2()
    {
        saveTextLog_Allure_er("Payment method is displayed");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentMethod_et");
        findByResourceID_Click(5, "com.sprint.care.beta:id/selector_rb");
        findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
    }

    @Step("3. Enter card info from the data provider")
    private void SPRCOM_105306_Step3(String cardNum, String cardType)
    {
        saveTextLog_Allure_er("Card info is entered");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/tvNameOnCard", "Max");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/cardNumberTextField", cardNum);
        findByResourceID_Click(5, "com.sprint.care.beta:id/expirationDateTextField");
        findByResourceID_Click(5, "com.sprint.care.beta:id/ok_button");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/zipCodeTextField", "66600");
        String keyWord;
        if (cardType.equals("American Express")) {
            keyWord = "0000";
        } else {
            keyWord = "000";
        }
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/securityCodeTextField", keyWord);
        findByResourceID_Click(5, "com.sprint.care.beta:id/poof_rb_yes");

        // CVV Icon tap
//        findByResourceID_Click(5, "com.sprint.care.beta:id/securityCodeHelp");
//        findByResourceID_Click(5, "com.sprint.care.beta:id/ok");
    }

    @Step("4. Check if there is error message '- Invalid card number.', and tap 'Continue'")
    private void SPRCOM_105306_Step4() throws Exception
    {
        saveTextLog_Allure_er("Payment page is displayed again");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/textinput_error")) {
            assertFail_toMainPage(3, "Error message showed up - Step fail");
        } else {
            findByResourceID_ScrollDown(
                    "com.sprint.care.beta:id/poof_rb_yes",
                    "com.sprint.care.beta:id/zipCodeTextField");
            findByResourceID_Click(5, "com.sprint.care.beta:id/save");
            // Google Credit Card Options
            findByResourceID_Click(
                    5,
                    "android:id/autofill_save_no",
                    true,
                    "No Google Credit Info Showed");
        }
    }

    @Step("5. Fill the amount text field and tap continue")
    private void SPRCOM_105306_Step5()
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");
        findByResourceID_Clear(5, "com.sprint.care.beta:id/paymentAmount_et");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/paymentAmount_et", "100");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentContinue_button");
    }

    @Step("6. Tap continue if warning dialog is displayed")
    private void SPRCOM_105306_Step6()
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");
        if(findByResourceID_GetText(
                5,
                "com.sprint.care.beta:id/dialogTitle_tv").toLowerCase().equals("warning")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
        }
    }

    @Step("7. Tap 'Authorize' button")
    private void SPRCOM_105306_Step7() throws Exception
    {
        saveTextLog_Allure_er("Payment confirmation dialog is displayed");
        if(findByResourceID_GetText(
                5,
                "com.sprint.care.beta:id/positive_btn").toLowerCase().equals("authorize")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
        }
        if(findByResourceID_GetText(
                5,
                "com.sprint.care.beta:id/dialogTitle_tv").toLowerCase().equals("error")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
            navigateBack(2, 1);
            allure_mes("Duplicate payment found - Error dialog is displayed");
            Assert.fail();
        }
    }

    @Step("8. Tap 'OK' to finish the payment")
    private void SPRCOM_105306_Step8(String cardNum, String cardType, String cardCountry)
    {
        saveTextLog_Allure_er("MainPage is displayed");
        findByResourceID_Click(20, "com.sprint.care.beta:id/positive_btn");
        saveTextLog_Allure("INFO: The Card - " + cardType + " - " + cardNum + " - " + cardCountry + " Test Passed");
    }

    @Step("9. Tap ‘NO, THANKS’ if Rate My Sprint dialog is displayed")
    private void SPRCOM_105306_Step9()
    {
        saveTextLog_Allure_er("MainPage is displayed");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/dialogTitle_tv")) {
            if(findByResourceID_GetText(
                    5,
                    "com.sprint.care.beta:id/dialogTitle_tv").toLowerCase().equals("rate my sprint")) {
                findByResourceID_Click(5, "com.sprint.care.beta:id/negative_btn");
            }
        }
    }
}
