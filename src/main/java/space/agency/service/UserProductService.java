package space.agency.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import space.agency.dto.ProductDTO;
import space.agency.model.Product;
import space.agency.model.User;
import space.agency.model.UserProduct;
import space.agency.repository.UserProductRepository;

@Service
@Slf4j
@AllArgsConstructor
public class UserProductService {
  
  private UserProductRepository repository;
  
  private UserService userService;
  
  private ProductService productService;
  
  @Transactional
  public ProductDTO create(Integer productId, String userLogin) {
    log.info("CREATE user product with params: userLogin:[{}], productId:[{}]", userLogin, productId);
    User user = userService.findByLogin(userLogin);
    Product product = productService.read(productId);
    repository.save(new UserProduct(null, user, product));
    return product.dto(true);
  }

}
