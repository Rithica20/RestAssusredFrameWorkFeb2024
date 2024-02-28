package tests;

import base.BaseTest;
import client.RestClient;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CircuitTest extends BaseTest {

    @BeforeMethod
    public void create() {
        restClient = new RestClient(prop, baseURI);
    }
    @Test
    public void getAllUsers() {

        restClient.get("/api/f1/2017/circuits.json", true,false)
                .then().log().all()
                .statusCode(200);
    }
}
