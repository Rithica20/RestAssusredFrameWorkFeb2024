package tests;

import client.RestClient;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;


public class GetUserTest {

    RestClient restClient;

    @Test
    public void getAllUsers() {
        restClient = new RestClient();

        restClient.get("/public/v2/users", true)
                .then().log().all()
                .statusCode(200);
    }
    //5850425
    @Test
    public void getSpecificUser() {
        restClient = new RestClient();

        restClient.get("/public/v2/users/5850425", true)
                .then().log().all()
                .statusCode(200);
//                .and()
//                .body("id",equalTo("5850425"));
    }
    @Test
    public void getSPecificUser_WithQueryParam() {
        restClient = new RestClient();
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("gender", "female");
        queryParams.put("status", "active");

        restClient.get( "/public/v2/users",queryParams, null, true)
                .then().log().all()
                .statusCode(200);

    }
}
