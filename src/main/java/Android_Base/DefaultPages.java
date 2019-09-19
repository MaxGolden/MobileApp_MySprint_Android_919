package Android_Base;

import io.appium.java_client.android.AndroidElement;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import java.util.List;
import java.util.NoSuchElementException;

import static Listeners_Tests.Listeners_Android.saveScreenshotPNG_Allure;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;
import static Util.Android_Driver_Methods.*;

public class DefaultPages extends MainBase {
    
    @BeforeTest(groups = {"MainBase"})
    @Severity(SeverityLevel.CRITICAL)
    public void pagesFirstLaunch() throws Exception {
        List<AndroidElement> button_cont = findByResourceID_Counts(30, "com.sprint.care.beta:id/continueBtn");
        try {
            if (button_cont.size() > 0) {
                findByResourceID_Click(
                        30,
                        "com.sprint.care.beta:id/continueBtn",
                        true,
                        "Server error - test fail");

                findByAndroidUIAutomator_Click(10, "text(\"CONTINUE\")");
                findByAndroidUIAutomator_Click(10, "text(\"CONTINUE\")");
                System.out.println("INFO: Pre-pages passing ... DONE \n ");
            } else {
                List<AndroidElement> network_title = findByResourceID_Counts(5, "com.sprint.care.beta:id/cardTitle");
                try {
                    if (network_title.size() > 0) {
                        saveScreenshotPNG_Allure(Android_Driver);
                        saveTextLog_Allure("Sprint Network Unavailable ... ... Driver Quitting");
                        Assert.fail();
                        Android_Driver.quit();
                    } else {
                        saveScreenshotPNG_Allure(Android_Driver);
                        saveTextLog_Allure("INFO: SERVER ERROR ... ... Driver Quitting");
                        Assert.fail();
                        Android_Driver.quit();
                    }
                } catch (NoSuchElementException e) {
                    System.out.println("INFO: Error catching: " + e.getMessage());
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
        findByAndroidUIAutomator_Click(30, "text(\"" + notifAccess + "\")");
        Thread.sleep(3000);
    }
}
