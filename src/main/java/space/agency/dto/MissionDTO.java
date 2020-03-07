package space.agency.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import space.agency.util.ImageType;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MissionDTO {

  private Integer id;

  private String name;

  private ImageType imageType;

  private LocalDate startDate;
  
  private LocalDate endDate;
}
