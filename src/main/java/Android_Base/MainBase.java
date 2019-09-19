package Android_Base;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.File;
import java.net.URL;

public class MainBase {

    public static AndroidDriver<AndroidElement> Android_Driver=null;
    static String notifAccess = "ALLOW";

    @BeforeSuite(groups = "MainBase")
    @Parameters({"udid", "appiumPort", "deviceName", "versionNo"})
    @Severity(SeverityLevel.CRITICAL)
    public static AndroidDriver<AndroidElement> setUp(String udid, String appiumPort,
                                                      String deviceName, String versionNO) throws Exception {

        if(deviceName.equals("appium29")) {
            notifAccess = "Allow all the time";
        }

        File app = new File("src/main/java/app",versionNO);
        DesiredCapabilities d = new DesiredCapabilities();
        d.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        d.setCapability(MobileCapabilityType.UDID, udid);
        d.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        d.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        Android_Driver = new AndroidDriver<>(new URL("http://127.0.0.1:" + appiumPort + "/wd/hub"), d);

        System.out.println("System: \n My Sprint Beta - \n Test Device: Android \n App Version: " + versionNO +
                "\n Others: Thread 1 \n ---------------------------------------------");
        return Android_Driver;
    }

    @AfterSuite(groups = "MainBase")
    public void tearDown() {
        Android_Driver.quit();
        System.out.println("System: Test Done, Android Driver Quitting ...\n ---------------------------------------------");
    }


}
