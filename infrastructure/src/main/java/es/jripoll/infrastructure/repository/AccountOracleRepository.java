package es.jripoll.infrastructure.repository;

import es.jripoll.application.repository.AccountRepository;
import es.jripoll.domain.Account;
import es.jripoll.infrastructure.repository.entity.AccountEntity;
import es.jripoll.infrastructure.repository.mapper.AccountMapper;
import java.util.List;
import java.util.stream.Collectors;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
@Transactional
public class AccountOracleRepository implements AccountRepository {

  private final AccountMapper accountMapper;

  @Override
  public Account findById(final String id) {
    return accountMapper.toDomain(AccountEntity.findById(id));
  }

  @Override
  public Account findByEmail(final String email) {
    return accountMapper.toDomain(AccountEntity.find("email", email).firstResult());
  }

  @Override
  public List<Account> findAll() {
    return AccountEntity.findAll().stream()
        .map(panacheEntityBase -> accountMapper.toDomain((AccountEntity) panacheEntityBase))
        .collect(Collectors.toList()
        );
  }

  @Override
  @Transactional
  public Account save(final Account account) {
    AccountEntity accountEntity;
    accountEntity = accountMapper.toEntity(account);
    AccountEntity.getEntityManager().merge(accountEntity);
    return accountMapper.toDomain(accountEntity);
  }
}
