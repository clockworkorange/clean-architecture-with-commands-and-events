package es.jripoll.infrastructure.repository.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import java.time.Instant;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "account")
public class AccountEntity extends PanacheEntityBase {

  @Id
  private String id;

  private String email;

  private String name;

  private String surname;

  private Instant birthday;

  private boolean enabled;

  private boolean canceled;

}
