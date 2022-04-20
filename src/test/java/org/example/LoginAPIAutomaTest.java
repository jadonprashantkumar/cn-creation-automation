package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import org.testng.annotations.Test;


import  static io.restassured.RestAssured.*;
import static java.lang.System.currentTimeMillis;


public class LoginAPIAutomaTest extends BaseTestClass {
    public static String prsID="null";





    @Test(priority = 0)
    public void cnCreation() {
        test = extent.createTest("cnCreation");
        //   System.out.println("Token is == >> " + access_token);

        RestAssured.baseURI = baseURL;
        String se1 = given().header("Authorization", "Bearer " + access_token)
                .header("Content-Type", "application/json")
                .body("{\"id\": null,\"client\": {\"id\": 1519 },\"addressType\": " +
                        "\"PICKUPANDDROP\",\"address\": {\"id\": null,\"detailedAddress\":" +
                        " \"test test test3333395798\",\"pincode\": \"" + FromPin + "\",\"state\": " +
                        "\"Delhi\",\"landmark\": null},\"name\": \"test\",\"phoneNumber\": " +
                        "\"9971639104\",\"isAppoinmentDelivery\": false,\"contactPerson\": \"test\"," +
                        "\"status\": \"ACTIVE\",\"organizationId\": null}")
                .when().post("/backend/master/client_address")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(se1);
        //     System.out.println("Json file ::::----- ");
        String Consignor_Address = js1.getString("payload.id");
        //      System.out.println("Consignor_Address From Address is :- \"" + Consignor_Address + "\"");


        RestAssured.baseURI = baseURL;
        String se2 = given().header("Authorization", "Bearer " + access_token)
                .header("Content-Type", "application/json")
                .body("{\"id\": null,\"client\": {\"id\": 1519 },\"addressType\": " +
                        "\"PICKUPANDDROP\",\"address\": {\"id\": null,\"detailedAddress\":" +
                        " \"test test test3333395798\",\"pincode\": \"" + ToPin + "\",\"state\": " +
                        "\"Delhi\",\"landmark\": null},\"name\": \"test\",\"phoneNumber\": " +
                        "\"9971639104\",\"isAppoinmentDelivery\": false,\"contactPerson\": \"test\",\"status\": \"ACTIVE\",\"organizationId\": null}")
                .when().post("/backend/master/client_address")
                .then().assertThat().statusCode(200).extract().response().asString();

        JsonPath js2 = new JsonPath(se2);
        //      System.out.println("Json file ::::----- ");
        String Consignee_Address = js2.getString("payload.id");
        //    System.out.println("Consignee_Address From Address is :- \"" + Consignee_Address + "\"");

        //  System.out.println("CN creation process ::::::::================");

        String Cnote = "" + (long) (Math.random() * 100000 + 3333300000L);
        String Cnotebarcode = "" + (long) (Math.random() * 100000 + 3333300000L);
        String time = String.valueOf(currentTimeMillis());
        // System.out.println(payload.VehicleNo());


        RestAssured.baseURI = baseURL;
        String se3 = given().header("Authorization", "Bearer " + access_token)
                .header("Content-Type", "application/json")
                .body("{\"id\":null,\"cnoteType\":\"NORMAL\",\"cnote\":\"" + Cnote + "\",\"clientCode\":\"" + client_code + "\",\"serviceType\":\"ZOOM\",\"gstNumber\":null,\"fromPinCode\":\"" + FromPin + "\",\"toPinCode\":\"" + ToPin + "\",\"totalBoxes\":\"" + numberOfBoxes + "\"," +
                        "\"weight\":\""+ WeightOfBox+"\",\"volume\":null,\"value\":null,\"bookingDateTime\":" + time + ",\"consignorAddress\":\"test\",\"consignorAddressId\":" + Consignor_Address + ",\"consignorEmail\":\"testunibic@gmail.com\",\"consignorPhone\":\"9971639104\",\n" +
                        "\"consigneeAddress\":\"sdfv dvervev sc\",\"consigneeAddressId\":" + Consignee_Address + ",\"consigneeEmail\":null,\"consigneePhone\":\"12313131313\",\"contents\":\"Chemicals\",\"consignorName\":\"test\",\"consigneeName\":\"Rivigo costumer\",\"consignmentDocumentDtoList\":[{\"document\":\"GST Invoice / Delivery Challan\",\"status\":\"NOT_PICKED_UP\",\"minInvoiceValue\":null,\"documentType\":\"GSTIN number\",\"order\":99},{\"document\":\"CN consignee copy\",\"status\":\"NOT_PICKED_UP\",\"minInvoiceValue\":null,\"order\":2},{\"document\":\"CN POD copy\",\"status\":\"NOT_PICKED_UP\",\"minInvoiceValue\":null,\"order\":1}],\"packing\":\"Bundle\",\"barcodeType\":\"PRE_PRINTED\"," +

                        "\"barcodes\":[" + Cnotebarcode + "],\"isDacc\":null,\"invoices\":[{\"invoiceId\":null,\"invoiceNo\":\"1\",\"invoiceValue\":\"1\",\"eWaybillNumber\":\"\",\"hsnCodes\":null}],\"volumeDetails\":[{\"unit\":\"IN\",\"breadth\":\"1\",\"height\":\"1\",\"numberOfBoxes\":\"1\",\"length\":\"1\",\"volume\":0.0006}],\"valueAddedServicesDTO\":null,\"paymentDetailsDTO\":null,\"gstDetailsDTO\":null,\"taxId\":null,\"taxIdType\":null,\"trackerDTO\":{\"deviceIdType\":\"IP\",\"deviceType\":\"ZOOM_OPS\"},\"openPopupTime\":1573037386291,\"allIssuesResolved\":true,\"billingEntity\":\"UNIBIC FOODS INDIA PVT LTD.\"," +
                        "\"vehicleNumber\":\"" + payload.VehicleNo() + "\",\"consignmentCodDodDTO\":null,\"deliveryType\":\"NORMAL\",\"completionStatus\":\"COMPLETE\"}")

                .when().post("/backend/operations/consignments")
                .then().assertThat().statusCode(200).extract().response().asString();

        // Arrival pending ....


        JsonPath js3 = new JsonPath(se3);
        //   System.out.println(js3.getString("payload"));
        //    System.out.println("Json file ::::----- ");
        String CNOte = js3.getString("payload.cnote");
        System.out.println("CNOte is :- \"" + CNOte + "\"");
        String barcodes1 = js3.getString("payload.barcodes[0]");
        System.out.println("Barcode is :-\"" + barcodes1 + "\"");
        prsID = js3.getString("payload.prsId");
        System.out.println("PRS is :- \"" + prsID + "\"");
        String vehicleNO = js3.getString("payload.vehicleNumber");
        System.out.println("Vehicle Number is :-\"" + vehicleNO + "\"");
        Assert.assertEquals(Cnote, CNOte);
        //    Assert.assertNotNull(barcodes1);
    }

   // @Test(priority = 1)
        public void arrivalPending(){
            test = extent.createTest("arrivalPending");


            RestAssured.baseURI = baseURL;

            given().queryParam("prsId", "" + prsID + "").header("Authorization", "Bearer " + access_token).
                    when().put("/backend/operations/pickupRunSheet/markReached").
                    then().assertThat().statusCode(200).extract().response().asString();

            System.out.println("PRS is Arrived");

        }


   }



