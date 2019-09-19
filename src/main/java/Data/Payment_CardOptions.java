package Data;

import org.testng.annotations.DataProvider;

public class Payment_CardOptions {

    @DataProvider(name = "CardNumber")
    public Object[][] getData() {
        Object[][] card_data = {
                {"4444424444444440", "Visa", "LOAD TEST ACCOUNTS"},
                {"5500005555555559", "MasterCard", "LOAD TEST ACCOUNTS"},
                {"36111111111111", "MasterCard Diners", "LOAD TEST ACCOUNTS"},
        };
        return card_data;
    }
}
