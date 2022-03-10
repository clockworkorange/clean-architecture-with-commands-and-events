package es.jripoll.domain.event;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AccountDisabled implements DomainEvent {

  private String email;

}
