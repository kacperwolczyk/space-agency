package space.agency.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import space.agency.dto.MissionDTO;
import space.agency.model.Mission;
import space.agency.repository.MissionRepository;

@Service
@Slf4j
@AllArgsConstructor
public class MissionService {
  
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  static class MissionNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 123815525195994126L;

    public MissionNotFoundException(Integer id) {
      super("Mission with id: [" + id + "] does not exist");
    }
    
    public MissionNotFoundException(String name) {
      super("Mission with name: [" + name + "] does not exist");
    }
  }
  
  @ResponseStatus(code = HttpStatus.CONFLICT)
  static class MissionNameUsedException extends RuntimeException {
    private static final long serialVersionUID = 3893499421347205702L;

    public MissionNameUsedException(String name) {
      super("Mission with name: [" + name + "] already exist");
    }
  }

  private MissionRepository repository;
  
  @Transactional
  public void create(MissionDTO dto) {
    log.info("CREATE mission with params:[{}]", dto.toString());
    if(repository.findByName(dto.getName()).isPresent()) {
      throw new MissionNameUsedException(dto.getName());
    }
    repository.save(new Mission(null, dto.getName(), dto.getImageType(), dto.getStartDate(), dto.getEndDate(), null));
  }

  public Mission read(Integer id) {
    log.info("READ mission with id:[{}]", id);
    return repository.findById(id).orElseThrow(() -> new MissionNotFoundException(id));
  }
  
  public Mission findByName(String name) {
    log.info("READ mission with name:[{}]", name);
    return repository.findByName(name).orElseThrow(() -> new MissionNotFoundException(name));
  }
  
  @Transactional
  public void update(Integer id, MissionDTO dto) {
    log.info("UPDATE mission with id:[{}]. Params:[{}]", id, dto.toString());
    Mission mission = repository.findById(id).orElseThrow(() -> new MissionNotFoundException(id));
    if(repository.findByName(dto.getName()).isPresent() && !mission.getName().equalsIgnoreCase(dto.getName())) {
      throw new MissionNameUsedException(dto.getName());
    }
    mission.update(dto);
  }
  
  @Transactional
  public void delete(Integer id) {
    log.info("DELETE mission with id:[{}]", id);
    Mission mission = repository.findById(id).orElseThrow(() -> new MissionNotFoundException(id));
    repository.delete(mission);
  }
  
  public List<Mission> list() {
    log.info("FIND ALL missions");
    return repository.findAll().stream().collect(Collectors.toList());
  }
}
