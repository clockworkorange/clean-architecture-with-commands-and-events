package es.jripoll.infrastructure.repository;

import es.jripoll.application.repository.AccountRepository;
import es.jripoll.domain.Account;
import java.util.List;

public class AccountMemoryRepository implements AccountRepository {

  @Override
  public Account findById(final String id) {
    return null;
  }

  @Override
  public Account findByEmail(final String email) {
    return null;
  }

  @Override
  public List<Account> findAll() {
    return null;
  }

  @Override
  public Account save(final Account voucher) {
    return null;
  }
}
