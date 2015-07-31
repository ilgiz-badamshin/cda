package ru.cg.cda.database.bean;

import java.util.Date;
import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "`device`")
public class Device {
  private Long id;
  private String udsId;
  private Long userId;
  private String name;
  private String description;
  private String model;
  private Date createdAt;
  private Date insertedAt;
  private User user;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "uds_id")
  public String getUdsId() {
    return udsId;
  }

  public void setUdsId(String udsId) {
    this.udsId = udsId;
  }

  @Column(name = "user_id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Column(name = "model")
  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  @Column(name = "created_at")
  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  @Column(name = "inserted_at")
  public Date getInsertedAt() {
    return insertedAt;
  }

  public void setInsertedAt(Date insertedAt) {
    this.insertedAt = insertedAt;
  }

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", insertable = false, updatable = false)
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
