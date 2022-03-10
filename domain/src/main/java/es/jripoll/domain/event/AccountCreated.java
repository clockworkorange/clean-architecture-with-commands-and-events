package es.jripoll.domain.event;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AccountCreated implements DomainEvent {

  private String email;
  private String name;
  private String surname;
  private Instant birthday;
  private boolean enabled;
  private boolean canceled;

}
