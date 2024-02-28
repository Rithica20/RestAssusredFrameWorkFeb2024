package base;

import client.RestClient;
import configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    protected Properties prop;
    protected RestClient restClient;
    protected ConfigurationManager configManager;
    protected String baseURI;

    public final static String GOREST_ENDPOINT = "/public/v2/users";
    public final static String CIRCUITTEST_ENDPOINT = "/api/f1";

    @BeforeTest
    @Parameters({"baseURI"})
    public void setup(String baseURI) {
        RestAssured.filters(new AllureRestAssured());
        configManager = new ConfigurationManager();
        prop = configManager.initProp();
//        String baseURI = prop.getProperty("baseURI");
//        restClient = new RestClient(prop, baseURI); -- moved to test class we are going to create separte object for each tests that isthe thumb rule in api framework
        this.baseURI = baseURI;
    }
}
