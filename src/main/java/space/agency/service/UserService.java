package space.agency.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import space.agency.model.User;
import space.agency.repository.UserRepository;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
  
  private UserRepository repository;
  
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  static class UserNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 123815525195994126L;

    public UserNotFoundException(String login) {
      super("User with login: [" + login + "] does not exist");
    }
  }

  public User findByLogin(String login) {
    log.info("READ user with login:[{}]", login);
    return repository.findByLogin(login).orElseThrow(() -> new UserNotFoundException(login));
  }
}
