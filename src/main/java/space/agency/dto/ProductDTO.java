package space.agency.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Integer id;
 
  private MissionDTO mission;

  private LocalDate acquisitionDate;
  
  private Double price;
  
  private String url;
  
  private CoordinateDTO firstCoordinate;
  
  private CoordinateDTO secondCoordinate;
  
  private CoordinateDTO thirdCoordinate;
  
  private CoordinateDTO fourthCoordinate;
  
  @JsonIgnore
  public FootPrintDTO getCoordinates() {
    return new FootPrintDTO(firstCoordinate, secondCoordinate, thirdCoordinate, fourthCoordinate);
  }
}
