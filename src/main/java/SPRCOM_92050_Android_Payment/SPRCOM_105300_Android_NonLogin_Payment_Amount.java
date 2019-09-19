package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105300 My Sprint App Android - NonLogin Payment")
public class SPRCOM_105300_Android_NonLogin_Payment_Amount extends MainBase {

    @Test(groups = {"NonLogin", "MakePayment"}, priority = 2)
    @Description("Basic Payment Functions - Amount Testing")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105304 Payment amount testing")
    public void SPRCOM_105304()
    {
        SPRCOM_105304_Step1();
        SPRCOM_105304_Step2();
        SPRCOM_105304_Step3();
        SPRCOM_105304_Step4();
    }

    @Step("1. Tap the button 'Make a payment' on the MainPage")
    private void SPRCOM_105304_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        findByResourceID_Click(
                30,
                "com.sprint.care.beta:id/action_button",
                true,
                "Connection failed - No 'Make a payment' button showed");
    }

    @Step("2.Type amount less than $1")
    private void SPRCOM_105304_Step2()
    {
        saveTextLog_Allure_er("The error message should show up");
        findByResourceID_Clear(15, "com.sprint.care.beta:id/paymentAmount_et");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/paymentAmount_et", "99");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentContinue_button");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/textinput_error")) {
            if(!findByResourceID_Enable(5, "com.sprint.care.beta:id/paymentContinue_button")) {
                saveTextLog_Allure("Error message showed and continue is disabled - Step passed");
            }
        } else {
            assertFail_toMainPage(3, "Error message did not show - Step fail");
        }
    }

    @Step("3. Type amount more than $2000")
    private void SPRCOM_105304_Step3()
    {
        saveTextLog_Allure_er("The error message should show up");
        findByResourceID_Clear(15, "com.sprint.care.beta:id/paymentAmount_et");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/paymentAmount_et", "200001");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentContinue_button");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/textinput_error")) {
            if(!findByResourceID_Enable(5, "com.sprint.care.beta:id/paymentContinue_button")) {
                saveTextLog_Allure("Error message showed and continue is disabled - Step passed");
            }
        } else {
            assertFail_toMainPage(3, "Error message did not show - Step fail");
        }
    }

    @Step("4. Tap Cancel - End Test")
    private void SPRCOM_105304_Step4()
    {
        saveTextLog_Allure_er("Main page is displayed");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentCancel_button");
        if(!findByResourceID_Exist(10, "com.sprint.care.beta:id/action_button")) {
            allure_mes("Main page did not show - Step fail");
        }
    }

}
