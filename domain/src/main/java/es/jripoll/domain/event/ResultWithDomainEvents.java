package es.jripoll.domain.event;

import java.util.List;
import lombok.Getter;

@Getter
public class ResultWithDomainEvents<T, E extends DomainEvent> {

  private final T result;
  private final List<E> events;

  public ResultWithDomainEvents(final T result, final List<E> events) {
    this.result = result;
    this.events = events;
  }
}
