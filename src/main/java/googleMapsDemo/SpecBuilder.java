package googleMapsDemo;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.googleMapsAPI.AddPlace;
import pojo.googleMapsAPI.Location;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class SpecBuilder {
    public static void main(String[] args){
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        AddPlace place = new AddPlace();
        place.setAccuracy(50);
        place.setAddress("Av Alverdi 2000 & Av Marconi - torre ");
        place.setPhone_number("+54 362 6666666");
        place.setWebsite("https://rahulshettyacademy.com");
        place.setName("JM-CREATOR");
        place.setLanguage("Spanish-ES");
        List<String> typesList = new ArrayList<String>();
        typesList.add("Green-House");
        typesList.add("Candy-Shop");
        place.setTypes(typesList);
        Location location = new Location();
        location.setLat(-27.46056);
        location.setLng(-58.98389);

        RequestSpecification requestSpec = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addQueryParam("key", "qaclick123")
                .setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        Response response = given().spec(requestSpec).body(place).when().post("maps/api/place/add/json")
                .then().spec(responseSpec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);
    }
}
