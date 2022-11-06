import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestGET {

    private final String password = "password";

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "http://localhost:7081";
        RestAssured.basePath = "/api";
    }
    @Test
    void getAllBooksUser() {
        Response response = given()
                .auth().preemptive().basic("user", password)
                .when().get("/books")
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    void getBookByIdUser() {
        Response response = given()
                .auth().preemptive().basic("user", password)
                .when().get("/books/{id}",1)
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 200);
    }

//    @Test
//    void getBookNotSavedByIdUser() {
//        Response response = given()
//                .auth().preemptive().basic("user", password)
//                .when().get("/books/{id}",50)
//                .then().extract().response();
//
//        System.out.println("Response: " + response.asString());
//        Assert.assertEquals(response.statusCode(), 200);
//    }

    @Test
    void getAllBooksAdmin() {
        Response response = given()
                .auth().preemptive().basic("admin", password)
                .when().get("/books")
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test
    void getBookNotSavedByIdAdmin() {
        Response response = given()
                .auth().preemptive().basic("admin", password)
                .when().get("/books/{id}",50)
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 404);
    }

    @Test
    void getBookByIdAdmin() {
        Response response = given()
                .auth().preemptive().basic("admin", password)
                .when().get("/books/{id}",2)
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 200);
    }

}
