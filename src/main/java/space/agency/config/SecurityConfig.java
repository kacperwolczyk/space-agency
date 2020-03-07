package space.agency.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  
  @Autowired
  private CustomUserDetailsService userDetailsService;
  
  @Bean
  public PasswordEncoder passwordEncoder()
  {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return passwordEncoder;
  }
  
  @Override
  protected void configure(HttpSecurity http) throws Exception 
  {
      http
      .httpBasic()
      .and()
      .authorizeRequests()
      .antMatchers("/h2/**")
      .permitAll()
      .antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**")
      .permitAll()
      .antMatchers(HttpMethod.POST, "/products/order").permitAll()
      .antMatchers(HttpMethod.POST).hasAuthority("ADMIN")
      .antMatchers(HttpMethod.PUT).hasAuthority("ADMIN")
      .antMatchers(HttpMethod.DELETE).hasAuthority("ADMIN")
      .antMatchers(HttpMethod.GET).permitAll()
      .and()
      .csrf().disable()
      .headers().frameOptions().disable();
  }
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
  }
}
