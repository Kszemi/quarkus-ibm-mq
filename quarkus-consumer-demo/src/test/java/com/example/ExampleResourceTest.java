package com.example;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@Transactional
public class ExampleResourceTest {

    @Test
    public void testConsumerEndpoint() {
        given()
                .when().get("/consumer")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }
}