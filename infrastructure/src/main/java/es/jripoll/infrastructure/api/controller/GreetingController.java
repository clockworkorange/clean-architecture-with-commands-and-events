package es.jripoll.infrastructure.api.controller;

import es.jripoll.application.GreetingService;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import lombok.AllArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/hello")
@AllArgsConstructor
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.TEXT_PLAIN)
public class GreetingController {

  private final GreetingService greetingService;

  @GET
  @Path("/greeting/{name}")
  public String greeting(@PathParam final String name) {
    return greetingService.greeting(name);
  }

  @GET
  public String hello() {
    return "hello";
  }
}