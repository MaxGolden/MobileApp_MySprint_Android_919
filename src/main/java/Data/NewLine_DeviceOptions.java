package Data;

import org.testng.annotations.DataProvider;

public class NewLine_DeviceOptions {

    @DataProvider(name = "DeviceOption")
    public Object[][] getData() {
        Object[][] deviceOption = {
                // deviceType, brand, model, planOption, phonePlan, protectionOption
                {"Phones", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Tablets", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Watches", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Others", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//
//                {"Phones", "S", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Tablets", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Watches", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
//                {"Others", "Apple", "Apple iPhone XR", "finance", "Unlimited", "Add protection"},
        };
        return deviceOption;
    }
}
