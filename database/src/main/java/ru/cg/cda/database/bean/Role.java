package ru.cg.cda.database.bean;

import java.util.List;
import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "role")
public class Role {
  private Long id;
  private String name;
  private List<Group> groups;
  private List<LinkUserRole> linkUserRoles;
  private List<AccessToGroup> accessToGroups;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "access_to_group", joinColumns = {
      @JoinColumn(name = "role_id")
  }, inverseJoinColumns = {@JoinColumn(name = "group_id")})
  public List<Group> getGroups() {
    return groups;
  }

  public void setGroups(List<Group> groups) {
    this.groups = groups;
  }


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
  public List<LinkUserRole> getLinkUserRoles() {
    return linkUserRoles;
  }

  public void setLinkUserRoles(List<LinkUserRole> linkUserRoles) {
    this.linkUserRoles = linkUserRoles;
  }

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
  public List<AccessToGroup> getAccessToGroups() {
    return accessToGroups;
  }

  public void setAccessToGroups(List<AccessToGroup> accessToGroups) {
    this.accessToGroups = accessToGroups;
  }
}
