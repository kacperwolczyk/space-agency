package space.agency.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FootPrintDTO {

  private CoordinateDTO firstCoordinate;
  
  private CoordinateDTO secondCoordinate;
  
  private CoordinateDTO thirdCoordinate;
  
  private CoordinateDTO fourthCoordinate;
}
