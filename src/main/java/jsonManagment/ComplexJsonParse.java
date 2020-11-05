package jsonManagment;

import utils.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
//Arrays on json test methods
    public static void main(String[] args) {
        JsonPath jsonPath = new JsonPath(Payload.coursesJson());
        int count = jsonPath.getInt("courses.size()");
        jsonPath.prettyPrint();
        for(int i = 0; i < count; i++ ) {
            System.out.println(jsonPath.get("courses["+i+"].title"));
            System.out.println(jsonPath.get("courses["+i+"].price"));
        }
    }
}
