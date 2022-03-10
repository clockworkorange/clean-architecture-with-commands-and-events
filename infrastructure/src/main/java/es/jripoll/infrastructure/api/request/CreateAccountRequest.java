package es.jripoll.infrastructure.api.request;

import java.io.Serializable;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequest implements Serializable {

  public String email;
  public String name;
  public String surname;
  public Instant birthday;

}
