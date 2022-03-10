package es.jripoll.infrastructure.api.mapper;

import es.jripoll.domain.Account;
import es.jripoll.domain.command.CreateAccountCommand;
import es.jripoll.infrastructure.api.request.CreateAccountRequest;
import es.jripoll.infrastructure.api.response.AccountResponse;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

  CreateAccountCommand toCommand(final CreateAccountRequest request);

  AccountResponse toResponse(final Account account);

  List<AccountResponse> toResponse(final List<Account> account);

}
