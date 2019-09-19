package Util;

import Android_Base.MainBase;
import io.appium.java_client.android.AndroidElement;
import org.testng.Assert;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.*;

public class Android_Error_Dialog extends MainBase {

    public static void Error_Dialog_Check() {
        Android_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<AndroidElement> error_dialog_title = Android_Driver.findElementsById("com.sprint.care.beta:id/dialogTitle_tv");
        try {
            if(error_dialog_title.size() > 0) {
                String error_title = Android_Driver.findElementById("com.sprint.care.beta:id/dialogTitle_tv").getText();
                if(error_title.equals("Error")) {

                    String error_mes = Android_Driver.findElementById("com.sprint.care.beta:id/dialogMessage_tv").getText();
                    if(error_mes.equals("We are having technical difficulties. Please wait a few minutes and try again.")){
                        saveScreenshotPNG_Allure_Fail(Android_Driver);
                        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
                        System.out.println("Server Error dialog detected, tap OK");
                        saveTextLog_Allure("Server Error dialog detected and tap OK, testing cont.d. Check detail with screenshot attachment");
                    } else {
                        System.out.println("Error dialog detected");
                        saveTextLog_Allure("Error dialog detected, check detail with screenshot attachment");
                        saveScreenshotPNG_Allure_Fail(Android_Driver);
//                        Assert.fail();
                        Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                        Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
                    }

                } else if(error_title.equals("Unlock")) {
                    saveScreenshotPNG_Allure_Fail(Android_Driver);
                    Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    Android_Driver.findElementById("com.sprint.care.beta:id/password").sendKeys("T3stM3.P1s");
                    Android_Driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
                    Android_Driver.findElementById("com.sprint.care.beta:id/positive_btn").click();
                    System.out.println("Fast Login dialog detected");
                    saveTextLog_Allure("Fast Login dialog detected, and login with account password");
                } else {
                    System.out.println("Login dialog detected");
                    saveTextLog_Allure("Login dialog detected, check detail with screenshot attachment");
                    saveScreenshotPNG_Allure_Fail(Android_Driver);
                    Assert.fail();
                }
            } else {
                System.out.println("No ERROR dialogs detected");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Exception");
        }
    }

    public static void Network_Dialog_Check() {
        Android_Driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        List<AndroidElement> network_title = Android_Driver.findElementsById("com.sprint.care.beta:id/cardTitle");
        try {
            if (network_title.size() > 0) {
                System.out.println("Sprint Network Unavailable ... ... Driver Quitting ");
                saveScreenshotPNG_Allure(Android_Driver);
                saveTextLog_Allure("Sprint Network Unavailable ... ... Driver Quitting");
                Assert.fail();
                Android_Driver.quit();
            } else {
                System.out.println("INFO: Network check done ...");
                saveTextLog_Allure("INFO: Network check done ...");
            }
        } catch (NoSuchElementException e) {
            System.out.println("INFO: Error catching: " + e.getMessage());
        }
    }
}
