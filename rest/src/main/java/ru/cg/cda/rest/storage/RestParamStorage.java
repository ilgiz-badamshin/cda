package ru.cg.cda.rest.storage;

import java.util.HashMap;

/**
 * @author Badamshin
 */
public class RestParamStorage {
  private static ThreadLocal<Long> currentUserId = new ThreadLocal<>();

  private static ThreadLocal<HashMap<String, Object>> threadLocal = new ThreadLocal<>();

  public RestParamStorage() {
    threadLocal.set(new HashMap<>());
  }

  public static HashMap<String, Object> getParams() {
    return threadLocal.get();
  }

  public static void setParams(HashMap<String, Object> params) {
    threadLocal.set(params);
  }

  public static void setCurrrentUserId(Long userId) {
    currentUserId.set(userId);
  }

  public static Long getCurrrentUserId() {
    return currentUserId.get();
  }
}
