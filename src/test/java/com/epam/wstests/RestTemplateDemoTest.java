package com.epam.wstests;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestTemplateDemoTest {

    @Test
    public void checkStatusCode(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class);
        int actualStatusCode = response.getStatusCodeValue();
        Assert.assertEquals(actualStatusCode, 200);
    }

    @Test
    public void checkResponseHeader(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class);
        HttpHeaders headers = response.getHeaders();
        String contentTypeValue = headers.getContentType().toString();
        System.out.println(contentTypeValue);
        Assert.assertEquals(contentTypeValue, "application/json;charset=utf-8");
    }

    @Test
    public void checkResponseBody(){
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Post[]> response = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts", Post[].class);
        Post[] actPosts = response.getBody();
        Assert.assertEquals(actPosts.length, 100);

    }
}
