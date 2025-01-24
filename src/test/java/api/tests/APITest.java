package api.tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;
import java.util.Set;

public class APITest {

    @Test
    public void validateBpiData() {
        // Endpoint URL
        String url = "https://api.coindesk.com/v1/bpi/currentprice.json";
        Response response = RestAssured.get(url);
        Assert.assertEquals(response.getStatusCode(), 200);
        Map<String, Object> bpiMap = response.jsonPath().getMap("bpi");
        Set<String> bpiKeys = bpiMap.keySet();
        Set<String> expectedKeys = Set.of("USD", "GBP", "EUR");

        for (String key : expectedKeys) {
            System.out.println("keys are: " + key);
            Assert.assertTrue(bpiKeys.contains(key),  key);
        }

        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
        Assert.assertEquals(gbpDescription, "British Pound Sterling");
    }
}
