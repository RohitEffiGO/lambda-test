package com.scenario.automate;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestApiTest{
	private static String URL;
	private static String FILE_PATH = "src/main/resources/application.properties";
	@BeforeTest
	public void loadUp() {
		System.out.println(System.getProperty("user.dir"));
		Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(FILE_PATH)) {
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        URL = properties.getProperty("url");
	}
	
	@Test(priority = 1)
	public void restApiTestOne() {
		String endpoint = "/users?page=2";
        Response response = RestAssured.get(URL + endpoint);
        response.then().statusCode(200);
	}
	
	@Test(priority = 2)
	public void restApiTestTwo() {
		String endpoint = "/users?page=2";
        Response response = RestAssured.get(URL + endpoint);
		int perPageValue = response.jsonPath().getInt("per_page");
		assertEquals(perPageValue, 6);
	}
}