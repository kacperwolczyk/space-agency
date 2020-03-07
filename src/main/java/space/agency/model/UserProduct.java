package space.agency.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;

@Table(name="T_USER_PRODUCTS")
@Entity
@AllArgsConstructor
public class UserProduct {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "T_USERS_ID")
  private User user;
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "T_PRODUCTS_ID")
  private Product product;
}
