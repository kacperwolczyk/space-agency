package space.agency.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import space.agency.model.Mission;

@Repository
public interface MissionRepository extends JpaRepository<Mission, Integer> {
  
  Optional<Mission> findByName(String name);
}
