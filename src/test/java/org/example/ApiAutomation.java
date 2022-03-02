package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;



public class ApiAutomation {

/*
    Given -> all input detail.
    when -> submit the API.
    Then -> validate the Response.

*/
public static void main(String[] args) {

    RestAssured.baseURI= "https://rahulshettyacademy.com";
    String rr=given().log().all().queryParam("key","qaclick123").body("{\n" +
                    "  \"location\": {\n" +
                    "    \"lat\": -38.383494,\n" +
                    "    \"lng\": 33.427362\n" +
                    "  },\n" +
                    "  \"accuracy\": 50,\n" +
                    "  \"name\": \"jadon house\",\n" +
                    "  \"phone_number\": \"(+91) 999 999 9999\",\n" +
                    "  \"address\": \"29, side layout, cohen 09\",\n" +
                    "  \"types\": [\n" +
                    "    \"shoe park\",\n" +
                    "    \"shop\"\n" +
                    "  ],\n" +
                    "  \"website\": \"http://google.com\",\n" +
                    "  \"language\": \"French-IN\"\n" +
                    "}")
            .when().post("/maps/api/place/add/json")
            .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
            .body("status",equalTo("OK")).header("Server","Apache/2.4.18 (Ubuntu)").
            header("Access-Control-Allow-Methods","POST").extract().response().asString();

    System.out.println( "file is"  + rr);
    JsonPath js=new JsonPath(rr);
    System.out.println("Json file ::::----- ");
    String placeID= js.getString("place_id");
    System.out.println("place ID is :- \""+placeID+"\"");


// for update API -

    System.out.println(" Update API start  ");

    String sw="70 Summer walk, USA prashant jadon";

    given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).body("{\n" +
            "\"place_id\":\""+placeID+"\",\n" +
            "\"address\":\""+sw+"\",\n" +
            "\"key\":\"qaclick123\"\n" +
            "}\n").when().put("/maps/api/place/update/json")
            .then().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));


// get place API :-
    System.out.println("get place API started :- ");

   String rr1= given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID)
            .when().get("/maps/api/place/get/json")
            .then().log().all().statusCode(200)
            .header("Server","Apache/2.4.18 (Ubuntu)").extract().response().asString();

    JsonPath js1=new JsonPath(rr1);
    String ss2= js1.getString("address");
    System.out.println("address is "+ ss2);
    Assert.assertEquals(ss2,sw);
}
}
