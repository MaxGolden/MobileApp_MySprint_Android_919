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
public class SPRCOM_105700_Android_LoginAO_Sales_PriorityStatus extends MainBase {

    @Test(groups = {"LoginAO_Sales"}, priority = 7, dataProvider = "DeviceOption",
            dataProviderClass = Data.Upgrade_DeviceOptions.class)
    @Description("My Sprint app Login - Sales: 'upgrade this device' on the MainPage")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105708 MainPage: Priority Status")
    public void SPRCOM_105708(String deviceType, String brand, String model, String planOption, String phonePlan,
                              String protectionOption, String CurrentPhone)
    {
        SPRCOM_105708_Step1();
        SPRCOM_105708_Step2();
        SPRCOM_105708_Step3();
        SPRCOM_105708_Step4();
        SPRCOM_105708_Step5();
    }

    @Step("1. Tap ‘Priority Status’ on the top when it shows up")
    private void SPRCOM_105708_Step1()
    {
        saveTextLog_Allure_er("Priority Status page is displayed");
        findByResourceID_Click(
                30,
                "com.sprint.care.beta:id/ctaTitle",
                true,
                "Priority status did not show up");
    }
    
    @Step("2. Tap ‘Add another’ on the bottom ")
    private void SPRCOM_105708_Step2()
    {
        saveTextLog_Allure_er("Bottom sheet is displayed");
        findByResourceID_Click(
                30,
                "com.sprint.care.beta:id/continueButton",
                true,
                "Element not found");
    }

    @Step("3. Tap Phones/Watches ")
    private void SPRCOM_105708_Step3()
    {
        saveTextLog_Allure_er("Device choices page is displayed");
        findByAndroidUIAutomator_Click(
                5,
                "text(\"Phones\")"); // "text(\"Watches\")"
    }

    @Step("4. Tap ‘add a new phone’/current device")
    private void SPRCOM_105708_Step4()
    {
        saveTextLog_Allure_er("???");
        findByAndroidUIAutomator_Click(
                5,
                "text(\"Add a new phone\")"); // "text(\"Galaxy S8+\")"
    }

    @Step("5. ???")
    private void SPRCOM_105708_Step5()
    {
        saveTextLog_Allure_er("???");
        findByResourceID_Click(
                10,
                "com.sprint.care.beta:id/continueButton");
        assertFail_toMainPage(5, "Error message");
    }


}
