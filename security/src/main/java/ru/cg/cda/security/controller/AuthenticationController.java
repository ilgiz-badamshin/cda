package ru.cg.cda.security.controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.security.bean.UserDetails;
import ru.cg.cda.security.domain.Credentials;
import ru.cg.cda.security.domain.Session;
import ru.cg.cda.security.exception.AuthException;
import ru.cg.cda.security.exception.UserAlreadyExistsException;
import ru.cg.cda.security.service.AuthenticationService;
import ru.cg.cda.security.service.UserRegisterService;
import ru.cg.cda.security.token.TokenProc;

/**
 * @author Ildar
 */
@RestController
public class AuthenticationController {

  @Autowired
  private UserDetailsService userService;

  @Autowired
  private UserRegisterService registerService;

  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authManager;

  @Autowired
  private TokenProc tokenProc;

  @Autowired
  private AuthenticationService authenticationService;


  @RequestMapping(value = "/authenticate", method = RequestMethod.GET)
  public Session tt() {
    return new Session();
  }

  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public Session authenticate(@RequestBody Credentials credentials, HttpServletResponse response) {
    try {

      UserDetails userDetails = authenticationService.authenticate(credentials, response);
      return new Session(tokenProc.createToken(userDetails), getAuthorities(userDetails), userDetails.getFullName());
    }
    catch (BadCredentialsException ex) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
    catch (AuthException e) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
  }

  @RequestMapping(value = "/register", method = RequestMethod.POST)
  public Session register(@RequestBody Credentials credentials, HttpServletResponse response) throws UserAlreadyExistsException {
    try {
      UserDetails userDetails = this.registerService.register(credentials);

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
      Authentication authentication = this.authManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);

      return new Session(tokenProc.createToken(userDetails), getAuthorities(userDetails), userDetails.getFullName());
    }
    catch (BadCredentialsException ex) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return null;
    }
  }

  protected List<String> getAuthorities(UserDetails userDetails) {
    List<String> authorities = new ArrayList<>();
    for (GrantedAuthority authority : userDetails.getAuthorities()) {
      authorities.add(authority.getAuthority());
    }
    return authorities;
  }
}
