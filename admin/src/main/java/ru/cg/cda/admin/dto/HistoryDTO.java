package ru.cg.cda.admin.dto;

import java.util.Date;

import ru.cg.cda.database.enums.CallType;
import ru.cg.cda.database.enums.PhoneType;

/**
 * @author Badamshin
 */
public class HistoryDTO {
  private Long id;
  private Long userId;
  private Long callerId;
  private Date startOn;
  private Date endOn;
  private PhoneType phoneType;
  private CallType callType;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public Long getCallerId() {
    return callerId;
  }

  public void setCallerId(Long callerId) {
    this.callerId = callerId;
  }

  public Date getStartOn() {
    return startOn;
  }

  public void setStartOn(Date startOn) {
    this.startOn = startOn;
  }

  public Date getEndOn() {
    return endOn;
  }

  public void setEndOn(Date endOn) {
    this.endOn = endOn;
  }

  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  public CallType getCallType() {
    return callType;
  }

  public void setCallType(CallType callType) {
    this.callType = callType;
  }
}
