package utils;

import data.models.api.AllureResponse;
import lombok.extern.apachecommons.CommonsLog;

import static data.api.ApiSpecs.request;
import static io.restassured.RestAssured.given;
import static utils.BaseTest.allureProjectId;

@CommonsLog
public class ApiTools {

    public static AllureResponse getAllureInfo() {
        return given(request)
                .when()
                .header("Accept", "application/json")
                .get("/projects/" + allureProjectId)
                .then()
                .log().status()
                .extract().as(AllureResponse.class);
    }

    public static void createProject() {
        given(request)
                .when()
                .header("Content-Type", "application/json")
                .body("{\"id\": \"" + allureProjectId + "\"}")
                .post("/projects")
                .then()
                .log().status()
                .extract().as(AllureResponse.class);
    }
}
