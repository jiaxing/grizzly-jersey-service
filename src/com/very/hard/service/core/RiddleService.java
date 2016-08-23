package com.very.hard.service.core;

import java.util.concurrent.ThreadLocalRandom;
import javax.inject.Inject;

public class RiddleService {
  private static final String ANSWER = "42";

  public String crack(String riddle) {
    think();
    return ANSWER;
  }

  private void think() {
    try {
      Thread.sleep(ThreadLocalRandom.current().nextInt(2000));
    } catch (InterruptedException e) {
    }
  }
}
