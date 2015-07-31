package ru.cg.cda.uds.beans;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Badamshin
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "user")
public class UdsUser {

  @XmlElement
  private String id;
  @XmlElement
  private String userName;
  @XmlElement
  private String firstName;
  @XmlElement
  private String lastName;
  @XmlElement
  private String selfServiceId;
  @XmlElement
  private String middleName;
  @XmlElement
  private String nickName;
  @XmlElement
  private String displayName;
  @XmlElement
  private String phoneNumber;
  @XmlElement
  private String homeNumber;
  @XmlElement
  private String mobileNumber;
  @XmlElement
  private String mobileConnect;
  @XmlElement
  private String remoteDestinationLimit;
  @XmlElement
  private String userLocale;
  @XmlElement
  private String email;
  @XmlElement
  private String directoryUri;
  @XmlElement
  private String msUri;
  @XmlElement
  private String department;
  @XmlElement
  private String manager;
  @XmlElement
  private String title;
  @XmlElement
  private String pager;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getSelfServiceId() {
    return selfServiceId;
  }

  public void setSelfServiceId(String selfServiceId) {
    this.selfServiceId = selfServiceId;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getNickName() {
    return nickName;
  }

  public void setNickName(String nickName) {
    this.nickName = nickName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getHomeNumber() {
    return homeNumber;
  }

  public void setHomeNumber(String homeNumber) {
    this.homeNumber = homeNumber;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public void setMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
  }

  public String getMobileConnect() {
    return mobileConnect;
  }

  public void setMobileConnect(String mobileConnect) {
    this.mobileConnect = mobileConnect;
  }

  public String getRemoteDestinationLimit() {
    return remoteDestinationLimit;
  }

  public void setRemoteDestinationLimit(String remoteDestinationLimit) {
    this.remoteDestinationLimit = remoteDestinationLimit;
  }

  public String getUserLocale() {
    return userLocale;
  }

  public void setUserLocale(String userLocale) {
    this.userLocale = userLocale;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getDirectoryUri() {
    return directoryUri;
  }

  public void setDirectoryUri(String directoryUri) {
    this.directoryUri = directoryUri;
  }

  public String getMsUri() {
    return msUri;
  }

  public void setMsUri(String msUri) {
    this.msUri = msUri;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getManager() {
    return manager;
  }

  public void setManager(String manager) {
    this.manager = manager;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getPager() {
    return pager;
  }

  public void setPager(String pager) {
    this.pager = pager;
  }
}
