import io.restassured.specification.RequestSender;
import org.json.JSONObject;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

class APISteps {

    private static final String address = "https://api.weather.yandex.ru/v1/forecast";
    private static final String keyName = "X-Yandex-API-Key";
    private static final String keyValue = "5544b796-738b-4ddf-85c6-842a9a2b3375";
    private static final int limit = 2;

    static JSONObject getForecast() {
        RequestSender request = given()
                .header(keyName, keyValue)
                .param("lat", ExpectedVariables.expectedLat)
                .param("lon", ExpectedVariables.expectedLon)
                .param("limit", limit)
                .when();
        return new JSONObject(request.get(address).asString());
    }

    static Object getValueFromJsonObject(JSONObject response, String objectName, String attributeName) {
        return response.getJSONObject(objectName).toMap().get(attributeName);
    }

    static Object getValueFromJsonObject(JSONObject response, String objectName, String objectName2, String attributeName) {
        return response.getJSONObject(objectName).getJSONObject(objectName2).toMap().get(attributeName);
    }

    static int getValueFromJsonArray(JSONObject response, String objectName) {
        return response.getJSONArray(objectName).length();
    }

    static Object getValueFromJsonArray(JSONObject response, String objectName, int index, String attributeName) {
        Assert.assertTrue("Заданный идекс превышает размер возвращаемого списка", response.getJSONArray(objectName).length() >= index + 1);
        return response.getJSONArray(objectName).getJSONObject(index).toMap().get(attributeName);
    }

    static Boolean isMoonCodeRelateToMoonText(int moonCode, String moonText) {
        if (moonCode == 0 && moonText.equals("full-moon")) {
            return true;
        } else if ((moonCode >= 1 && moonCode <= 3 || moonCode >= 5 && moonCode <= 7) && (moonText.equals("decreasing-moon"))) {
            return true;
        } else if (moonCode == 4 && moonText.equals("last-quarter")) {
            return true;
        } else if (moonCode == 8 && moonText.equals("new-moon")) {
            return true;
        } else if ((moonCode >= 9 && moonCode <= 11 || moonCode >= 13 && moonCode <= 15) && (moonText.equals("growing-moon"))) {
            return true;
        } else return moonCode == 12 && moonText.equals("first-quarter");
    }
}
