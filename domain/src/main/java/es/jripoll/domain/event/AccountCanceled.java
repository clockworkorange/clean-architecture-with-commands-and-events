package es.jripoll.domain.event;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AccountCanceled implements DomainEvent {

  private String email;

}
