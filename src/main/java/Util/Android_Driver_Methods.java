package Util;

import Android_Base.MainBase;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static Listeners_Tests.Listeners_Android.saveScreenshotPNG_Allure_Fail;
import static Listeners_Tests.Listeners_Android.saveTextLog_Allure;

public class Android_Driver_Methods extends MainBase {

    private static String Error_Dialog_Title = "com.sprint.care.beta:id/dialogTitle_tv";
    private static String Error_Dialog_Message = "com.sprint.care.beta:id/dialogMessage_tv";
    private static String Error_Dialog_Positive = "com.sprint.care.beta:id/positive_btn";
    private static String Error_Dialog_Password_Field = "T3stM3.P1s";
    private static String Error_Dialog_Password = "T3stM3.P1s";

    // Appium find element by using Resource ID
    public static void findByResourceID_Click(int timeNum, String resourceID, boolean errorDialogCheck,
                                              String FailMessage) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsById(resourceID);
        try {
            if(CountNum.size() > 0) {
                Android_Driver.findElementById(resourceID).click();
            } else {
                if(errorDialogCheck) {
                    Error_dialog_detect(FailMessage);
                } else {
                    assertFail_toMainPage(10, FailMessage);
                }
            }
        }catch (NoSuchElementException e) {
            System.out.println("Exception");
        }
    }

    public static void findByResourceID_Click(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsById(resourceID);
        try {
            if(CountNum.size() > 0) {
                Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
                Android_Driver.findElementById(resourceID).click();
            } else {
                assertFail_toMainPage(3, "Element not found");
            }
        }catch (NoSuchElementException e) {
            System.out.println("Exception");
        }
    }

    public static void findByResourceID_SendKey(int timeNum, String resourceID, String keysInfo) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        Android_Driver.findElementById(resourceID).sendKeys(keysInfo);
    }

    public static void findByResourceID_SendKey(int timeNum, String resourceID, String keysInfo, String FailMessage) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsById(resourceID);
        try {
            if(CountNum.size() > 0) {
                Android_Driver.findElementById(resourceID).sendKeys(keysInfo);
            } else {
                Error_dialog_detect(FailMessage);
            }
        }catch (NoSuchElementException e) {
            System.out.println("Exception");
        }
    }

    public static void findByResourceID_Clear(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        Android_Driver.findElementById(resourceID).clear();
    }

    public static String findByResourceID_GetText(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        return Android_Driver.findElementById(resourceID).getText();
    }

    public static boolean findByResourceID_Enable(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        return Android_Driver.findElementById(resourceID).isEnabled();
    }

    public static List<AndroidElement> findByResourceID_Counts(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        return Android_Driver.findElementsById(resourceID);
    }

    public static boolean findByResourceID_Exist(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsById(resourceID);
        try {
            return CountNum.size() > 0;
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
        return true;
    }

    public static List<AndroidElement> findByResourceID_List(int timeNum, String resourceID) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        return Android_Driver.findElementsById(resourceID);
    }

    public static int findByList_TextMatchIndex(List<AndroidElement> androidList, String matchText) {
        if(androidList.size() < 1) {
            assertFail_toMainPage(true, 5, "Element not found");
        }
        for(int i = 0; i <= (androidList.size()-1); i++) {
            if(androidList.get(i).getText().toLowerCase().equals(matchText)) {
                return i;
            }
        }
        return -1;
    }

    public static void findByList_IndexClick(int timeNum, String resourceID, String matchText) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> androidList = Android_Driver.findElementsById(resourceID);
        if(androidList.size() < 1) {
            assertFail_toMainPage(true, 5, "Element not found");
        }
        for(int i = 0; i <= (androidList.size()-1); i++) {
            if(androidList.get(i).getText().toLowerCase().equals(matchText)) {
                androidList.get(i).click();
            }
        }
    }

    // Appium find element by using class name and click
    public static void findByClassName_Click(int timeNum, String className, int IndexNum) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        Android_Driver.findElementsByClassName(className).get(IndexNum).click();
    }


    // Appium find element by using AndroidUIAutomator with text and click - "text(\"Add a new device\")"
    public static void findByAndroidUIAutomator_Click(int timeNum, String textContent) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        Android_Driver.findElementByAndroidUIAutomator(textContent).click();
    }

    public static void findByAndroidUIAutomator_Click(int timeNum, String textContent, boolean errorDialogCheck,
                                                      String FailMessage) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsByAndroidUIAutomator(textContent);
        try {
            if(CountNum.size() > 0) {
                Android_Driver.findElementByAndroidUIAutomator(textContent).click();
            } else {
                if(errorDialogCheck) {
                    Error_dialog_detect("Error Dialog Found");
                } else {
                    assertFail_toMainPage(true, 5, FailMessage);
                }
            }
        }catch (NoSuchElementException e) {
            System.out.println("Exception");
        }
    }

    public static boolean findByAndroidUIAutomator_Exist(int timeNum, String textContent) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        List<AndroidElement> CountNum = Android_Driver.findElementsByAndroidUIAutomator(textContent);
        try {
            return CountNum.size() > 0;
        }catch (NoSuchElementException e) {
            System.out.println("Error: No such element found!");
        }
        return true;
    }

    public static List<AndroidElement> findByAndroidUIAutomator_Counts(int timeNum, String textContent) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        return Android_Driver.findElementsByAndroidUIAutomator(textContent);
    }


    // Appium find element by using TouchAction with coordinators
    public static void findByCoord_Click(int X, int Y) {
        TouchAction t = new TouchAction(Android_Driver);
        t.tap(PointOption.point(X, Y)).release().perform();
    }


    // Appium find element and scroll down by using TouchAction
    public static void findByResourceID_ScrollDown(String ElementDown, String ElementUp, int TimeNum) {
        TouchAction t = new TouchAction(Android_Driver);
        AndroidElement scroll_point1 = Android_Driver.findElementById(ElementDown);
        AndroidElement scroll_point2 = Android_Driver.findElementById(ElementUp);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(TimeNum)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();
    }

    public static void findByResourceID_ScrollDown(String ElementDown, String ElementUp) {
        TouchAction t = new TouchAction(Android_Driver);
        AndroidElement scroll_point1 = Android_Driver.findElementById(ElementDown);
        AndroidElement scroll_point2 = Android_Driver.findElementById(ElementUp);
        t.longPress(LongPressOptions.longPressOptions().withElement(ElementOption.element(scroll_point1))
                .withDuration(Duration.ofSeconds(40)))
                .moveTo(ElementOption.element(scroll_point2))
                .release()
                .perform();
    }

    public static void findByResourceID_ScrollDown(boolean scroll_UP, int scrollDis) {
        TouchAction t = new TouchAction(Android_Driver);
        if(scroll_UP) {
            t.longPress(PointOption.point(200,300))
                    .moveTo(PointOption.point(200, 300+scrollDis))
                    .release()
                    .perform();
        } else {
            t.longPress(PointOption.point(200,300+scrollDis))
                    .moveTo(PointOption.point(200, 300))
                    .release()
                    .perform();
        }
    }


    // Appium find element by using Xpath and click
    public static void findByXpath_Click(int timeNum, String Xpath) {
        Android_Driver.manage().timeouts().implicitlyWait(timeNum, TimeUnit.SECONDS);
        Android_Driver.findElementByXPath(Xpath).click();
    }

    private static void Error_dialog_detect(String FailMessage) {
        if(findByResourceID_Exist(3, Error_Dialog_Title)) {
            String error_title = Android_Driver.findElementById(Error_Dialog_Title).getText();
            if(error_title.toLowerCase().equals("unlock")) {
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById(Error_Dialog_Password_Field).sendKeys(Error_Dialog_Password);
                Android_Driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
                Android_Driver.findElementById(Error_Dialog_Positive).click();

                /////////// More progress //////////

                saveTextLog_Allure("Fast Login dialog detected, and login with account password");
            } else {
                String error_mes = Android_Driver.findElementById(Error_Dialog_Message).getText();
                assertFail_toMainPage(
                        true, 5, "Error dialog detected, check detail with screenshot attachment " +
                                "\n MORE INFO: " + error_mes);
                Android_Driver.quit();
            }
        } else if(findByResourceID_Exist(3, "com.sprint.care.beta:id/warning")) {
            assertFail_toMainPage(true, 5, "Sprint network unavailable");
        } else {
            assertFail_toMainPage(true, 5, FailMessage);
        }
    }


    // Allure message show in the allure report
    public static void allure_mes(String FailMessage) {
        saveTextLog_Allure("FAIL: " + FailMessage);
    }

    // Android Driver navigate back
    public static void navigateBack(int timeNum, int backTimes) throws Exception {
        Thread.sleep(timeNum * 1000);
        for(int i=1; i<=backTimes; i++) {
            if(findByResourceID_Exist(5, "com.sprint.care.beta:id/action_button")) {
                System.out.println("Back to the main page");
            } else {
                Android_Driver.navigate().back();
            }
        }
    }

    public static void assertFail_toMainPage(int backSteps, String FailMessage) {
        allure_mes(FailMessage);
        saveScreenshotPNG_Allure_Fail(Android_Driver);
        for(int i = 1; i <= backSteps; i++) {
//            Thread.sleep(500);
            if(findByResourceID_Exist(2, "com.sprint.care.beta:id/dialogTitle_tv")) {
                findByResourceID_Click(2, "com.sprint.care.beta:id/positive_btn");
            }
            if(findByResourceID_Exist(8, "com.sprint.care.beta:id/action_button")) {
                Assert.fail();
            }
            Android_Driver.navigate().back();
        }
    }

    public static void assertFail_toMainPage(boolean toMainPage, int backSteps, String FailMessage) {
        allure_mes(FailMessage);
        saveScreenshotPNG_Allure_Fail(Android_Driver);
        if(toMainPage) {
            for(int i = 1; i <= backSteps; i++) {
                if(findByResourceID_Exist(2, "com.sprint.care.beta:id/dialogTitle_tv")) {
                    findByResourceID_Click(2, "com.sprint.care.beta:id/positive_btn");
                }
                Android_Driver.navigate().back();
                if(findByResourceID_Exist(8, "com.sprint.care.beta:id/action_button")) {
                    Assert.fail();
                }
            }
        } else {
            Assert.fail();
        }
    }
}
