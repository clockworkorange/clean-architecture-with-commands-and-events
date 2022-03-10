package es.jripoll.infrastructure.publisher;

import es.jripoll.application.publisher.DomainEventPublisher;
import es.jripoll.domain.event.DomainEvent;
import es.jripoll.domain.event.DomainEventEnvelope;
import es.jripoll.domain.event.DomainEventMetadata;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import javax.inject.Singleton;
import org.jboss.logging.Logger;

@Singleton
public class DomainEventLogPublisher implements DomainEventPublisher {

  private static final Logger LOG = Logger.getLogger(DomainEventLogPublisher.class);

  @Override
  public void publish(final String key, final List<DomainEvent> events) {
    events.forEach(event -> {
      DomainEventEnvelope domainEventEnvelope = DomainEventEnvelope.builder()
          .metadata(getMetadata(key, event))
          .payload(event)
          .build();

      LOG.infov("Publish message with id: {0}, metadata: {1}, payload: {2}", key, domainEventEnvelope.getMetadata(),
          domainEventEnvelope.getPayload());
    });
  }

  DomainEventMetadata getMetadata(final String key, final DomainEvent event) {
    return DomainEventMetadata.builder()
        .id(UUID.randomUUID().toString())
        .version(1)
        .timeStamp(Instant.now())
        .aggregateType("Domain")
        .aggregateId(key)
        .type(event.getClass().getSimpleName().toUpperCase())
        .build();
  }
}
