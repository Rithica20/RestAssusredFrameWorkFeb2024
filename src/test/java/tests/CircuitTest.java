package tests;

import base.BaseTest;
import org.testng.annotations.Test;

public class CircuitTest extends BaseTest {

    @Test
    public void getAllUsers() {

        restClient.get("/api/f1/2017/circuits.json", true)
                .then().log().all()
                .statusCode(200);
    }
}
