package es.jripoll.application;

import es.jripoll.application.interceptors.Logging;
import es.jripoll.application.metrics.MetricSender;
import javax.enterprise.context.ApplicationScoped;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ApplicationScoped
public class GreetingService {

  private final MetricSender metricSender;

  @Logging
  public String greeting(final String name) {
    metricSender.sendGreetingsMetric(name);
    return "hello " + name;
  }

}