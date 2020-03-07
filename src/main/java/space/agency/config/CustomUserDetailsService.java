package space.agency.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import space.agency.model.User;
import space.agency.service.UserService;
import space.agency.util.UserRole;

@Service
public class CustomUserDetailsService implements UserDetailsService {
  
  @Autowired
  private UserService userService;
  
  @Override
  public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
    User user = userService.findByLogin(login);
    if(user == null)
      throw new UsernameNotFoundException("User not found");
    org.springframework.security.core.userdetails.User userDetails = 
        new org.springframework.security.core.userdetails.User(
            user.getLogin(), 
            user.getPassword(), 
            convertAuthorities(user.getRole()));
    return userDetails;
  }
  
  private Set<GrantedAuthority> convertAuthorities(UserRole role) {
    GrantedAuthority authority = new SimpleGrantedAuthority(role.toString());
    Set<GrantedAuthority> authorities = new HashSet<>();
    authorities.add(authority);
    return authorities;
  }
}
