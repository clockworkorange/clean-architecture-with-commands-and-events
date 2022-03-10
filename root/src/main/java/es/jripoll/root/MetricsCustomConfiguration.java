package es.jripoll.root;

import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.config.MeterFilter;
import io.quarkus.runtime.configuration.ProfileManager;
import java.util.Collections;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class MetricsCustomConfiguration {

  @Produces
  @Singleton
  public MeterFilter configureAllRegistries() {
    return MeterFilter.commonTags(Collections.singletonList(Tag.of("env", ProfileManager.getActiveProfile())));
  }
}
