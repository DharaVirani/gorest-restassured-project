package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest {


    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .pathParam("url", "https://gorest.co.in/public/v2")
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .when()
                .get("/users")
                .then().statusCode(200);
    }

    //1. Verify the if the total record is 20
    @Test
    public void test001() {
        response.body("total.size", equalTo(20));
    }


    //2. Verify the if the name of id: 2223224 is equal to ”Aatreya Shah”
    @Test
    public void test002() {
        response
                .body("[0].id", equalTo(2223218))
                .body("[0].name", equalTo("Sumitra Saini"));
    }


    //3. Check the single ‘Name’ in the Array list (Sumitra Saini)
    @Test
    public void test003() {
        response.body("name", hasItem("Sumitra Saini"));
    }

    //4. Check the multiple ‘Names’ in the ArrayList (Trilokanath Ahluwalia, Deeptimoy Gowda DDS, Meghnath Sinha)
    @Test
    public void test004() {
        response.body("name", hasItems("Deeptimoy Gowda DDS", "Meghnath Sinha", "Trilokanath Ahluwalia"));
    }

    //5. Verify the email of userid = 2223216 is equal “sinha_meghnath@mitchell-paucek.example”
    @Test
    public void test005(){
        response
                .body("[1].id", equalTo(2223209))
                .body("[1].email",equalTo("param_msgr_shukla@rogahn.test"));
    }

    //6. Verify the status is “Active” of username is “Shanti Bhat V”
    @Test
    public void test006(){
        response
                .body("[1].name",equalTo("Msgr. Param Shukla"))
                .body("[1].status",equalTo("active"));
    }

    //7. Verify the Gender = male of user name is “Niro Prajapat”
    @Test
    public void test007(){
        response.body("[0].gender",equalTo("male"));
    }

}
