package es.jripoll.infrastructure.metrics.sender;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import java.util.List;
import javax.inject.Inject;

public class CounterMetricSender {

  @Inject
  MeterRegistry registry;

  private String counterName;

  protected void setup(final String counterName) {
    this.counterName = counterName;
  }

  protected void increment(final List<Tag> tags) {
    registry.counter(counterName, tags).increment();
  }

}


