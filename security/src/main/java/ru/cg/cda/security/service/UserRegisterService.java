package ru.cg.cda.security.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.cg.cda.security.bean.UserDetails;
import ru.cg.cda.security.domain.Credentials;
import ru.cg.cda.security.exception.UserAlreadyExistsException;

/**
 * @author Badamshin
 */
public interface UserRegisterService {

  public UserDetails register(Credentials credentials) throws UsernameNotFoundException, UserAlreadyExistsException;
}
