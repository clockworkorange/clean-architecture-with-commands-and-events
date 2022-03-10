package es.jripoll.application.repository;

import es.jripoll.domain.Account;
import java.util.List;

public interface AccountRepository {

  Account findById(final String id);

  Account findByEmail(final String email);

  List<Account> findAll();

  Account save(final Account voucher);

}
