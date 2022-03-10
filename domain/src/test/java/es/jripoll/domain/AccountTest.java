package es.jripoll.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import es.jripoll.domain.command.CreateAccountCommand;
import es.jripoll.domain.event.AccountCanceled;
import es.jripoll.domain.event.AccountCreated;
import es.jripoll.domain.event.AccountDisabled;
import es.jripoll.domain.event.DomainEvent;
import es.jripoll.domain.event.ResultWithDomainEvents;
import es.jripoll.domain.exception.AccountEmailIsMandatoryException;
import es.jripoll.domain.exception.AccountIsAlreadyCanceled;
import es.jripoll.domain.exception.AccountIsAlreadyEnabled;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.Test;

class AccountTest {

  @Test
  void create_an_account() {
    //given:
    CreateAccountCommand createAccountCommand = new CreateAccountCommand(
        "julio.ripoll@osoco.es",
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    );

    //when:
    ResultWithDomainEvents<Account, DomainEvent> result = Account.create(createAccountCommand);

    //then:
    assertDoesNotThrow(() -> {
    });
    assertNotNull(result.getResult());
    assertFalse(result.getEvents().isEmpty());
    assertEquals(AccountCreated.class, result.getEvents().get(0).getClass());
  }

  @Test
  void create_an_account_without_email() {
    //given:
    CreateAccountCommand createAccountCommand = new CreateAccountCommand(
        null,
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    );

    //expect:
    assertThrows(AccountEmailIsMandatoryException.class, () -> Account.create(createAccountCommand));
  }

  @Test
  void enable_account() {
    //given:
    Account account = Account.create(new CreateAccountCommand(
        "julio.ripoll@osoco.es",
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    )).getResult();

    //when:
    account.enable();

    //then:
    assertTrue(account.isEnabled());
  }

  @Test
  void enable_enabled_account() {
    //given:
    Account account = Account.create(new CreateAccountCommand(
        "julio.ripoll@osoco.es",
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    )).getResult();
    account.enable();

    //expect:
    assertThrows(AccountIsAlreadyEnabled.class, account::enable);
  }

  @Test
  void cancel_enabled_account() {
    //given:
    Account account = Account.create(new CreateAccountCommand(
        "julio.ripoll@osoco.es",
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    )).getResult();
    account.enable();

    //when:
    List<DomainEvent> events = account.cancel();

    //then:
    assertEquals(AccountDisabled.class, events.get(0).getClass());
    assertEquals(AccountCanceled.class, events.get(1).getClass());
  }

  @Test
  void cancel_canceled_account() {
    //given:
    Account account = Account.create(new CreateAccountCommand(
        "julio.ripoll@osoco.es",
        "Julio",
        "Ripoll",
        Instant.parse("1982-11-27T00:00:00Z")
    )).getResult();
    account.cancel();

    //expect:
    assertThrows(AccountIsAlreadyCanceled.class, account::cancel);
  }
}
