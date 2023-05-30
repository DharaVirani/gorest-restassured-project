package com.gorest.crudtest;

import com.gorest.model.UserPojo;
import com.gorest.utils.TestUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;


public class UserCRUDTest  extends TestUtils {

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        RestAssured.basePath = "/users";
    }

    static String name = "Piyush";
    static String email = "piyush" + getRandomValue() +"gorasiya@gmail.com";
    static String gender = "female";
    static String status = "active";
    static String token = "b4ca58749ef980c797da415df530cac00dbac642ef3839896b47de0b428a2df5";
    static int user_id;

    //verifyCreateUserSuccessfully
    @Test
    public void a1(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Response response = given()
                .header("Authorization","Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .post();
        user_id = response.then().extract().path("id");
        response.then().statusCode(201);
        response.then().log().body();

//        given()
//                .header("Authorization","Bearer" + token)
//                .when()
//                .get("/" + user_id);
//        response.then().statusCode(200);


    }

    //2. verifyUserUpdateSuccessfully()
    @Test
    public void a2(){
        UserPojo userPojo = new UserPojo();
        userPojo.setName("Kamal");
        userPojo.setEmail("kamal" + TestUtils.getRandomValue() + "hassan@gmail.com");
        userPojo.setGender("male");
        userPojo.setStatus("active");
        Response response = given()
                .header("Authorization","Bearer " + token)
                .contentType(ContentType.JSON)
                .body(userPojo)
                .when()
                .put("/" + user_id);
        response.then().statusCode(200);
        response.then().log().body();
    }

    //3. verifyUserDeleteSuccessfully()
    @Test
    public void a3(){
        given()
                .header("Authorization","Bearer " + token)
                .when()
                .delete("/" + user_id)
                .then()
                .statusCode(204);
    }




}
