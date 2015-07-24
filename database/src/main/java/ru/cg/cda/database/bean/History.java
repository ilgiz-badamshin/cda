package ru.cg.cda.database.bean;

import java.util.Date;
import javax.persistence.*;

import ru.cg.cda.database.enums.CallType;
import ru.cg.cda.database.enums.PhoneType;

/**
 * @author Badamshin
 */
@Entity
@Table(name = "history")
public class History {
  private Long id;
  private Long userId;
  private Long callerId;
  private Date startOn;
  private Date endOn;
  private PhoneType phoneType;
  private CallType callType;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  @Column(name = "user_id")
  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }

  @Column(name = "caller_id")
  public Long getCallerId() {
    return callerId;
  }

  public void setCallerId(Long callerId) {
    this.callerId = callerId;
  }

  @Column(name = "start_on", columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getStartOn() {
    return startOn;
  }

  public void setStartOn(Date startOn) {
    this.startOn = startOn;
  }

  @Column(name = "end_on", columnDefinition="DATETIME")
  @Temporal(TemporalType.TIMESTAMP)
  public Date getEndOn() {
    return endOn;
  }

  public void setEndOn(Date endOn) {
    this.endOn = endOn;
  }

  @Column(name = "phone_type")
  public PhoneType getPhoneType() {
    return phoneType;
  }

  public void setPhoneType(PhoneType phoneType) {
    this.phoneType = phoneType;
  }

  @Column(name = "call_type")
  public CallType getCallType() {
    return callType;
  }

  public void setCallType(CallType callType) {
    this.callType = callType;
  }
}
