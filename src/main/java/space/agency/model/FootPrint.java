package space.agency.model;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.agency.dto.CoordinateDTO;
import space.agency.dto.FootPrintDTO;

@Data
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class FootPrint {

  @Column(name = "COORDINATE_X1")
  private Double x1;

  @Column(name = "COORDINATE_X2")
  private Double x2;

  @Column(name = "COORDINATE_X3")
  private Double x3;

  @Column(name = "COORDINATE_X4")
  private Double x4;

  @Column(name = "COORDINATE_Y1")
  private Double y1;

  @Column(name = "COORDINATE_Y2")
  private Double y2;

  @Column(name = "COORDINATE_Y3")
  private Double y3;

  @Column(name = "COORDINATE_Y4")
  private Double y4;

  public static FootPrint of(FootPrintDTO dto) {
    Optional<CoordinateDTO> firstCoordinate = Optional.of(dto.getFirstCoordinate());
    Optional<CoordinateDTO> secondCoordinate = Optional.of(dto.getSecondCoordinate());
    Optional<CoordinateDTO> thirdCoordinate = Optional.of(dto.getThirdCoordinate());
    Optional<CoordinateDTO> fourthCoordinate = Optional.of(dto.getFourthCoordinate());
    return new FootPrint(firstCoordinate.map(CoordinateDTO::getX).orElse(null), firstCoordinate.map(CoordinateDTO::getY)
        .orElse(null), secondCoordinate.map(CoordinateDTO::getX).orElse(null), secondCoordinate.map(CoordinateDTO::getY)
            .orElse(null), thirdCoordinate.map(CoordinateDTO::getX).orElse(null), thirdCoordinate.map(
                CoordinateDTO::getY).orElse(null), fourthCoordinate.map(CoordinateDTO::getX).orElse(null),
        fourthCoordinate.map(CoordinateDTO::getY).orElse(null));
  }
}
