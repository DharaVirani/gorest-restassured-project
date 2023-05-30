package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                // .pathParam("url", "https://gorest.co.in/public/v2")
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .when()
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001(){
        List<String> listOfTitles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The list of titles are : " + listOfTitles);
        System.out.println("------------------End of Test---------------------------");
    }
    //2. Extract the total number of record
    @Test
    public void test002(){
        List<Integer> totalNoOfRecords = response.extract().path("total");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The total number of records are : " + totalNoOfRecords.size());
        System.out.println("------------------End of Test---------------------------");
    }
    //3. Extract the body of 15th record
    @Test
    public void test003(){
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The 15th record is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> lisOfIds = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The user_id of all the records are : " + lisOfIds);
        System.out.println("------------------End of Test---------------------------");
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> titles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records are : " + titles);
        System.out.println("------------------End of Test---------------------------");
    }
    //6. Extract the title of all records whose user_id = 2250472
    @Test
    public void test006(){
        List<String> title = response.extract().path("findAll{it.user_id == 2250472}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The title of all the records whose user_id = 2250472 are : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 38978
    @Test
    public void test007(){
        String body = response.extract().path("find{it.id == '38978'}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The body of all records whose id = 38978 is : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
