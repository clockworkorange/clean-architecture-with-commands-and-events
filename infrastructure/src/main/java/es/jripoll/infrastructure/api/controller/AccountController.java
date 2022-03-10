package es.jripoll.infrastructure.api.controller;


import es.jripoll.application.service.AccountService;
import es.jripoll.domain.Account;
import es.jripoll.domain.exception.AccountEmailIsMandatoryException;

import es.jripoll.infrastructure.api.mapper.AccountMapper;
import es.jripoll.infrastructure.api.request.CreateAccountRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/account")
@AllArgsConstructor
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AccountController {

  private final AccountService accountService;
  private final AccountMapper accountMapper;

  @POST
  public Response create(final CreateAccountRequest request) {
    try {
      Account account = accountService.create(accountMapper.toCommand(request));
      return Response.ok(accountMapper.toResponse(account)).status(201).build();
    } catch (AccountEmailIsMandatoryException accountEmailIsMandatoryException) {
      return Response.ok().status(400).build();
    }
  }

  @POST
  @Path("/{id}/enable")
  public Response enable(@PathParam("id") final String id) {
    return Response.ok(accountMapper.toResponse(accountService.enable(id))).build();
  }

  @POST
  @Path("/{id}/cancel")
  public Response cancel(@PathParam("id") final String id) {
    return Response.ok(accountMapper.toResponse(accountService.cancel(id))).build();
  }

  @GET
  @Path("/all")
  public Response all() {
    return Response.ok(accountMapper.toResponse(accountService.all())).build();
  }
}
