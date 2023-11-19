package utils;

import data.models.api.AllureResponse;
import data.models.api.ProjectsResponse;
import io.restassured.response.ResponseBody;

import static data.api.ApiSpecs.request;
import static io.restassured.RestAssured.given;
import static utils.BaseTest.allureProjectId;

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

    public static void createProject(){
        given(request)
                .when()
                .header("Accept", "application/json")
                .body(allureProjectId)
                .post("/projects");

    }

}
