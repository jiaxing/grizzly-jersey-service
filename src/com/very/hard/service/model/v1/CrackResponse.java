package com.very.hard.service.model.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(Include.NON_EMPTY)
public class CrackResponse {
  private static final String OK = "ok";

  private final String status;
  private final String answer;

  public static CrackResponse ok(String answer) {
    return new CrackResponse(OK, answer);
  }

  public CrackResponse(String status, String answer) {
    this.status = status;
    this.answer = answer;
  }

  public CrackResponse(String status) {
    this(status, null);
  }

  @JsonProperty("status")
  public String getStatus() {
    return status;
  }

  @JsonProperty("answer")
  public String getAnswer() {
    return answer;
  }
}
