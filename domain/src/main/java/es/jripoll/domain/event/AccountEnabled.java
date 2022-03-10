package es.jripoll.domain.event;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
@AllArgsConstructor
public class AccountEnabled implements DomainEvent {

  private String email;

}
