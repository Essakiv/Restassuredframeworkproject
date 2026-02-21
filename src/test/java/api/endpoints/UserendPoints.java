package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import api.payload.User;  // Add this import

public class UserendPoints {
    // ... rest of the code

    // Create User
    public static Response createUser(User payload)
    {
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .body(payload)
        .when()
            .post(routes.post_url);
        
        return response;
    }

    // Read User
    public static Response readUser(String userName)
    {
        Response response = given()
            .pathParam("username", userName)
        .when()
            .get(routes.get_url);
        
        return response;
    }

    // Update User
    public static Response updateUser(String userName, User payload)
    {
        Response response = given()
            .contentType(ContentType.JSON)
            .accept(ContentType.JSON)
            .pathParam("username", userName)
            .body(payload)
        .when()
            .put(routes.update_url);
        
        return response;
    }

    // Delete User
    public static Response deleteUser(String userName)
    {
        Response response = given()
            .pathParam("username", userName)
        .when()
            .delete(routes.delete_url);
        
        return response;
    }
}
