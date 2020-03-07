package space.agency.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import space.agency.dto.OrderDTO;
import space.agency.dto.ProductDTO;
import space.agency.service.ProductService;
import space.agency.service.UserProductService;
import space.agency.util.ImageType;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/products")
public class ProductController {

  private ProductService service;
  
  private UserProductService userProductService;
  
  @PostMapping
  public void createProduct(@RequestBody ProductDTO dto, @RequestParam Integer missionId) {
    service.create(missionId, dto);
  }
  
  @PostMapping(path = "/order")
  public List<ProductDTO> createUserProduct(@RequestBody OrderDTO order) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String currentPrincipalName = authentication.getName();
    return order.getProductIds().stream().map(id -> userProductService.create(id, currentPrincipalName)).collect(Collectors.toList());
  }
  
  @GetMapping(path = "/{id}")
  public ProductDTO readProduct(@PathVariable("id") Integer id) {
    return service.read(id).dto(false);
  }
  
  @DeleteMapping(path = "/{id}")
  public void deleteProduct(@PathVariable("id") Integer id) {
    service.delete(id);
  }

  @GetMapping(path = "/mission/{mission}")
  public List<ProductDTO> getByMission(@PathVariable("mission") String missionName) {
    return service.findByMission(missionName).stream().map(p -> p.dto(false)).collect(Collectors.toList());
  }
  
  @GetMapping(path = "/type/{type}")
  public List<ProductDTO> getByType(@PathVariable("type") ImageType type) {
    return service.findByType(type).stream().map(p -> p.dto(false)).collect(Collectors.toList());
  }
  
  @GetMapping(path = "/date/{date}/before")
  public List<ProductDTO> getByDateBefore(@PathVariable(name = "date", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return service.findByAcquisitionDateBefore(date).stream().map(p -> p.dto(false)).collect(Collectors.toList());
  }
  
  @GetMapping(path = "/date/{date}/after")
  public List<ProductDTO> getByDateAfter(@PathVariable(name = "date", required=false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return service.findByAcquisitionDateAfter(date).stream().map(p -> p.dto(false)).collect(Collectors.toList());
  }
}
