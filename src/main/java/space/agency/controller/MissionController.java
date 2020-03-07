package space.agency.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import space.agency.dto.MissionDTO;
import space.agency.model.Mission;
import space.agency.service.MissionService;


@RestController
@AllArgsConstructor
@RequestMapping(path = "/missions")
public class MissionController {

  private MissionService service;
  
  @GetMapping
  public List<MissionDTO> readMissions() {
    return service.list().stream().map(Mission::dto).collect(Collectors.toList());
  }
  
  @PostMapping
  public void createMission(@RequestBody MissionDTO dto) {
    service.create(dto);
  }

  @PutMapping(path = "/{id}")
  public void updateMission(@PathVariable("id") Integer id, @RequestBody MissionDTO dto) {
    service.update(id, dto);
  }
  
  @GetMapping(path = "/{id}")
  public MissionDTO readMission(@PathVariable("id") Integer id) {
    return service.read(id).dto();
  }
  
  @DeleteMapping(path = "/{id}")
  public void deleteMission(@PathVariable("id") Integer id) {
    service.delete(id);
  }
}
