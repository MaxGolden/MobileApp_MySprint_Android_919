package Listeners_Tests;

import Android_Base.MainBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSStartScreenRecordingOptions;
import io.qameta.allure.Attachment;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners_Android extends MainBase implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Attachment(value = "Auto Fail Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG_Allure(AndroidDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Fail Screenshot", type = "image/png")
    public static byte[] saveScreenshotPNG_Allure_Fail(AndroidDriver driver) {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "IMPORTANT Message", type = "text/plain")
    public static String saveTextLog_Allure(String message) {
        System.out.println(message);
        return message;
    }

    @Attachment(value = "Expected Result", type = "text/plain")
    public static String saveTextLog_Allure_er(String message) {
        return message;
    }

    @Attachment(value = "Video Attachment", type = "video/mp4")
    public static byte[] saveH264_Allure_Video(String videoClip) {
        return Base64.decodeBase64(videoClip);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        System.out.println("LISTENER: onStart Test - " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        System.out.println("LISTENER: onFinish Test - " + iTestContext.getName());
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Android_Driver.startRecordingScreen(IOSStartScreenRecordingOptions.
                startScreenRecordingOptions().withVideoType("h264"));
        System.out.println("LISTENER: Test - " + getTestMethodName(iTestResult) + " - is Starting \n");
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        saveH264_Allure_Video(Android_Driver.stopRecordingScreen());
        System.out.println("LISTENER: Test - " + getTestMethodName(iTestResult) + " - is passed \n");
    }

    @Override
    public void onTestFailure(ITestResult iTestResult)
    {
        ITestContext context = iTestResult.getTestContext();
        saveScreenshotPNG_Allure(Android_Driver);
        saveH264_Allure_Video(Android_Driver.stopRecordingScreen());
        System.out.println("LISTENER: Test - " + iTestResult.getName() + " - " + context) ;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        saveH264_Allure_Video(Android_Driver.stopRecordingScreen());
        System.out.println("LISTENER: Test -" + getTestMethodName(iTestResult) + "- is Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        System.out.println("LISTENER: Test failed but it is in defined success ratio - " + getTestMethodName(iTestResult));
    }

}