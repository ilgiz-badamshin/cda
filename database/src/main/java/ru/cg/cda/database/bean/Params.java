package ru.cg.cda.database.bean;

import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "params")
public class Params {
  private Long id;
  private Long dbVersion;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "db_version")
  public Long getDbVersion() {
    return dbVersion;
  }

  public void setDbVersion(Long dbVersion) {
    this.dbVersion = dbVersion;
  }
}
