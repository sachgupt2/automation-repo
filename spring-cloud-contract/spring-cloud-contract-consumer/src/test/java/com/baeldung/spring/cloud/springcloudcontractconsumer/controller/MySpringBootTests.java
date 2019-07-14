package com.baeldung.spring.cloud.springcloudcontractconsumer.controller;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith( SpringRunner.class )
@SpringBootTest
public class MySpringBootTests
{

   @Autowired
   private RestTemplate restTemplate;

   @Test
   public void myTest() throws Exception
   {
      JSONParser parser = new JSONParser();

      HttpHeaders httpHeaders = new HttpHeaders();
      
      httpHeaders.add( "Content-Type", "application/json" );
      
      ResponseEntity<String> responseEntity = restTemplate.exchange( "http://localhost:8181/message?id=1", HttpMethod.GET, new HttpEntity<>( httpHeaders ), String.class );
      
      JSONObject jsonObject = (JSONObject) parser.parse( responseEntity.getBody() );
      
      System.out.println( "Response Body =  " + responseEntity.getBody() );

      System.out.println( "Response Code =  " + responseEntity.getHeaders().getContentType() );

      System.out.println( "Response Headers =  " + responseEntity.getHeaders().getContentType() );

      System.out.println( "ID =  " + jsonObject.get( "id" ) );

      System.out.println( "Message =  " + jsonObject.get( "message" ) );

      Assert.assertEquals( 200, responseEntity.getStatusCodeValue() );

      Assert.assertEquals("ID did not matched : ", "1", jsonObject.get( "id" ) );
      
      Assert.assertEquals( "Message did not matched : ", "Hello World!!!", jsonObject.get( "message" ) );
      
      Assert.assertEquals( "application/json;charset=UTF-8", responseEntity.getHeaders().getContentType().toString() );
   }
}