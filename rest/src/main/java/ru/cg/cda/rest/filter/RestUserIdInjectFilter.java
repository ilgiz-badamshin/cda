package ru.cg.cda.rest.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import ru.cg.cda.database.bean.Device;
import ru.cg.cda.rest.exception.UnknownUserException;
import ru.cg.cda.rest.service.DeviceService;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Component(value = "restUserIdInjectFilter")
public class RestUserIdInjectFilter extends GenericFilterBean {

  private static final String USER_ID = "UserId";
  private static final String MAC_ADDRESS = "MacAddress";
  private static String EXCLUDE_PATTERNS = "/public/*";
  @Autowired
  private DeviceService deviceService;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    String url = ((HttpServletRequest) request).getRequestURL().toString();
    if (!matchExcludePatterns(url)) {

      HttpServletRequest httpRequest = this.getAsHttpRequest(request);
      Long userId = extractUserIdFromRequest(httpRequest);

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

  private Long extractUserIdFromRequest(HttpServletRequest httpRequest) {
    /* Get userId from header */
    String macAddress = httpRequest.getHeader(MAC_ADDRESS);
    if (macAddress != null) {
      Device device = deviceService.getByName("SEP" + macAddress);
      return device != null ? device.getUserId() : null;
    }
    else {
      String userId = httpRequest.getHeader(USER_ID);
      try {
        return userId != null ? Long.valueOf(userId) : null;
      }
      catch (NumberFormatException ex) {
        throw new RuntimeException(ex.getMessage(), ex);
      }
    }
  }

  private Boolean matchExcludePatterns(String url) {
    Pattern pattern = Pattern.compile(EXCLUDE_PATTERNS);
    Matcher matcher = pattern.matcher(url);
    return matcher.find();
  }

}