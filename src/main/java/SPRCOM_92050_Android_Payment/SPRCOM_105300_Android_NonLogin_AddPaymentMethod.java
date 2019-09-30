package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105300 My Sprint App Android - NonLogin Payment")
public class SPRCOM_105300_Android_NonLogin_AddPaymentMethod extends MainBase {

    private String cardName = "Max";
    private String cardNumber = "4444424444444440";
    private String cardExpire = "09/23";
    private String cardZip = "66600";
    private String cardCVV = "000";

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 1)
    @Description("SPRCOM-105302 Payment - Add new payment method")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105302 Payment - Add new payment method")
    public void SPRCOM_105302() throws Exception
    {
        SPRCOM_105302_Step1();
        SPRCOM_105302_Step2();
        SPRCOM_105302_Step3();
        SPRCOM_105302_Step4();
        SPRCOM_105302_Step5();
        SPRCOM_105302_Step6();
        SPRCOM_105302_Step7();
        SPRCOM_105302_Step8();
    }

    @Step("1. Tap the ‘Make a payment’ on the MainPage")
    private void SPRCOM_105302_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        findByResourceID_Click(
                30,
                "com.sprint.care.beta:id/action_button",
                true,
                "Connection failed - No 'Make a payment' button showed");
    }

    @Step("2. Tap ‘Add payment method’ on the page")
    private void SPRCOM_105302_Step2()
    {
        saveTextLog_Allure_er("Payment method dialog is displayed");
        findByResourceID_Click(
                10,
                "com.sprint.care.beta:id/paymentMethod_et",
                true,
                "Unknown issues");
    }

    @Step("3. Tap ‘Add a new payment method’ and tap OK")
    private void SPRCOM_105302_Step3()
    {
        saveTextLog_Allure_er("Card info page is displayed");
        findByResourceID_Click(
                10,
                "com.sprint.care.beta:id/selector_rb");
        findByResourceID_Click(
                5,
                "com.sprint.care.beta:id/positive_btn");
    }

    @Step("4. Enter card info")
    private void SPRCOM_105302_Step4()
    {
        saveTextLog_Allure_er("Card info is entered");
        findByResourceID_SendKey(10, "com.sprint.care.beta:id/tvNameOnCard", cardName);
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/cardNumberTextField", cardNumber);
        findByResourceID_Click(5, "com.sprint.care.beta:id/expirationDateTextField");
        findByResourceID_Click(5, "com.sprint.care.beta:id/ok_button");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/zipCodeTextField", cardZip);
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/securityCodeTextField", cardCVV);


    }

    @Step("5. Scroll down and tap ‘Save payment method’ and save")
    private void SPRCOM_105302_Step5()
    {
        saveTextLog_Allure_er("My payment methods page is displayed");
        findByResourceID_ScrollDown(
                "com.sprint.care.beta:id/poof_rb_yes",
                "com.sprint.care.beta:id/zipCodeTextField");
        findByResourceID_Click(5, "com.sprint.care.beta:id/poof_rb_yes");
        findByResourceID_Click(5, "com.sprint.care.beta:id/savePaymentCheckBox");
        findByResourceID_Click(5, "com.sprint.care.beta:id/save");
    }

    @Step("6. Error with duplicate payment method added and tap OK and navigate back")
    private void SPRCOM_105302_Step6() throws Exception
    {
        saveTextLog_Allure_er("Back to payment page");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/dialogTitle_tv")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
            navigateBack(1, 1);
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
        }
    }

    @Step("7. Google save credit card click ‘NO THANKS’")
    private void SPRCOM_105302_Step7() throws Exception
    {
        saveTextLog_Allure_er("Back to payment page");
        if(findByResourceID_Exist(5, "android:id/autofill_save_no")) {
            findByResourceID_Click(3, "android:id/autofill_save_no");
        }
    }

    @Step("8. Back to Main page")
    private void SPRCOM_105302_Step8() throws Exception
    {
        saveTextLog_Allure_er("Main page is displayed");
        navigateBack(5, 1);
    }
}
