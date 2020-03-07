package space.agency;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import space.agency.model.User;
import space.agency.repository.UserRepository;
import space.agency.util.UserRole;

@SpringBootApplication
public class SpaceAgencyApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpaceAgencyApplication.class, args);
  }

  @Bean
  public CommandLineRunner demoData(UserRepository repo, PasswordEncoder passwordEncoder) {
    return args -> {
      repo.save(new User(null, "admin", passwordEncoder.encode("admin"), UserRole.ADMIN));
      repo.save(new User(null, "user", passwordEncoder.encode("user"), UserRole.USER));
    };
  }
}
