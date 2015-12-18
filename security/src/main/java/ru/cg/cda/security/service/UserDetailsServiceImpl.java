package ru.cg.cda.security.service;

import java.util.ArrayList;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.SecurityUser;
import ru.cg.cda.database.dao.SecurityUserDao;
import ru.cg.cda.security.bean.UserDetails;

/**
 * @author Ildar
 */
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

  private SecurityUserDao userDao;

  public UserDetailsServiceImpl(SecurityUserDao userDao) {
    this.userDao = userDao;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    SecurityUser user = userDao.get(username);
    if (user == null)
      throw new UsernameNotFoundException("user with username=" + username + " not found");
    return new UserDetails(user.getUsername(), user.getPassword(), new ArrayList<SimpleGrantedAuthority>(), user.getFullName());
  }
}
