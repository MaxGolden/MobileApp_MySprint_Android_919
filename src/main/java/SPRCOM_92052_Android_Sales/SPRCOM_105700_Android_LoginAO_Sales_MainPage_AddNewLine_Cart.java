package SPRCOM_92052_Android_Sales;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92052 My Sprint App iOS - Sales")
@Feature("SPRCOM-105700 My Sprint App iOS - LoginAO Sales")
public class SPRCOM_105700_Android_LoginAO_Sales_MainPage_AddNewLine_Cart extends MainBase {

    public Float Monthly;
    public Float Today;

    @Test(groups = {"LoginAO_Sales"}, priority = 5, dataProvider = "DeviceOption",
            dataProviderClass = Data.NewLine_DeviceOptions.class)
    @Description("My Sprint app Login - Sales: 'Upgrade this device' on the MainPage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105702 My Sprint App iOS - MainPage: Upgrade the device")
    public void SPRCOM_105702(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption) throws Exception
    {
        SPRCOM_105702_Step1();
        SPRCOM_105702_Step2(deviceType);
        SPRCOM_105702_Step3(model);
        SPRCOM_105702_Step4();
        SPRCOM_105702_Step5(planOption);
        SPRCOM_105702_Step6(phonePlan);
        SPRCOM_105702_Step7(protectionOption);
        SPRCOM_105702_Step8();

    }

    @Step("1. Make sure MainPage loaded and Tap ‘Add a new device’")
    private void SPRCOM_105702_Step1()
    {
        saveTextLog_Allure_er("Bottom Sheet should pop up with different type of devices");
        findByAndroidUIAutomator_Click(
                30,
                "text(\"Add a new device\")",
                true,
                "Element not found");
    }

    @Step("2. Tap Phones icon")
    private void SPRCOM_105702_Step2(String deviceType)
    {
        saveTextLog_Allure_er("Loading for a long time at first launch(20s) and Shop devices page is displayed");
        findByAndroidUIAutomator_Click(10, "text(\"" + deviceType + "\")");
    }

    @Step("3. Default filter, tap the model name on the page")
    private void SPRCOM_105702_Step3(String model) throws Exception
    {
        saveTextLog_Allure_er("Device Details page is displayed");
        findByAndroidUIAutomator_Click(
                60,
                "text(\"" + model + "\")",
                true,
                "Element not found");
    }

    @Step("4. Make sure Continue button is enabled and tap it")
    private void SPRCOM_105702_Step4()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        if(findByResourceID_Enable(30, "com.sprint.care.beta:id/continueButton")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/continueButton");
        } else {
            assertFail_toMainPage(3, "Continue button is having issue");
        }
    }

    @Step("5. Use Data Provider with three options and tap continue")
    private void SPRCOM_105702_Step5(String planOption) throws Exception
    {
        saveTextLog_Allure_er("Plans page is displayed");

        String full_planOption;
        if(planOption.equals("Lease")) {
            full_planOption = "Sprint Flex 18-mo. lease";
        } else if(planOption.equals("Buy")) {
            full_planOption = "Buy it at full price";
        } else {
            full_planOption = "Buy it with 24 monthly installments";
        }

        findByAndroidUIAutomator_Click(15, "text(\"" + full_planOption + "\")");
        findByResourceID_Click(5, "com.sprint.care.beta:id/continueButton");
    }

    @Step("6. Make sure default Continue button is disabled and click default shared plan ($20)? , tap Continue")
    private void SPRCOM_105702_Step6(String phonePLan)
    {
        saveTextLog_Allure_er("Protection page is displayed");
        findByAndroidUIAutomator_Click(
                60,
                "text(\"" + phonePLan + "\")",
                true,
                "Element not found");

        if(findByResourceID_Enable(5, "com.sprint.care.beta:id/continueButton")) {
            findByResourceID_Click(5, "com.sprint.care.beta:id/continueButton");
        } else {
            assertFail_toMainPage(3, "Continue button is having issue");
        }
    }

    @Step("7. Tap button ‘Add protection’?")
    private void SPRCOM_105702_Step7(String protectionOption) throws InterruptedException
    {
        saveTextLog_Allure_er("Wait for loading(15s), Cart page is displayed OR error happens, and main page is displayed");
        if(protectionOption.equals("Add protection")) {
            findByResourceID_Click(30, "com.sprint.care.beta:id/acceptAndContinue");
        } else {
            findByResourceID_Click(30, "com.sprint.care.beta:id/declineAndContinue");
            findByResourceID_Click(10, "com.sprint.care.beta:id/continue_without_protection_button");
        }
    }

    @Step("8. Verify the value of monthly and today due & back to main page")
    private void SPRCOM_105702_Step8() throws Exception
    {
        saveTextLog_Allure_er("All payment values are correct & Main page is displayed");
        if(findByAndroidUIAutomator_Exist(10, "text(\"Error\")")) {
            findByResourceID_Click(30, "com.sprint.care.beta:id/positive_btn");
            assertFail_toMainPage(5, "ERROR: error message");
        }
        navigateBack(1, 1);
    }
}
