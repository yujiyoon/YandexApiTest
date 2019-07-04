import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

public class APITests {
    @Test
    public void checkLat() {
        JSONObject response = APISteps.getForecast();

        Object actualLat = APISteps.getValueFromJsonObject(response, "info", "lat");
        Assert.assertEquals(ExpectedVariables.expectedLat, actualLat);
    }

    @Test
    public void checkLon() {
        JSONObject response = APISteps.getForecast();

        Object actualLon = APISteps.getValueFromJsonObject(response, "info", "lon");
        Assert.assertEquals(ExpectedVariables.expectedLon, actualLon);
    }

    @Test
    public void checkOffset() {
        JSONObject response = APISteps.getForecast();

        Object actualOffset = APISteps.getValueFromJsonObject(response, "info", "tzinfo", "offset");
        Assert.assertEquals(ExpectedVariables.expectedOffset, actualOffset);
    }

    @Test
    public void checkName() {
        JSONObject response = APISteps.getForecast();

        Object actualName = APISteps.getValueFromJsonObject(response, "info", "tzinfo", "name");
        Assert.assertEquals(ExpectedVariables.expectedName, actualName);
    }

    @Test
    public void checkAbbr() {
        JSONObject response = APISteps.getForecast();

        Object actualAbbr = APISteps.getValueFromJsonObject(response, "info", "tzinfo", "abbr");
        Assert.assertEquals(ExpectedVariables.expectedAbbr, actualAbbr);
    }

    @Test
    public void checkDst() {
        JSONObject response = APISteps.getForecast();

        Object actualDst = APISteps.getValueFromJsonObject(response, "info", "tzinfo", "dst");
        Assert.assertEquals(ExpectedVariables.expectedDst, actualDst);
    }

    @Test
    public void checkUrl() {
        JSONObject response = APISteps.getForecast();

        Object actualUrl = APISteps.getValueFromJsonObject(response, "info", "url");
        Assert.assertEquals(ExpectedVariables.expectedUrl, actualUrl);
        Assert.assertTrue("Ссылка не содержит координаты заданного региона", actualUrl.toString()
                .contains("lat=" + ExpectedVariables.expectedLat.toString() + "&lon=" + ExpectedVariables.expectedLon));
    }

    @Test
    public void checkSeason() {
        JSONObject response = APISteps.getForecast();

        Object actualSeason = APISteps.getValueFromJsonObject(response, "fact", "season");
        Assert.assertEquals(ExpectedVariables.expectedSeason, actualSeason);
    }

    @Test
    public void checkForecastDateCount() {
        JSONObject response = APISteps.getForecast();

        Object actualDateCount = APISteps.getValueFromJsonArray(response, "forecasts");
        Assert.assertEquals(ExpectedVariables.expectedDateCount, actualDateCount);
    }

    @Test
    public void checkMoonSecondDay() {
        JSONObject response = APISteps.getForecast();

        int moonCode = Integer.parseInt(APISteps.getValueFromJsonArray(response, "forecasts", 1, "moon_code").toString());
        String moonText = APISteps.getValueFromJsonArray(response, "forecasts", 1, "moon_text").toString();
        Assert.assertTrue(APISteps.isMoonCodeRelateToMoonText(moonCode, moonText));
    }
}

