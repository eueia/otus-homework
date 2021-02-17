//import com.google.gson.JsonObject;
//import com.sun.org.glassfish.gmbal.Description;
//import io.restassured.RestAssured;
//import io.restassured.specification.RequestSpecification;
//import org.junit.jupiter.api.Test;
//import io.restassured.response.Response;
//import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
//import static org.hamcrest.Matchers.greaterThan;
//import static org.hamcrest.Matchers.is;
//
//public class RestAssuredTest {
//    RequestSpecification request = RestAssured.given().baseUri("https://petstore.swagger.io/v2");
//
//    public static String makeRandomEmail() {
//        String domain = "mailinator.com";
//        return String.format(
//                "otus.test.%s@%s",
//                randomAlphanumeric(10).toLowerCase(),
//                domain
//        );
//    }
//
//    @Test
//    @Description("Create user with all params")
//    public void createUser() {
//        JsonObject requestParams = new JsonObject();
//        requestParams.addProperty("id", 0);
//        requestParams.addProperty("username", "Test");
//        requestParams.addProperty("firstName", "Test");
//        requestParams.addProperty("lastName", "Test");
//        requestParams.addProperty("password", "password111");
//        requestParams.addProperty("email",  makeRandomEmail());
//        requestParams.addProperty("phone","phone");
//        requestParams.addProperty("userStatus",0);
//
//        request.header("Content-Type", "application/json");
//        request.body(requestParams.toString());
//
//        Response response = request
//                        .when()
//                        .post("/user")
//                        .then()
//                        .statusCode(200)
//                        .body("message", greaterThan("0"))
//                        .extract()
//                        .response();
//        System.out.println(response.asPrettyString());
//    }
//
//    @Test
//    @Description("Create user with no params")
//    public void createUserNoParams() {
//        JsonObject requestParams = new JsonObject();
//        request
//                .body(requestParams.toString())
//                .header("Content-Type", "application/json")
//                .when()
//                .post("/user")
//                .then()
//                .statusCode(200)
//                .body("message", is("0"))
//                .extract()
//                .response();
//    }
//
//    @Test
//    @Description("Get user by username")
//    public void getUserByName() {
//        String userName = "user1";
//        JsonObject requestParams = new JsonObject();
//        request
//                .body(requestParams.toString())
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/user/"+ userName +"")
//                .then()
//                .statusCode(200)
//                .body("username", is("user1"))
//                .body("email", is("user1@gmail.com"))
//                .extract()
//                .response();
//    }
//
//    @Test
//    @Description("Get user by username invalid")
//    public void getUserByNameNotFound() {
//        String userName = "1user";
//        JsonObject requestParams = new JsonObject();
//        request
//                .body(requestParams.toString())
//                .header("Content-Type", "application/json")
//                .when()
//                .get("/user/"+ userName +"")
//                .then()
//                .statusCode(404)
//                .body("message", is("User not found"))
//                .extract()
//                .response();
//    }
//}
