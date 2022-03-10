package es.jripoll.infrastructure.metrics;

import es.jripoll.application.metrics.MetricSender;
import es.jripoll.infrastructure.metrics.sender.GreetingsMetricSender;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class MetricMicrometerSender implements MetricSender {

  private final GreetingsMetricSender greetingsMetricSender;

  public void sendGreetingsMetric(final String name) {
    greetingsMetricSender.send(name);
  }
}
