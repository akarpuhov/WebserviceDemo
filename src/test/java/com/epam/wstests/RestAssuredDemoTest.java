package com.epam.wstests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RestAssuredDemoTest {
    @BeforeTest
    public void initTest(){

//        String host = "https://jsonplaceholder.typicode.com/users";
        RestAssured.baseURI = "http://jsonplaceholder.typicode.com";
    }

    @Test
    public void checkStatusCode(){
        Response response = RestAssured.when().get("/users").andReturn();
        System.out.println(response.getStatusLine());
        System.out.println(response.getStatusCode());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader(){
        Response response = RestAssured.when().get("/users").andReturn();

        String rpContentTypeHeader = response.getHeader("content-type");
        System.out.println(rpContentTypeHeader);
        Assert.assertEquals(rpContentTypeHeader, "application/json; charset=utf-8");
    }

    @Test
    public void checkResponseBody(){
        Response response = RestAssured.when().get("/users").andReturn();
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
