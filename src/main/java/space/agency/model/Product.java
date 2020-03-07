package space.agency.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import space.agency.dto.CoordinateDTO;
import space.agency.dto.ProductDTO;


@Table(name="T_PRODUCTS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;
 
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "T_MISSIONS_ID")
  private Mission mission;

  @Column(name = "ACQUISITION_DATE")
  private LocalDate acquisitionDate;
  
  @Column(name = "PRICE")
  private Double price;
  
  @Column(name = "UUID")
  private String uuid;
  
  @Embedded
  private FootPrint footPrint;
  
  public ProductDTO dto(boolean ordered) {
    return ProductDTO.builder()
        .id(id)
        .mission(mission.dto())
        .acquisitionDate(acquisitionDate)
        .price(price)
        .url(ordered == true ? generateProductUrl() : null)
        .firstCoordinate(new CoordinateDTO(footPrint.getX1(), footPrint.getY1()))
        .secondCoordinate(new CoordinateDTO(footPrint.getX2(), footPrint.getY2()))
        .thirdCoordinate(new CoordinateDTO(footPrint.getX3(), footPrint.getY3()))
        .fourthCoordinate(new CoordinateDTO(footPrint.getX4(), footPrint.getY4()))
        .build();
  }
  
  private String generateProductUrl() {
    return "http://localhost:8080/products/" + uuid;
  }
}
