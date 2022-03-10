package es.jripoll.domain.command;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateAccountCommand implements DomainCommand {

  private String email;
  private String name;
  private String surname;
  private Instant birthday;

}
