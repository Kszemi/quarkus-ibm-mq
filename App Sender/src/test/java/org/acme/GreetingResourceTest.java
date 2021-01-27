package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/quarkus/hello")
          .then()
             .statusCode(200)
             .body(is("hello"));
    }

    @Test
    public void testHelloUserEndpoint() {
        String user = UUID.randomUUID().toString();

        given()
                .pathParam("user", user)
                .when().get("quarkus/hello/{user}")
                .then()
                    .statusCode(200)
                    .body(is("hello" + " dear " + user));
    }

}
