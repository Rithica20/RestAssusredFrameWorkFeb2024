package tests;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
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

        restClient.get(GOREST_ENDPOINT, true,true)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
    //5850425
    @Test(priority = 2)
    public void getSpecificUser() {
//        restClient = new RestClient();

        restClient.get(GOREST_ENDPOINT+"/5850425", true,true)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode());
//                .and()
//                .body("id",equalTo("5850425"));
    }
    @Test(priority = 1)
    public void getSPecificUser_WithQueryParam() {
//        restClient = new RestClient();
        Map<String,String> queryParams = new HashMap<String, String>();
        queryParams.put("gender", "female");
        queryParams.put("status", "active");

        restClient.get( GOREST_ENDPOINT,queryParams, true, true)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode());

    }
}
