package com.very.hard.service.config;

import java.util.concurrent.Executor;
import javax.inject.Singleton;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import com.very.hard.service.core.RiddleService;

public class AppConfig extends ResourceConfig {
  public AppConfig() {
    super();

    packages("com.very.hard.service");
    register(JacksonFeature.class); //use Jackson as the JSON provider
    register(new AbstractBinder() {
      @Override
      protected void configure() {
        bindFactory(WorkerPool.class).to(Executor.class).in(Singleton.class);
        bind(RiddleService.class).to(RiddleService.class).in(Singleton.class);
      }
    });
  }
}
