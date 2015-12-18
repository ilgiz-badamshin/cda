package ru.cg.cda.security.bean;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

/**
 * @author Badamshin
 */
public class UserDetails extends org.springframework.security.core.userdetails.User {

  private static final long serialVersionUID = -1972269170647610969L;
  private String fullName;

  public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities, String fullName) {
    super(username, password, authorities);
    this.fullName = fullName;
  }

  public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
  }

  public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities, String fullName) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.fullName = fullName;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }
}
