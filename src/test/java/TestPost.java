import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class TestPost {
    private final String username = "admin";
    private final String password = "password";

    static String book1 = "{\"id\": \"1\", \"title\": \"Sherlock Holmes\", \"author\": \"Author Conan\"}";
    static String book2 = "{\"id\": \"2\",\"title\": \"A brief history of time\", \"author\": \"Stephan hawking\"}";
//    static String book3 = "{\"title\": \"Dune\", \"author\": \"Frank herbert\"}";
//    static String book4 = "{\"title\": \"Gone Girl\", \"author\": \"William Flynn\"}";



    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "http://localhost:7081";
        RestAssured.basePath = "/api";
    }
    @Test
    void createBookTest1() {
        Response response = given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(book1)
                .when().post("/books")
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 201);
    }

    @Test
    void createBookTest2() {
        Response response = given()
                .auth().preemptive().basic(username, password)
                .header("Content-Type", "application/json")
                .body(book2)
                .when().post("/books")
                .then().extract().response();

        System.out.println("Response: " + response.asString());
        Assert.assertEquals(response.statusCode(), 201);
    }
//    @Test
//    void createBookTest3() {
//        Response response = given()
//                .auth().preemptive().basic(username, password)
//                .header("Content-Type", "application/json")
//                .body(book3)
//                .when().post("/books")
//                .then().extract().response();
//
//        System.out.println("Response: " + response.asString());
//        Assert.assertEquals(response.statusCode(), 201);
//    }
//    @Test
//    void createBookTest4() {
//        Response response = given()
//                .auth().preemptive().basic(username, password)
//                .header("Content-Type", "application/json")
//                .body(book4)
//                .when().post("/books")
//                .then().extract().response();
//
//        System.out.println("Response: " + response.asString());
//        Assert.assertEquals(response.statusCode(), 201);
//    }
}
