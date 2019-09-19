package Data;

import org.testng.annotations.DataProvider;

public class Upgrade_DeviceOptions {

    @DataProvider(name = "DeviceOption")
    public Object[][] getData() {
        Object[][] deviceOption = {
                {"Phones", "Apple", "Apple iPhone XR", "Lease", "Unlimited Savings Plan", "Add protection", "Buy this phone"},
//                {"Phones", "Apple", "Apple iPhone XS", "Sprint Flex 18-mo. lease", "Unlimited Savings Plan", "Add protection", "Return this phone"},
        };
        return deviceOption;
    }
}
