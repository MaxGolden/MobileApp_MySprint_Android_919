package SPRCOM_92051_Android_LoginAO;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.*;
import org.openqa.selenium.Point;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure_er;
import static Util.Android_Driver_Methods.*;

@Listeners(Listeners_Tests.Listeners_Android.class)
@Epic("SPRCOM-92051 My Sprint App Android - LOGIN")
@Feature("SPRCOM-105400 My Sprint App Android - LoginAO")
public class SPRCOM_105400_Android_LoginAO_Login extends MainBase {
    
    @Test(groups = {"LoginAO"}, priority = 3, dataProvider = "AccountOwner",
            dataProviderClass = Data.AccountOwner_Info.class)
    @Description("My Sprint app login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105402 My Sprint App Android - MDN Change")
    public void SPRCOM_105402(String Env, String MDN, String ACCusername, String ACCpassword) throws Exception
    {
        SPRCOM_105402_Step1();
        SPRCOM_105402_Step2();
        SPRCOM_105402_Step3(Env, MDN);
        SPRCOM_105402_Step4();
        SPRCOM_105402_Step5();
    }

    @Step("1. Tap the profile icon in the upper right corner")
    private void SPRCOM_105402_Step1()
    {
        saveTextLog_Allure_er("Drop down sheet is displayed");
        if(findByResourceID_Exist(20, "com.sprint.care.beta:id/action_button")) {
            findByClassName_Click(5, "android.widget.ImageView", 0);
        } else {
            allure_mes("No main page showed up");
            Assert.fail();
        }
    }

    @Step("2. Tap Settings in the drop down sheet")
    private void SPRCOM_105402_Step2()
    {
        saveTextLog_Allure_er("Setting page is displayed");
        findByAndroidUIAutomator_Click(5, "text(\"Settings\")");
    }

    @Step("3. Scroll down and change MDN")
    private void SPRCOM_105402_Step3(String Env, String MDN) throws Exception
    {
        saveTextLog_Allure_er("New MDN is entered");
        Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Point point1 = Android_Driver.findElementById("com.sprint.care.beta:id/about_title").getLocation();
        TouchAction t = new TouchAction(Android_Driver);
        Thread.sleep(500);
        for (int i = 0; i < 10; i++) {
            t.tap(PointOption.point(point1)).release().perform();
        }

        findByResourceID_ScrollDown(
                "com.sprint.care.beta:id/about_details",
                "com.sprint.care.beta:id/privacy_policy_title"
        );

        findByResourceID_ScrollDown(
                "com.sprint.care.beta:id/serviceSwitcher_btn",
                "com.sprint.care.beta:id/license_acknowledgement_title"
        );

        findByResourceID_Clear(5, "com.sprint.care.beta:id/mdn_text");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/mdn_text", MDN);
        findByResourceID_Click(5, "com.sprint.care.beta:id/environment_text");
        findByAndroidUIAutomator_Click(5, "text(\"" + Env + "\")");
    }

    @Step("4. Click 'set' button on the bottom of the page")
    private void SPRCOM_105402_Step4()
    {
        saveTextLog_Allure_er("First launch page is displayed");
        findByResourceID_Click(5, "com.sprint.care.beta:id/set_btn");
    }
    @Step("5. Tap 4 times of 'Continue' on these pages")
    private void SPRCOM_105402_Step5()
    {
        saveTextLog_Allure_er("Main page is displayed");
        findByResourceID_Click(
                25,
                "com.sprint.care.beta:id/continueBtn",
                true,
                "Server error - test fail");
        findByAndroidUIAutomator_Click(
                10,
                "text(\"CONTINUE\")",
                true,
                "Server error - test fail");
        findByAndroidUIAutomator_Click(
                10,
                "text(\"CONTINUE\")",
                true,
                "Server error - test fail");
    }

    @Test(groups = {"LoginAO"}, dependsOnMethods = {"SPRCOM_105402"}, priority = 2, dataProvider = "AccountOwner",
            dataProviderClass = Data.AccountOwner_Info.class)
    @Description("My Sprint app Login as account owner")
    @Severity(SeverityLevel.CRITICAL)
    @Story("SPRCOM-105404 Test Login with type of account owner")
    public void SPRCOM_105404(String Env, String MDN, String ACCusername, String ACCpassword) throws Exception
    {
        SPRCOM_105404_Step1();
        SPRCOM_105404_Step2();
        SPRCOM_105404_Step3(ACCusername, ACCpassword);
        SPRCOM_105404_Step4();
        SPRCOM_105404_Step5();
    }

    @Step("1. Verify the main page is displayed and tap 'more info' icon")
    private void SPRCOM_105404_Step1()
    {
        saveTextLog_Allure_er("Drop down sheet is displayed");

        if(findByResourceID_Exist(40, "com.sprint.care.beta:id/action_button")) {
            findByClassName_Click(5, "android.widget.ImageView", 0);
        } else {
            saveTextLog_Allure("FAIL: No main page showed up");
            Assert.fail();
        }
    }

    @Step("2. Tap 'Sign in'")
    private void SPRCOM_105404_Step2()
    {
        saveTextLog_Allure_er("Sign in page is displayed");
        if(findByAndroidUIAutomator_Exist(5, "text(\"Sign out\")")) {
            saveTextLog_Allure("User already Signed in");
            findByResourceID_Click(5, "com.sprint.care.beta:id/negative_btn");
            Assert.fail();
        } else {
            findByAndroidUIAutomator_Click(5, "text(\"Sign in\")");
        }
    }

    @Step("3. Enter the username and password into the fields")
    private void SPRCOM_105404_Step3(String ACCusername, String ACCpassword)
    {
        saveTextLog_Allure_er("Username and password are entered");
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/userNameTextField", ACCusername);
        findByResourceID_SendKey(5, "com.sprint.care.beta:id/passwordTextField", ACCpassword);
    }

    @Step("4. Tap 'Sign in' button")
    private void SPRCOM_105404_Step4()
    {
        saveTextLog_Allure_er("Sign in successfully");
        findByResourceID_Click(5, "com.sprint.care.beta:id/loginButton");
    }

    @Step("5. Verify the main page is displayed")
    private void SPRCOM_105404_Step5()
    {
        saveTextLog_Allure_er("Main page is displayed");
        if(!findByResourceID_Exist(35, "com.sprint.care.beta:id/action_button")) {
            saveTextLog_Allure("FAIL: No main page showed up");
            Assert.fail();
        }
    }
}
