package tests;

import base.BaseTest;
import client.RestClient;
import constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pojo.Product;
import pojo.User;
import utils.StringUtils;

public class CreateProductTest extends BaseTest {
    @BeforeMethod
    public void create() {
        restClient = new RestClient(prop, baseURI);
    }

    @Test
    public void createAProduct() {
        Product product = new Product("pen", 15.10f, "This is camlin pen", "https://img.com", "stationery");

        Integer id = restClient.post(FAKESTORE_ENDPOINT, "json", product, true, false)
                .then().log().all()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .extract()
                .path("id");
        System.out.println("user id is:: " + id);
    }
}
