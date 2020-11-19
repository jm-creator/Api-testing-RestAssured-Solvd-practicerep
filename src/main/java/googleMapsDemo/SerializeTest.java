package googleMapsDemo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import pojo.googleMapsAPI.AddPlace;
import pojo.googleMapsAPI.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {
    public static void main(String[] args){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("Av Alverdi 2000 & Av Marconi");
        place.setPhone_number("+54 362 4666666");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("JM-CREATOR");
        place.setLanguage("Spanish-ES");
        List<String> typesList = new ArrayList<String>();
        typesList.add("green-house");
        typesList.add("shop");
        place.setTypes(typesList);
        Location location = new Location();
        location.setLat(-27.46056);
        location.setLng(-58.98389);

        Response response = given().log().all().contentType(ContentType.JSON)
                .queryParam("key", "qaclick123")
                .body(place).when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }
}
