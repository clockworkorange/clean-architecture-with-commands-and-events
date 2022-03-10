package es.jripoll.cucumber.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import es.jripoll.infrastructure.AccountClientService;
import es.jripoll.application.repository.AccountRepository;
import es.jripoll.infrastructure.api.request.CreateAccountRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import java.time.Instant;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;

public class AccountStepDefs {

  @Inject
  @RestClient
  AccountClientService accountClientService;

  @Inject
  AccountRepository accountRepository;

  private CreateAccountRequest createAccountRequest;
  private Response response;

  @Given("A web user with email {string}, name {string}, surname {string} and birthday {string}")
  public void all_vouchers(String email, String name, String surname, String birthday) {
    this.createAccountRequest = new CreateAccountRequest(email, name, surname, Instant.parse(birthday));
  }

  @When("send create account request")
  public void send_create_account_request() {
    try {
      response = accountClientService.create(this.createAccountRequest);
    } catch (WebApplicationException webApplicationException) {
      response = webApplicationException.getResponse();
    }
  }

  @Then("the user is successfully registered")
  public void the_user_is_successfully_registered() {
    assertEquals(201, response.getStatus());
    assertNotNull(accountRepository.findByEmail(this.createAccountRequest.email));
  }

  @Then("user cannot register")
  public void user_cannot_register() {
    assertEquals(400, response.getStatus());
  }

}
