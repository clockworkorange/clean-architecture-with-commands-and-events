package es.jripoll.infrastructure.metrics.sender;

import io.micrometer.core.instrument.Tag;
import java.util.Collections;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
@Slf4j
public class GreetingsMetricSender extends CounterMetricSender {

  @ConfigProperty(name = "metrics.greetings.counter.name")
  String counterName;

  @ConfigProperty(name = "metrics.greetings.tag.name")
  String tagName;

  @PostConstruct
  void setup() {
    super.setup(counterName);
  }

  public void send(final String name) {
    log.info("Send metrics - counterName {} - name {}", counterName, name);
    this.increment(Collections.singletonList(Tag.of(tagName, name)));
  }
}
