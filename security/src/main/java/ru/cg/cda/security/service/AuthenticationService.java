package ru.cg.cda.security.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;

import ru.cg.cda.security.bean.UserDetails;
import ru.cg.cda.security.domain.Credentials;
import ru.cg.cda.security.exception.AuthException;

/**
 * @author nuriev
 * @since 29.08.2014
 */
public class AuthenticationService {
  @Autowired
  @Qualifier("authenticationManager")
  private AuthenticationManager authManager;
  @Autowired
  private UserDetailsService userService;

  public UserDetails authenticate(@RequestBody Credentials credentials, HttpServletResponse response) throws AuthException {
    try {

      UsernamePasswordAuthenticationToken authenticationToken =
          new UsernamePasswordAuthenticationToken(credentials.getUsername(), credentials.getPassword());
      Authentication authentication = this.authManager.authenticate(authenticationToken);
      SecurityContextHolder.getContext().setAuthentication(authentication);
      /**
       * Reload user as password of authentication principal will be null after authorization and
       * password is needed for token generation
       */
      UserDetails userDetails = (UserDetails) this.userService.loadUserByUsername(credentials.getUsername());

      return userDetails;
    }
    catch (BadCredentialsException ex) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      throw ex;
    }
  }

}
