package es.jripoll.domain.event;

import lombok.Builder;
import lombok.Getter;

/**
 * The Event's envelope
 */

@Builder
@Getter
public class DomainEventEnvelope implements DomainEvent {

  private DomainEventMetadata metadata;
  private Object payload;

}
