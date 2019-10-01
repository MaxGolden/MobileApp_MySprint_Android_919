package SPRCOM_92052_Android_Sales;

import Android_Base.MainBase;
import io.qameta.allure.*;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92052 My Sprint App Android - Sales")
@Feature("SPRCOM-105700 My Sprint App Android - LoginAO Sales")
public class SPRCOM_105700_Android_LoginAO_Sales_MainPage_UpgradeAO_Cart extends MainBase {

    public Float Monthly;
    public Float Today;

    @Test(groups = {"LoginAO_Sales"}, priority = 6, dataProvider = "DeviceOption",
            dataProviderClass = Data.Upgrade_DeviceOptions.class)
    @Description("My Sprint app Login - Sales: 'upgrade this device' on the MainPage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105706 MainPage: Upgrade Device and add to Cart")
    public void SPRCOM_105706(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption, String CurrentPhone) throws Exception
    {
        SPRCOM_105706_Step1();
        SPRCOM_105706_Step2(model);
        SPRCOM_105706_Step3();
        SPRCOM_105706_Step4(planOption);
        SPRCOM_105706_Step5(CurrentPhone);
        SPRCOM_105706_Step6(phonePlan);

    }

    @Step("1. Make sure MainPage loaded and Tap ‘Upgrade this device’")
    private void SPRCOM_105706_Step1()
    {
        saveTextLog_Allure_er("Bottom Sheet should pop up with different type of devices");
        if(findByResourceID_Exist(20, "com.sprint.care.beta:id/action_button")) {
            findByResourceID_ScrollDown(false, 300);
        }
        if(findByResourceID_Exist(5, "com.sprint.care.beta:id/action_button_filled")) {
            findByResourceID_Click(1, "com.sprint.care.beta:id/action_button_filled");
        }
        if(findByResourceID_Exist(1, "com.sprint.care.beta:id/action_button_outlined")) {
            findByResourceID_Click(1, "com.sprint.care.beta:id/action_button_outlined");
        }
        if(findByResourceID_Exist(1, "com.sprint.care.beta:id/upgrade_btn")) {
            findByResourceID_Click(1, "com.sprint.care.beta:id/upgrade_btn");
        }
    }
    
    @Step("2. Default filter, tap the model name on the page")
    private void SPRCOM_105706_Step2(String model)
    {
        saveTextLog_Allure_er("Device Details page is displayed");

        findByAndroidUIAutomator_Click(
                60,
                "text(\"" + model + "\")",
                true,
                "Element not found");
    }

    @Step("3. Make sure Continue button is enabled and tap it")
    private void SPRCOM_105706_Step3()
    {
        saveTextLog_Allure_er("Payment page is displayed");
        findByResourceID_Click(30, "com.sprint.care.beta:id/continueButton",
                true, "Error");
    }

    @Step("4. Use Data Provider with three options and tap continue")
    private void SPRCOM_105706_Step4(String planOption) throws Exception
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
        findByAndroidUIAutomator_Click(15, "text(\"" + full_planOption + "\")",
                true, "Error");
        findByResourceID_Click(5, "com.sprint.care.beta:id/continueButton");
    }


    @Step("5. Tap the option of current handling")
    private void SPRCOM_105706_Step5(String currentPhone) throws Exception
    {
        saveTextLog_Allure_er("Next page is displayed");
        findByAndroidUIAutomator_Click(
                30,
                "text(\"" + currentPhone + "\")",
                true,
                "Element having issue");
        findByResourceID_Click(5, "com.sprint.care.beta:id/continueButton");
    }

    @Step("6. Unknown Experience - Error - Back to main page")
    private void SPRCOM_105706_Step6(String planOption) throws Exception
    {
        saveTextLog_Allure_er("---Main page is displayed");
        if(findByAndroidUIAutomator_Exist(10, "text(\"Error\")")) {
            findByResourceID_Click(30, "com.sprint.care.beta:id/positive_btn",
                    true, "Error");
            assertFail_toMainPage(5, "ERROR: error message");
        }
    }
}
