package tests;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GetProductsTest extends BaseTest {
    @BeforeMethod
    public void create() {
        restClient = new RestClient(prop, baseURI);
    }
    @Test
    public void getAllProducts() {


        restClient.get(FAKESTORE_ENDPOINT, true,false)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
    @Test
    public void getAProduct() {

        restClient.get(FAKESTORE_ENDPOINT+"/1", true,false)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
