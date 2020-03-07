package space.agency.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import space.agency.model.Mission;
import space.agency.model.Product;
import space.agency.util.ImageType;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

  List<Product> findByMission(Mission mission);
  
  Optional<Product> findByUuid(String uuid);
  
  @Query("SELECT e FROM Product e WHERE e.acquisitionDate<=:date")
  List<Product> findByAcquisitionDateBefore(LocalDate date);
  
  @Query("SELECT e FROM Product e WHERE e.acquisitionDate>=:date")
  List<Product> findByAcquisitionDateAfter(LocalDate date);
  
  @Query("SELECT e FROM Product e WHERE e.acquisitionDate>=:startDate AND e.acquisitionDate<=:endDate")
  List<Product> findByAcquisitionDateBetween(@Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);

  @Query("SELECT e FROM Product e LEFT JOIN e.mission r WHERE r.imageType=:imageType")
  List<Product> findByType(@Param("imageType") ImageType imageType);
}
