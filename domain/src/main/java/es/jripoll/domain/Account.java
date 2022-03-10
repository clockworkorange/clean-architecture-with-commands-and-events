package es.jripoll.domain;

import es.jripoll.domain.command.CreateAccountCommand;
import es.jripoll.domain.event.AccountCanceled;
import es.jripoll.domain.event.AccountCreated;
import es.jripoll.domain.event.AccountDisabled;
import es.jripoll.domain.event.AccountEnabled;
import es.jripoll.domain.event.DomainEvent;
import es.jripoll.domain.event.ResultWithDomainEvents;
import es.jripoll.domain.exception.AccountEmailIsMandatoryException;
import es.jripoll.domain.exception.AccountIsAlreadyCanceled;
import es.jripoll.domain.exception.AccountIsAlreadyEnabled;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Account {

  private String id;
  private String email;
  private String name;
  private String surname;
  private Instant birthday;
  private boolean enabled = false;
  private boolean canceled = false;

  private Account(CreateAccountCommand command) {
    this.id = UUID.randomUUID().toString();
    this.email = command.getEmail();
    this.name = command.getName();
    this.surname = command.getSurname();
    this.birthday = command.getBirthday();
  }

  public static ResultWithDomainEvents<Account, DomainEvent> create(final CreateAccountCommand command) {
    if (command.getEmail() == null || command.getEmail().isEmpty()) {
      throw new AccountEmailIsMandatoryException();
    }
    Account account = new Account(command);
    AccountCreated event = new AccountCreated(
        account.getEmail(),
        account.getName(),
        account.getSurname(),
        account.getBirthday(),
        account.isEnabled(),
        account.isCanceled()
    );
    return new ResultWithDomainEvents<>(account, Collections.singletonList(event));
  }

  public List<DomainEvent> enable() {
    if (this.enabled) {
      throw new AccountIsAlreadyEnabled();
    } else {
      this.enabled = true;
    }
    return Collections.singletonList(new AccountEnabled(this.email));
  }

  public List<DomainEvent> cancel() {
    List<DomainEvent> events = new ArrayList<>();
    if (this.canceled) {
      throw new AccountIsAlreadyCanceled();
    } else {
      if (this.enabled) {
        this.enabled = false;
        events.add(new AccountDisabled(this.email));
      }
      this.canceled = true;
      events.add(new AccountCanceled(this.email));
    }

    return events;
  }
}
