package data.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static io.restassured.http.ContentType.TEXT;

public class ApiSpecs {

    public static RequestSpecification request = with()
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(TEXT)
            .baseUri("http://127.0.0.1:5050")
            .basePath("/allure-docker-service");
//
//    public static ResponseSpecification response200 = new ResponseSpecBuilder()
//            .expectStatusCode(200)
//            .build();
//    public static ResponseSpecification response201 = new ResponseSpecBuilder()
//            .expectStatusCode(201)
//            .build();
//    public static ResponseSpecification response400 = new ResponseSpecBuilder()
//            .expectStatusCode(400)
//            .build();
//    public static ResponseSpecification response404 = new ResponseSpecBuilder()
//            .expectStatusCode(404)
//            .build();
}
