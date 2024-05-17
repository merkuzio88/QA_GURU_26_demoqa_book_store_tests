package api.spec;

import helpers.CustomAllureListener;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static io.restassured.http.ContentType.JSON;

public class BookUserSpec {

    public static RequestSpecification bookRequestSpec = with()
            .filters(CustomAllureListener.withCustomTemplates())
            .log().all()
            .contentType(JSON);

    public static ResponseSpecification bookAddResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(201)
            .build();

    public static ResponseSpecification bookDeleteResponseSpec = new ResponseSpecBuilder()
            .log(ALL)
            .expectStatusCode(204)
            .build();
}