package tests;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.User;
import utils.StringUtils;

import static org.hamcrest.Matchers.equalTo;

public class CreateUserTest extends BaseTest {

    @BeforeMethod
    public void create() {
        restClient = new RestClient(prop, baseURI);
    }
    @Test
    public void createUser(){

//        RestClient restClient = new RestClient();
        User user = new User("rithica", StringUtils.getRandomEmailId(),"female","active");



        Integer id = restClient.post(GOREST_ENDPOINT,"json",user,true,true)
                .then().log().all()
                .statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract()
                .path("id");

        System.out.println("user id is:: "+id);

//        //get call
//        RestClient restClient1 = new RestClient();

//       restClient1.get("/public/v2/users/"+id,true)
//                .then()
//                .statusCode(200)
//               .and()
//               .body("id", equalTo(id));


    }
}
