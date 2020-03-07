package space.agency.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import space.agency.dto.MissionDTO;
import space.agency.util.ImageType;

@Table(name = "T_MISSIONS")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Mission {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Integer id;

  @NotNull
  @Getter
  @Column(name = "NAME", unique = true)
  private String name;

  @Enumerated(EnumType.STRING)
  @Column(name = "IMAGE_TYPE")
  private ImageType imageType;

  @Column(name = "START_DATE")
  private LocalDate startDate;

  @Column(name = "END_DATE")
  private LocalDate endDate;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "mission", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
  List<Product> products = new ArrayList<>();

  public void update(MissionDTO dto) {
    this.name = dto.getName();
    this.startDate = dto.getStartDate();
    this.endDate = dto.getEndDate();
    this.imageType = dto.getImageType();
  }
  
  public MissionDTO dto() {
    return MissionDTO.builder().id(id).name(name).imageType(imageType).startDate(startDate).endDate(endDate).build();
  }
}
