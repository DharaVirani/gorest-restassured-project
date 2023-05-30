package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {

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

    //1. Verify the if the total record is 25
    @Test
    public void test001(){
        response.body("total.size",equalTo(25));
    }

    //2. Verify the if the title of id = 38963 is equal to ”Quibusdam rerum dolore maxime antea.” PENDINGGGGG
    @Test
    public void test002(){
        response
                .body("[18].id",equalTo(38963))
                .body("[18].title",equalTo("Quibusdam rerum dolore maxime antea."));
    }

    //3. Check the single user_id in the Array list (2250456)
    @Test
    public void test003(){
        response.body("[17].user_id",equalTo(2250456));
    }

    //4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004(){
        response.body("user_id", hasItems(2250463, 2250465, 2250466));
    }

    //5. Verify the body of userid = 2678 is equal “Carus eaque voluptatem. Calcarspectaculum coniuratio. Abstergo consequatur deleo. Amiculum advenio dolorem.
    //Sollers conservo adiuvo. Concedo campana capitulus. Adfectus tibi truculenter.
    //Canto temptatio adimpleo. Ter degenero animus. Adeo optio crapula. Abduco et
    //antiquus. Chirographum baiulus spoliatio. Suscipit fuga deleo. Comburo aequus
    //cuppedia. Crur cuppedia voluptates. Argentum adduco vindico. Denique undique
    //adflicto. Assentator umquam pel."”

    @Test
    public void test005(){
        response
                .body("[2].user_id", equalTo(2250472))
                .body("[2].body",equalTo("Utique vapulus spectaculum. Textor ut voveo. Sophismata est callide. Cito debilito uter. Turba antiquus volva. Cado expedita vitae. Autus contra avarus. Antea tantillus curatio. Sordeo solitudo cribro. Adhaero theatrum turpe."));
    }
}
