package space.agency.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import space.agency.dto.ProductDTO;
import space.agency.model.FootPrint;
import space.agency.model.Mission;
import space.agency.model.Product;
import space.agency.repository.ProductRepository;
import space.agency.util.ImageType;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {
  
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  static class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 123815525195994126L;

    public ProductNotFoundException(Integer id) {
      super("Product with id: [" + id + "] does not exist");
    }
    
    public ProductNotFoundException(String uuid) {
      super("Product with uuid: [" + uuid + "] does not exist");
    }
  }

  private ProductRepository repository;
  
  private MissionService missionService;
  
  @Transactional
  public void create(Integer missionId, ProductDTO dto) {
    log.info("CREATE product with params:[{}]", dto.toString());
    Mission mission = missionService.read(missionId);
    repository.save(new Product(null, mission, dto.getAcquisitionDate(), dto.getPrice(), UUID.randomUUID().toString(), FootPrint.of(dto.getCoordinates())));
  }

  @Transactional
  public void delete(Integer id) {
    log.info("DELETE product with id:[{}]", id);
    Product product = repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    repository.delete(product);
  }
  
  public Product read(Integer id) {
    log.info("READ product with id:[{}]", id);
    return repository.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
  }
  
  public Product findByUuid(String uuid) {
    log.info("READ product with uuid:[{}]", uuid);
    return repository.findByUuid(uuid).orElseThrow(() -> new ProductNotFoundException(uuid));
  }
  
  public List<Product> findByMission(String missionName) {
    log.info("FIND products by mission. Mission name:[{}]", missionName);
    Mission mission = missionService.findByName(missionName);
    return repository.findByMission(mission);
  }
  
  public List<Product> findByType(ImageType type) {
    log.info("FIND products by image type. Image type:[{}]", type);
    return repository.findByType(type);
  }
  
  public List<Product> findByAcquisitionDateBefore(LocalDate date) {
    log.info("FIND products by acquisition date before:[{}]", date);
    return repository.findByAcquisitionDateBefore(date);
  }
  
  public List<Product> findByAcquisitionDateAfter(LocalDate date) {
    log.info("FIND products by acquisition date aster:[{}]", date);
    return repository.findByAcquisitionDateAfter(date);
  }
  
  public List<Product> findByAcquisitionDateBetween(LocalDate startDate, LocalDate endDate) {
    log.info("FIND products by acquisition date between:[{}] and:[{}]", startDate, endDate);
    return repository.findByAcquisitionDateBetween(startDate, endDate);
  }
}
