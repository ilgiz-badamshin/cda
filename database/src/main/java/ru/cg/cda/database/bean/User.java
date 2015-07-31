package ru.cg.cda.database.bean;

import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "`user`")
public class User {
  private Long id;
  private String udsId;
  private Long groupId;
  private String userName;
  private String userUri;
  private String lastName;
  private String firstName;
  private String middleName;
  private String vksNumber;
  private String mobilePhone;
  private String workPhone;
  private String orgName;
  private String positionName;
  private Date insertedAt;
  private Date updatedAt;

  private List<User> favorites;
  private Group group;

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

  @Column(name = "group_id")
  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  @Column(name = "username")
  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  @Column(name = "user_uri")
  public String getUserUri() {
    return userUri;
  }

  public void setUserUri(String userUri) {
    this.userUri = userUri;
  }

  @Column(name = "last_name")
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "middle_name")
  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  @Column(name = "vks_number")
  public String getVksNumber() {
    return vksNumber;
  }

  public void setVksNumber(String vksNumber) {
    this.vksNumber = vksNumber;
  }

  @Column(name = "mobile_phone")
  public String getMobilePhone() {
    return mobilePhone;
  }

  public void setMobilePhone(String mobilePhone) {
    this.mobilePhone = mobilePhone;
  }

  @Column(name = "work_phone")
  public String getWorkPhone() {
    return workPhone;
  }

  public void setWorkPhone(String workPhone) {
    this.workPhone = workPhone;
  }

  @Column(name = "org_name")
  public String getOrgName() {
    return orgName;
  }

  public void setOrgName(String orgName) {
    this.orgName = orgName;
  }

  @Column(name = "position_name")
  public String getPositionName() {
    return positionName;
  }

  public void setPositionName(String positionName) {
    this.positionName = positionName;
  }

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "favorite", joinColumns = {
      @JoinColumn(name = "user_id")
  }, inverseJoinColumns = {@JoinColumn(name = "favorite_id")})
  public List<User> getFavorites() {
    return favorites;
  }

  public void setFavorites(List<User> favorites) {
    this.favorites = favorites;
  }

  @ManyToOne
  @JoinColumn(name = "group_id", insertable = false, updatable = false)
  public Group getGroup() {
    return group;
  }

  public void setGroup(Group group) {
    this.group = group;
  }

  @Column(name = "inserted_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getInsertedAt() {
    return insertedAt;
  }

  public void setInsertedAt(Date insertedAt) {
    this.insertedAt = insertedAt;
  }

  @Column(name = "updated_at", columnDefinition = "DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }
}
