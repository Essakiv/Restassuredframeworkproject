package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserendPoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests {

    User userPayload;
    public Logger logger;

    @BeforeClass
    public void setupData() {
        userPayload = new User();
        userPayload.setId(12345);
        userPayload.setUsername("testuser");
        userPayload.setFirstName("John");
        userPayload.setLastName("Doe");
        userPayload.setEmail("john@test.com");
        userPayload.setPassword("[REDACTED:PASSWORD]");
        userPayload.setPhone("1234567890");

        // Logger initialization
        logger = LogManager.getLogger(this.getClass());
    }

    @Test(priority=1)
    public void testPostUser() 
    {
        logger.info("************ Creating user ****************");
        Response response = UserendPoints.createUser(userPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***********User is created***************");
    }

    @Test(priority=2)
    public void testGetUserByName() 
    {
        logger.info("************ Reading User Info ***************");

        Response response = UserendPoints.readUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***********Reading User Info***************");
    }

    @Test(priority=3)
    public void testUpdateUserByName() 
    {
        logger.info("************ Updating User Info ***************");

        // Update data
        userPayload.setFirstName("UpdatedJohn");
        userPayload.setLastName("UpdatedDoe");

        Response response = UserendPoints.updateUser(this.userPayload.getUsername(), userPayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***********User Info Updated***************");
    }

    @Test(priority=4)
    public void testDeleteUserByName() 
    {
        logger.info("************ Deleting User ***************");

        Response response = UserendPoints.deleteUser(this.userPayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 200);

        logger.info("***********User Deleted***************");
    }
}