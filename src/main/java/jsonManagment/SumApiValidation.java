package jsonManagment;

import utils.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumApiValidation {
// validate sum of elements on a json array and validate with json attribute total
@Test
public void sumOfCourses()
{
    int sum = 0;
    JsonPath jsonPath = new JsonPath(Payload.coursesJson());
    int count =	jsonPath.getInt("courses.size()");
    for(int i = 0; i < count; i++)
    {
        int price = jsonPath.getInt("courses["+i+"].price");
        int copies =jsonPath.getInt("courses["+i+"].copies");
        int amount = price * copies;
        System.out.println(amount);
        sum = sum + amount;
    }
    System.out.println(sum);
    int purchaseAmount = jsonPath.getInt("dashboard.purchaseAmount");
    Assert.assertEquals(sum, purchaseAmount);
}

}
