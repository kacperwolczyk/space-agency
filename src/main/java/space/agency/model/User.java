package space.agency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.agency.util.UserRole;

@Data
@Table(name="T_USERS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  
  @NotNull
  @Column(name = "LOGIN", unique = true)
  private String login;

  @NotNull
  @Column(name = "PASSWORD")
  private String password;
  
  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(name = "ROLE")
  private UserRole role;

}
