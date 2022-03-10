package es.jripoll.application.publisher;

import es.jripoll.domain.event.DomainEvent;
import java.util.List;

public interface DomainEventPublisher {

  void publish(final String key, final List<DomainEvent> events);

}
