package es.jripoll.infrastructure;

import static io.restassured.RestAssured.given;

import es.jripoll.infrastructure.api.response.AccountResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import java.time.Instant;
import javax.ws.rs.core.MediaType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
class AccountResourceTest {

  @Test
  void test_all_endpoint() {
    // When
    Response response = given()
        .header("Content-type", MediaType.APPLICATION_JSON)
        .body("{ \"email\": \"jripoll@example.es\", \"name\": \"Julio\", \"surname\": \"Ripoll Moreno\", \"birthday\": \"1982-11-27T00:00:00Z\"}")
        .when().post("/account")
        .then()
        .extract().response();

    // Then
    Assertions.assertEquals(HttpResponseStatus.CREATED.code(), response.statusCode());
    AccountResponse accountResponse = response.getBody().as(AccountResponse.class);

    Assertions.assertEquals("jripoll@example.es", accountResponse.getEmail());
    Assertions.assertEquals("Julio", accountResponse.getName());
    Assertions.assertEquals("Ripoll Moreno", accountResponse.getSurname());
    Assertions.assertEquals(Instant.parse("1982-11-27T00:00:00Z"), accountResponse.getBirthday());
  }

}
