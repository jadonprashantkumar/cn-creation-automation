package org.example;

import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class Sumvalidation {

    @Test
    public void sumOfCourses() {

        JsonPath js = new JsonPath(payload.CoursePrice());
        int cousesNo = js.getInt("courses.size()");
        int sum=0;
        for (int i = 0; i < cousesNo; i++) {
            int ss1=js.getInt("courses["+i+"].copies");
            int ss2=js.getInt("courses["+i+"].price");
            sum=sum+(ss1*ss2);
       }
        int puchaseAmt=js.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(puchaseAmt,sum);



    }
}
