package org.example;

import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;



import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utils.PropertiesUtils;

import static io.restassured.RestAssured.given;

public class BaseTestClass {
    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;
    public static String projectdir = System.getProperty("user.dir");
    protected static PropertiesUtils prop = null;
    public static String baseURL = null;
    public static String tokenURL = null;
    public static String access_token = null;
    public static String ClientId = null;
    public static String ToPin = null;
    public static String FromPin = null;
    public static String numberOfBoxes = null;
    public static String client_code = null;
    public static String WeightOfBox = null;


    // public static String userName = null;
   // public static String passWord = null;


    public static void initialiseProperties_Zoom() throws Exception {
        prop = new PropertiesUtils();
        prop.setPropertyFile(projectdir+"/src/main/resources/application.properties");
        baseURL=prop.gettingValueOfProperty("ZoomBaseUrl");
        tokenURL=prop.gettingValueOfProperty("login.stg.url");

      //  userName=prop.gettingValueOfProperty("userName");
     //   passWord=prop.gettingValueOfProperty("passWord");


        ClientId=prop.gettingValueOfProperty("ClientId");
        ToPin=prop.gettingValueOfProperty("ToPin");
        FromPin=prop.gettingValueOfProperty("FromPin");
        numberOfBoxes=prop.gettingValueOfProperty("NumberOfBoxes");
        client_code=prop.gettingValueOfProperty("client_code");
        WeightOfBox=prop.gettingValueOfProperty("WeightOfBox");
    }
    public static String accessToken(){

        String se=given().formParam("client_id","sso")
                .formParam("grant_type","password")
                .formParam("username","smoke_oadelt1@rivigo.com")
                .formParam("password","Smoketest@zoom5")
                .when().post(tokenURL)
                .then().assertThat().statusCode(200).extract().response().asString();
//     System.out.println( "file is"  + se);
        JsonPath js=new JsonPath(se);
//    System.out.println("Json file ::::----- ");
        access_token= js.getString("response.access_token");
        //   System.out.println("access_token is :- \""+access_token+"\"");
        return access_token;
    }
    @BeforeClass
    public void setup() throws Exception {

        initialiseProperties_Zoom();
        accessToken();
        payload.VehicleNo();
        setExtent();
    }
    public void setExtent() {

        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
        htmlReporter.config().setDocumentTitle("Automation Report");//Title of the report
        htmlReporter.config().setReportName("Functional Report"); //Report Name
        htmlReporter.config().setTheme(Theme.DARK);


        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("OS", "Linux");
        extent.setSystemInfo("Browser", "Chrome");
        extent.setSystemInfo("Author","Prashant");
    }

    @AfterClass
    public void endReport() {
        extent.flush();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
            test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
        }
        else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
        }

    }

}
