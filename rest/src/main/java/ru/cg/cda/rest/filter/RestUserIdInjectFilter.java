package ru.cg.cda.rest.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import ru.cg.cda.rest.exception.UnknownUserException;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Component
public class RestUserIdInjectFilter implements Filter {

  private static final String USER_ID = "UserId";
  private String excludePatterns;

  @Override
  public void init(FilterConfig cfg) throws ServletException {
    this.excludePatterns = cfg.getInitParameter("excludePatterns");
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    String url = ((HttpServletRequest) request).getRequestURL().toString();
    if (!matchExcludePatterns(url)) {

      HttpServletRequest httpRequest = this.getAsHttpRequest(request);
      Long userId = extractProjectIdFromRequest(httpRequest);

      if (userId != null) {
        //Вяжем к текущему потоку ID пользователя
        RestParamStorage.setCurrrentUserId(userId);
      }
      else {
        throw new UnknownUserException("Unknown user");
      }
    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
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

  private Boolean matchExcludePatterns(String url) {
    if (excludePatterns == null || excludePatterns.isEmpty()) {
      return false;
    }
    Pattern pattern = Pattern.compile(excludePatterns);
    Matcher matcher = pattern.matcher(url);
    return matcher.find();
  }

}