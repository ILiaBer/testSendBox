package data.api;

import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.TEXT;

public class ApiSpecs {

    public static RequestSpecification request = with()
            .log().uri()
            .log().headers()
            .log().body()
            .contentType(TEXT)
            .baseUri("http://127.0.0.1:5050")
            .basePath("/allure-docker-service");
}
