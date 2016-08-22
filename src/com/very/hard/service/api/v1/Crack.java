package com.very.hard.service.api.v1;

import static javax.ws.rs.core.Response.Status.SERVICE_UNAVAILABLE;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import com.very.hard.service.model.v1.CrackResponse;
import com.very.hard.service.core.RiddleService;

@Path("/crack")
public class Crack {
  private static final String RIDDLE = "riddle";

  @Inject
  private RiddleService riddleService;
  @Inject
  private Executor executor;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public void getAddress(@Suspended AsyncResponse response,
    @QueryParam(RIDDLE) String riddle)
  {
    response.setTimeout(1000, TimeUnit.MILLISECONDS);
    response.setTimeoutHandler(r -> {
      r.resume(Response.status(SERVICE_UNAVAILABLE).entity("Timed out").build());
    });

    CompletableFuture.supplyAsync(() -> riddleService.crack(riddle), executor)
      .thenApply(addresses -> CrackResponse.ok(addresses))
      .thenAccept((okResponse) -> {
        response.resume(okResponse);
        // TODO log
      })
      .exceptionally(e -> {
        response.resume(Response.serverError().build());
        return null;
        // TODO log
      });
  }
}
