package tests;

import base.BaseTest;
import client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;


public class GetUserTest extends BaseTest {

//    RestClient restClient;
    @BeforeMethod
    public void create() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test(priority = 3)
    public void getAllUsers() {
//        restClient = new RestClient();

        restClient.get("/public/v2/users", true,true)
                .then().log().all()
                .statusCode(200);
    }
    //5850425
    @Test(priority = 2)
    public void getSpecificUser() {
//        restClient = new RestClient();

        restClient.get("/public/v2/users/5850425", true,true)
                .then().log().all()
                .statusCode(200);
//                .and()
//                .body("id",equalTo("5850425"));
    }
    @Test(priority = 1)
    public void getSPecificUser_WithQueryParam() {
//        restClient = new RestClient();
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("gender", "female");
        queryParams.put("status", "active");

        restClient.get( "/public/v2/users",queryParams, true, true)
                .then().log().all()
                .statusCode(200);

    }
}
