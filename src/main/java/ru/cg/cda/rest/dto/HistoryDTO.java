package ru.cg.cda.rest.dto;

import java.sql.Time;
import java.util.Date;

import ru.cg.cda.rest.enums.CallType;
import ru.cg.cda.rest.enums.PhoneType;

/**
 * @author Badamshin
 */
public class HistoryDTO {
  public Long id;
  public Date date;
  public Time duration;
  public CallType callType;
  public PhoneType phoneType;

}
