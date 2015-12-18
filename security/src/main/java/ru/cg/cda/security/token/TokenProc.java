package ru.cg.cda.security.token;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Интерфейс для работы с токеном
 *
 * @author Ildar
 */
public interface TokenProc {

  /**
   * Задать таймоут токену
   *
   * @param timeout
   */
  public void setTokenTimeout(long timeout);

  /**
   * Создать токен для пользователя
   *
   * @param userDetails - данные пользователя
   * @return токен
   */
  public String createToken(UserDetails userDetails);


  /**
   * Получить имя пользователя из токена
   *
   * @param authToken - токен
   * @return имя пользователя
   */
  public String getUserNameFromToken(String authToken);


  /**
   * Валидировать токен
   *
   * @param authToken   - токен
   * @param userDetails - - данные пользователя
   * @return true - токен валилный, иначе false
   */
  public boolean validateToken(String authToken, UserDetails userDetails);
}
