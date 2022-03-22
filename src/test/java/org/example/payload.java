package org.example;
//import io.restassured.RestAssured;
//import io.restassured.path.json.JsonPath;
//import org.junit.Assert;
//import org.testng.annotations.Test;
//import  static io.restassured.RestAssured.*;
//import static java.lang.System.currentTimeMillis;
//import static org.hamcrest.Matchers.*;



// import java.util.Random;

public class payload {


    public static String CoursePrice(){


        return "{\n" +
                "\n" +
                "\"dashboard\": {\n" +
                "\n" +
                "\"purchaseAmount\": 910,\n" +
                "\n" +
                "\"website\": \"rahulshettyacademy.com\"\n" +
                "\n" +
                "},\n" +
                "\n" +
                "\"courses\": [\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Selenium Python\",\n" +
                "\n" +
                "\"price\": 50,\n" +
                "\n" +
                "\"copies\": 6\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"Cypress\",\n" +
                "\n" +
                "\"price\": 40,\n" +
                "\n" +
                "\"copies\": 4\n" +
                "\n" +
                "},\n" +
                "\n" +
                "{\n" +
                "\n" +
                "\"title\": \"RPA\",\n" +
                "\n" +
                "\"price\": 45,\n" +
                "\n" +
                "\"copies\": 10\n" +
                "\n" +
                "}\n" +
                "\n" +
                "]\n" +
                "\n" +
                "}\n" +
                "\n";
    }

    public static String VehicleNo(){
        int alpha1 = 'A' + (int)(Math.random() * ('Z' - 'A'));
        int alpha2 = 'A' + (int)(Math.random() * ('Z' - 'A'));
        int alpha3 = 'A' + (int)(Math.random() * ('Z' - 'A'));
        int digit1 = (int)(Math.random() * 10);
        int digit2 = (int)(Math.random() * 10);
        int digit3 = (int)(Math.random() * 10);
        int digit4 = (int)(Math.random() * 10);
        String vehicleNo = (""+(char)(alpha1) + ((char)(alpha2)) +digit1+digit4+
                ((char)(alpha3)) +(char)(alpha1)+ digit1 + digit2 + digit3 + digit4);

        return vehicleNo;

    }


    public static void main(String[] args) {

        payload p1=new payload();




    }}
