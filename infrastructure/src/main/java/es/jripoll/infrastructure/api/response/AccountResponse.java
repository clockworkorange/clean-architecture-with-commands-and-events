package es.jripoll.infrastructure.api.response;

import java.io.Serializable;
import java.time.Instant;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountResponse implements Serializable {

  public String email;
  public String name;
  public String surname;
  public Instant birthday;

  public AccountResponse(String email, String name, String surname, Instant birthday) {
    this.email = email;
    this.name = name;
    this.surname = surname;
    this.birthday = birthday;
  }

  public AccountResponse() {
  }
}
