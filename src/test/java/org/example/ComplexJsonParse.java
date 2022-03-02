package org.example;

import io.restassured.path.json.JsonPath;

//        1. Print No of courses returned by API
//
//        2.Print Purchase Amount
//
//        3. Print Title of the first course
//
//        4. Print All course titles and their respective Prices
//
//        5. Print no of copies sold by RPA Course
//        6. Verify if Sum of all Course prices matches with Purchase Amount

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath js=new JsonPath(payload.CoursePrice());
        int cousesNo=js.getInt("courses.size()");
        System.out.println("total number of courses :- "+cousesNo);



        float purchaseAmt=js.getFloat("dashboard.purchaseAmount");
        System.out.println("puchase amount :- " +purchaseAmt + "Rs");

        String ss1=js.getString("courses[0].title");
        System.out.println("title of first course :- "+ss1);


        System.out.println("4. Print All course titles and their respective Prices\n" +
                "\n");


        for(int i=0;i<cousesNo;i++){

            String couseTitles=js.getString("courses["+i+"].title");
            int Prices=js.getInt("courses["+i+"].price");
            System.out.println(couseTitles+" :- "+ Prices);

        }

        System.out.println("\n 5. Print no of copies sold by RPA Course");


        for(int i=0;i<cousesNo;i++){

            String couseTitles=js.getString("courses["+i+"].title");
            if("RPA".equalsIgnoreCase(couseTitles)) {
                int copies = js.getInt("courses[" + i + "].copies");

                System.out.println((i+1)+ "->> RPA sold copies :- " + copies);

                break;
            }
        }

    }
}
