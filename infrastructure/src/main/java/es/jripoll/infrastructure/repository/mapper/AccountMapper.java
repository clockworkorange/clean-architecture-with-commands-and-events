package es.jripoll.infrastructure.repository.mapper;

import es.jripoll.domain.Account;
import es.jripoll.infrastructure.repository.entity.AccountEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

  AccountEntity toEntity(final Account account);

  Account toDomain(final AccountEntity account);

}
