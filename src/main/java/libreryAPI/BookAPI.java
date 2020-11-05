package libreryAPI;

import utils.Payload;
import utils.ReUsableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BookAPI {

    @DataProvider(name = "DATA_BOOKS")
    public Object[][] getData() {
        return new Object[][]{{"aa23", "21p3"}, {"pr9", "44a2"}, {"lle", "h282"}};
    }

    @Test(dataProvider = "DATA_BOOKS")
    public void addBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String postBookResponse = given().log().all().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath jsonPath = ReUsableMethods.rawToJson(postBookResponse);
        String id = jsonPath.get("ID");
        System.out.println(id);
    }
}
