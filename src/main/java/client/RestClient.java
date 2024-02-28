package client;

import frameworkexception.FrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.when;

public class RestClient {
    //1. to create different request specs --- RequestSpecification
    //2 . to create get and post request --- Response

    //moved to config.properties file
//    private static final String BASE_URI = "https://gorest.co.in";
//    private static final String BEARER_TOKEN ="1f99558079902d51dd2c7e9ea67f4859509b01c2892b82d5ea6a9cf4fe07d0d4";

    private static RequestSpecBuilder specbuilder;
    Properties prop;
    String baseURI;

    public RestClient(Properties prop,String baseURI)
    {
        specbuilder = new RequestSpecBuilder();
        this.prop = prop;
        this.baseURI = baseURI;
    }
    //Replaced static block with constructor as static block creates one reference in CMA memory and keeps it same and coz of this authorization is added multiple times
////creating static block because static block execute whenever we create object like constructor it works
//    static {
//        specbuilder = new RequestSpecBuilder();
//    }
    private void addAuthorizationHeader(){
        specbuilder.addHeader("Authorization","Bearer "+prop.getProperty("tokenID"));
    }
    private void setContentType(String contentType) {
        switch (contentType.toLowerCase()) {
            case "json":
                specbuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specbuilder.setContentType(ContentType.XML);
                break;
            case "multipart":
                specbuilder.setContentType(ContentType.MULTIPART);
                break;
            case "text":
                specbuilder.setContentType(ContentType.TEXT);
                break;
            default:
                System.out.println("Please enter valid content type"+contentType);
                throw new FrameworkException("Invalid content type");
        }
    }
//    private RequestSpecification createRequestSpec(boolean includeAuth) {
//        specBuilder.setBaseUri(baseURI);
//        if(includeAuth) {addAuthorizationHeader();}
//        return specBuilder.build();
//    }
    //for get call
    //spec ---- base uri + authorization header
    private RequestSpecification createRequestSpec(boolean includeAuth){
//        specbuilder.setBaseUri(BASE_URI);
        specbuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader();
        }
        return specbuilder.build();
    }
    //spec --- base uri + authorization header + multiple headers
    private RequestSpecification createRequestSpec(Map<String,String> headersMap,boolean includeAuth){
//        specbuilder.setBaseUri(BASE_URI);
        specbuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader();
        }
        if(headersMap!=null){
            specbuilder.addHeaders(headersMap);
        }
        return specbuilder.build();
    }
    //spec --- base uri + authorization header + multiple headers + multiple queryparams
    private RequestSpecification createRequestSpec(Map<String,String> headersMap,Map<String,String> multiQueryParam,boolean includeAuth){
//        specbuilder.setBaseUri(BASE_URI);
        specbuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader();
        }
        if(headersMap!=null){
            specbuilder.addHeaders(headersMap);
        }
        if(multiQueryParam!=null){
            specbuilder.addQueryParams(multiQueryParam);
        }
        return specbuilder.build();
    }
    //post call spec
    //- body - serialization
    //-content type = json/xml/mutipart/text
    //query param - NOT NEEDED
    private RequestSpecification createRequestSpec(Object requestbody,String contentType,boolean includeAuth){
//        specbuilder.setBaseUri(BASE_URI);
        specbuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader();
        }
        //specbuilder.setContentType(contentType); -- wrong approach
        setContentType(contentType);
        if (requestbody!=null){
            specbuilder.setBody(requestbody);
        }
        return specbuilder.build();
    }
    private RequestSpecification createRequestSpec(Object requestbody,String contentType,Map<String,String> headersMap,boolean includeAuth){
//        specbuilder.setBaseUri(BASE_URI);
        specbuilder.setBaseUri(baseURI);
        if(includeAuth){
            addAuthorizationHeader();
        }
        //specbuilder.setContentType(contentType); -- wrong approach
        setContentType(contentType);
        if(headersMap!=null){
            specbuilder.addHeaders(headersMap);
        }
        if (requestbody!=null){
            specbuilder.setBody(requestbody);
        }
        return specbuilder.build();
    }

    //Http Methods Utils:
//    public Response get(String serviceUrl, boolean log) {
//        if (log) {
//            return RestAssured.given(createRequestSpec()).log().all()
//                    .when()
//                    .get(serviceUrl);
//        }
//        return RestAssured.given(createRequestSpec()).when().get(serviceUrl);
//    }


    public Response get(String serviceUrl,boolean log,boolean includeAuth) {
        if (log) {
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return	RestAssured.given(createRequestSpec(includeAuth))
                .when()
                .get(serviceUrl);
    }
    public Response get(String serviceUrl,Map<String,String> headersMap,boolean log,boolean includeAuth) {
        if(log){
            return	RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return	RestAssured.given(createRequestSpec(headersMap,includeAuth))
                .when()
                .get(serviceUrl);
    }
    public Response get(String serviceUrl,Map<String,String> headersMap,Map<String,String> queryparam,boolean log,boolean includeAuth) {
        if (log){
            return	RestAssured.given(createRequestSpec(headersMap,queryparam,includeAuth)).log().all()
                    .when()
                    .get(serviceUrl);
        }
        return	RestAssured.given(createRequestSpec(headersMap,queryparam,includeAuth))
                .when()
                .get(serviceUrl);
    }
    //request spec for post calls
   //passing the body ,and we need to tell the content type its, json or html or xml
    //post call
    public Response post(String serviceUrl, String contentType, Object body,boolean log,boolean includeAuth){
        if(log){
            return RestAssured.given(createRequestSpec(body,contentType,includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,includeAuth))
                .when()
                .post(serviceUrl);
    }
    public Response post(String serviceUrl, String contentType, Object body,Map<String,String> headersMap,boolean log,boolean includeAuth){
        if (log){
            return RestAssured.given(createRequestSpec(body,contentType,headersMap,includeAuth)).log().all()
                    .when()
                    .post(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,headersMap,includeAuth))
                .when()
                .post(serviceUrl);
    }
    //put
    public Response put(String serviceUrl, String contentType, Object body,boolean log,boolean includeAuth){
        if (log){
            return RestAssured.given(createRequestSpec(body,contentType,includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,includeAuth))
                .when()
                .put(serviceUrl);
    }
    public Response put(String serviceUrl, String contentType, Object body,Map<String,String> headersMap,boolean log,boolean includeAuth){
        if (log){
            return RestAssured.given(createRequestSpec(body,contentType,includeAuth)).log().all()
                    .when()
                    .put(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,headersMap,includeAuth))
                .when()
                .put(serviceUrl);
    }
    //patch
    public Response patch(String serviceUrl, String contentType, Object body,boolean log,boolean includeAuth){
        if(log){
            return RestAssured.given(createRequestSpec(body,contentType,includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,includeAuth))
                .when()
                .patch(serviceUrl);
    }
    public Response patch(String serviceUrl, String contentType, Object body,Map<String,String> headersMap,boolean log,boolean includeAuth){
        if (log){
            return RestAssured.given(createRequestSpec(body,contentType,headersMap,includeAuth)).log().all()
                    .when()
                    .patch(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(body,contentType,headersMap,includeAuth))
                .when()
                .patch(serviceUrl);
    }
    //delete
    public Response delete(String serviceUrl,boolean log,boolean includeAuth){
        if (log){
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .delete(serviceUrl);
        }
        return RestAssured.given(createRequestSpec(includeAuth))
                .when()
                .delete(serviceUrl);
    }
}

