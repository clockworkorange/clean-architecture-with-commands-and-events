package es.jripoll.application.service;

import es.jripoll.application.interceptors.Logging;
import es.jripoll.application.publisher.DomainEventPublisher;
import es.jripoll.application.repository.AccountRepository;
import es.jripoll.domain.Account;
import es.jripoll.domain.command.CreateAccountCommand;
import es.jripoll.domain.event.DomainEvent;
import es.jripoll.domain.event.ResultWithDomainEvents;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class AccountService {

  private final AccountRepository accountRepository;
  private final DomainEventPublisher domainEventPublisher;

  @Logging
  public Account create(final CreateAccountCommand command) {
    ResultWithDomainEvents<Account, DomainEvent> accountAndEvents = Account.create(command);
    Account account = accountRepository.save(accountAndEvents.getResult());
    domainEventPublisher.publish(account.getId(), accountAndEvents.getEvents());
    return account;
  }

  @Logging
  public List<Account> all() {
    return accountRepository.findAll();
  }

  @Logging
  public Account enable(final String id) {
    Account account = accountRepository.findById(id);
    List<DomainEvent> events = account.enable();
    accountRepository.save(account);
    domainEventPublisher.publish(account.getId(), events);
    return account;
  }

  @Logging
  public Account cancel(final String id) {
    Account account = accountRepository.findById(id);
    List<DomainEvent> events = account.cancel();
    accountRepository.save(account);
    domainEventPublisher.publish(account.getId(), events);
    return account;
  }

}
