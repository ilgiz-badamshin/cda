package ru.cg.cda.rest.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import ru.cg.cda.rest.exception.UnknownUserException;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Component
public class RestUserIdInjectFilter extends GenericFilterBean {

  public static final String USER_ID = "UserId";

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
    HttpServletRequest httpRequest = this.getAsHttpRequest(request);

    Long userId = extractProjectIdFromRequest(httpRequest);

    if (userId != null) {
      //Вяжем к текущему потоку ID пользователя
      RestParamStorage.setCurrrentUserId(userId);
    }
    else {
      throw new UnknownUserException("Unknown user");
    }

    chain.doFilter(request, response);
  }

  private HttpServletRequest getAsHttpRequest(ServletRequest request) {
    if (!(request instanceof HttpServletRequest)) {
      throw new RuntimeException("Expecting an HTTP request");
    }

    return (HttpServletRequest) request;
  }

  private Long extractProjectIdFromRequest(HttpServletRequest httpRequest) {
    /* Get projectID from header */
    String userId = httpRequest.getHeader(USER_ID);

    try {
      return userId != null ? Long.valueOf(userId) : null;
    }
    catch (NumberFormatException ex) {
      throw new RuntimeException(ex.getMessage(), ex);
    }
  }

}