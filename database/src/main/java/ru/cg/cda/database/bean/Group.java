package ru.cg.cda.database.bean;

import java.util.List;
import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "`group`")
public class Group {
  private Long id;
  private String name;
  private List<User> users;

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


  @OneToMany(fetch = FetchType.LAZY, mappedBy = "group")
  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }
}
