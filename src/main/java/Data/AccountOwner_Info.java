package Data;

import org.testng.annotations.DataProvider;

public class AccountOwner_Info {

    @DataProvider(name = "AccountOwner")
    public Object[][] getData() {
        Object[][] accounts_data = {
                {"ST2", "3369549079", "TWREG-59003", "T3stM3.P1s"}
        };
        return accounts_data;
    }
}
