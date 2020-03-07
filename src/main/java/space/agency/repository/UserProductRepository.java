package space.agency.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.agency.model.UserProduct;

@Repository
public interface UserProductRepository extends JpaRepository<UserProduct, Integer> {

}
