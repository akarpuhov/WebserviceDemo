package com.epam.wstests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssuredTest {

    @BeforeTest
    public void initTest(){

//        String host = "http://dummy.restapiexample.com/api/v1/employees";
        RestAssured.baseURI = "http://dummy.restapiexample.com";
    }

    @Test
    public void checkStatusCode(){
        Response response = RestAssured.when().get("/api/v1/employees").andReturn();
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader(){
        Response response = RestAssured.when().get("/api/v1/employees").andReturn();

        String rpContentTypeHeader = response.getHeader("content-type");
        System.out.println(rpContentTypeHeader);
        Assert.assertEquals(rpContentTypeHeader, "text/html; charset=UTF-8");
    }

    @Test
    public void checkResponseBody(){
        Response response = RestAssured.when().get("/api/v1/employees").andReturn();
        ResponseBody responseBody = response.getBody();
        User[] users = responseBody.as(User[].class);
        for (User user : users) {
            System.out.println("----------");
            System.out.println(user.getId());
            System.out.println(user.getName());
        }
        System.out.println("User's count: " + users.length);

        Assert.assertEquals(users.length, 10);
    }

}
