package ru.cg.cda.database.bean;

import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "tableName")
public class Example {
  private Long id;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
