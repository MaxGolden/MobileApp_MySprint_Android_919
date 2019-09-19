package SPRCOM_92050_Android_Payment;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92050 My Sprint App Android - Payment")
@Feature("SPRCOM-105500 My Sprint App Android - LoginAO Payment")
public class SPRCOM_105500_Android_LoginAO_Payment_Basic extends MainBase {
    
    @Test(groups = {"LoginAO_Payment"}, priority = 10)
    @Description("My Sprint App Android LoginAO Payment - Phase 2 - Basic Payment Functional Tests")
    @Severity(SeverityLevel.NORMAL)
    @Story("SPRCOM-105504 Basic Payment Functional Tests")
    public void SPRCOM_105504() throws Exception
    {
        SPRCOM_105504_Step1();
        SPRCOM_105504_Step2();
        SPRCOM_105504_Step3();
        SPRCOM_105504_Step4();
        SPRCOM_105504_Step5();
        SPRCOM_105504_Step6();
        SPRCOM_105504_Step7();
        SPRCOM_105504_Step8();
    }

    @Step("1. Tap the button 'Make a payment' on the MainPage")
    private void SPRCOM_105504_Step1()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        findByResourceID_Click(
                40,
                "com.sprint.care.beta:id/action_button",
                true,
                "Connection failed - No 'Make a payment' button showed");
    }

    @Step("2.Type amount less than $1")
    private void SPRCOM_105504_Step2()
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
            saveTextLog_Allure("Error message did not show - Step fail");
            Assert.fail();
        }
    }

    @Step("3. Type amount more than $2000")
    private void SPRCOM_105504_Step3()
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
            saveTextLog_Allure("Error message did not show - Step fail");
            Assert.fail();
        }
    }

    @Step("4. Fill the amount text field and tap Yes and continue")
    private void SPRCOM_105504_Step4()
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");
        findByResourceID_Clear(5, "com.sprint.care.beta:id/paymentAmount_et");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/paymentAmount_et", "100");
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentContinue_button");
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/poof_rb_yes")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/poof_rb_yes");
        }
        findByResourceID_Click(5, "com.sprint.care.beta:id/paymentContinue_button");
    }

    @Step("5. Tap continue if warning dialog is displayed")
    private void SPRCOM_105504_Step5()
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");
        if(findByResourceID_GetText(
                5,
                "com.sprint.care.beta:id/dialogTitle_tv").toLowerCase().equals("warning")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/positive_btn");
        }
    }

    @Step("6. Tap 'Authorize' button")
    private void SPRCOM_105504_Step6() throws Exception
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

    @Step("7. Tap 'OK' to finish the payment")
    private void SPRCOM_105504_Step7()
    {
        saveTextLog_Allure_er("MainPage is displayed");
        findByResourceID_Click(20, "com.sprint.care.beta:id/positive_btn");
    }

    @Step("8. Tap ‘NO, THANKS’ if Rate My Sprint dialog is displayed")
    private void SPRCOM_105504_Step8()
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
