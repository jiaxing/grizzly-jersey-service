package com.very.hard.service.config;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.glassfish.hk2.api.Factory;

public class WorkerPool implements Factory<Executor> {
  private static final String THREAD_NAME = "%s-%%d";
  private static final String POOL_NAME = "worker-pool";
  private static final Integer POOL_SIZE = 3;
  private static final Integer QUEUE_SIZE = 50;

  private final ExecutorService executorService;

  public WorkerPool() {
    this.executorService = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, Long.MAX_VALUE,
      TimeUnit.NANOSECONDS, new ArrayBlockingQueue<Runnable>(QUEUE_SIZE, true),
      poolThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
  }

  @Override
  @Singleton
  public Executor provide() {
    return executorService;
  }

  @Override
  public void dispose(Executor executor) {
    // no-op
  }

  private ThreadFactory poolThreadFactory() {
    return new ThreadFactoryBuilder()
      .setNameFormat(String.format(THREAD_NAME, POOL_NAME))
      .setThreadFactory(Executors.defaultThreadFactory())
      .setUncaughtExceptionHandler(
        (Thread t, Throwable e) -> {
          // LOG.error("Uncaught exception in thread {}.", t, e);
        }
      )
      .build();
  }
}
