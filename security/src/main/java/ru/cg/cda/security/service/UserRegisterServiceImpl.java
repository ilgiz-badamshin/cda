package ru.cg.cda.security.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.SecurityUser;
import ru.cg.cda.database.dao.SecurityUserDao;
import ru.cg.cda.security.bean.UserDetails;
import ru.cg.cda.security.domain.Credentials;
import ru.cg.cda.security.exception.UserAlreadyExistsException;

/**
 * @author Ildar
 */
@Transactional
public class UserRegisterServiceImpl implements UserRegisterService {

  private SecurityUserDao userDao;

  @Autowired
  private BCryptPasswordEncoder passwordEncoder;

  public UserRegisterServiceImpl(SecurityUserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails register(Credentials credentials) throws UserAlreadyExistsException {
    SecurityUser user = userDao.get(credentials.getUsername());
    if (user != null)
      throw new UserAlreadyExistsException("user with username=" + credentials.getUsername() + " already exist");

    user = new SecurityUser();
    user.setUsername(credentials.getUsername());
    user.setFullName(credentials.getFullName());
    user.setPassword(passwordEncoder.encode(credentials.getPassword()));
    userDao.saveOrUpdate(user);
    return new UserDetails(user.getUsername(), user.getPassword(), new ArrayList<SimpleGrantedAuthority>(), user.getFullName());
  }
}
