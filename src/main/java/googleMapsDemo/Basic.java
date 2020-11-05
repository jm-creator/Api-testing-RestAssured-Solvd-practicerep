package googleMapsDemo;

import utils.Payload;
import utils.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class Basic {

    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        //Add place -> update with new address -> get place to validate if new address is present in response
        //Add place
        String response = given().log().all()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(Payload.addPlace())
                .when().post("/maps/api/place/add/json")
                .then().log().all()
                .assertThat()
                .statusCode(200)
                .body("scope", equalTo("APP"))
                .header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();

        //parsing json response
        JsonPath jsonPath = new JsonPath(response);
        String placeId = jsonPath.getString("place_id");
        System.out.println(placeId);
        String newPlace = "Resistencia-Chaco, Argentina";

        //update with new address
        given().log().all().queryParam("key","qaclick123").header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newPlace+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().assertThat().log().all().statusCode(200).body("msg", equalTo("Address successfully updated"));

        //get place to validate if new address is present in response
        String getPlaceResponse = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeId)
                .when().get("/maps/api/place/get/json")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsonPath1 = ReUsableMethods.rawToJson(getPlaceResponse);
        String actualAddress = jsonPath1.getString("address");
        System.out.println(actualAddress);
        Assert.assertEquals(actualAddress, newPlace);
    }
}
