package es.jripoll.domain.event;

import java.time.Instant;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

/**
 * The Event's metadata
 */

@ToString
@Builder
@Getter
public class DomainEventMetadata {

  private String id;
  private Integer version;
  private Instant timeStamp;
  private String aggregateId;
  private String aggregateType;
  private String type;

}
