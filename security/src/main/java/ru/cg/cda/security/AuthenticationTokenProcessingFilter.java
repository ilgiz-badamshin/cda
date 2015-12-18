package ru.cg.cda.security;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import ru.cg.cda.security.token.TokenProc;


@Component
public class AuthenticationTokenProcessingFilter extends GenericFilterBean implements ApplicationContextAware {

    @Autowired
    private UserDetailsService userService;
    @Autowired
    private TokenProc tokenProc;

    private static ApplicationContext context;

    public static final String X_AUTH_TOKEN = "X-Auth-Token";
    public static final String TOKEN_PARAM = "token";

    @Override
    public void doFilter (ServletRequest request, ServletResponse response, FilterChain chain)throws IOException,
      ServletException {
    HttpServletRequest httpRequest = this.getAsHttpRequest(request);
    String authToken = this.extractAuthTokenFromRequest(httpRequest);
    String userName = tokenProc.getUserNameFromToken(authToken);

    if (userName != null) {

      try {
        UserDetails userDetails = this.userService.loadUserByUsername(userName);

        if (tokenProc.validateToken(authToken, userDetails)) {

          UsernamePasswordAuthenticationToken authentication =
              new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
          authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
          SecurityContextHolder.getContext().setAuthentication(authentication);
        }
      }
      catch (UsernameNotFoundException ex) {
        //ignore (spring return 401 if this user does not have permission to url)
      }
    }

    chain.doFilter(request, response);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    context = applicationContext;
  }

  public static ApplicationContext getContext() {
    return context;
  }


  private HttpServletRequest getAsHttpRequest(ServletRequest request) {
    if (!(request instanceof HttpServletRequest)) {
      throw new RuntimeException("Expecting an HTTP request");
    }

    return (HttpServletRequest) request;
  }


  private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
    /* Get token from header */
    String authToken = httpRequest.getHeader(X_AUTH_TOKEN);

    /* If token not found get it from request parameter */
    if (authToken == null) {
      authToken = httpRequest.getParameter(TOKEN_PARAM);
    }

    return authToken;
  }
}