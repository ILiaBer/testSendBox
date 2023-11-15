package data.api;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static data.api.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class ApiSpecs {


    public static RequestSpecification request = with()
            .log().uri()
            .log().headers()
            .log().body()
            .filter(withCustomTemplates())
            .contentType(JSON)
            .baseUri("http://localhost:5050")
            .basePath("/api");
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
