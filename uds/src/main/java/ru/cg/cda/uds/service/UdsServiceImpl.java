package ru.cg.cda.uds.service;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.net.ssl.HostnameVerifier;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import ru.cg.cda.uds.RestTemplateException;
import ru.cg.cda.uds.beans.UdsDevice;
import ru.cg.cda.uds.beans.UdsDevices;
import ru.cg.cda.uds.beans.UdsUser;
import ru.cg.cda.uds.beans.UdsUserList;
import ru.cg.cda.uds.ssl.MyHostnameVerifier;
import ru.cg.cda.uds.ssl.MySimpleClientHttpRequestFactory;
import ru.cg.cda.uds.ssl.SSLUtil;

/**
 * @author Badamshin
 */
@Component
public class UdsServiceImpl implements UdsService {
  @Value("${uds.url.username}")
  private String USERNAME;
  @Value("${uds.url.password}")
  private String PASSWORD;
  @Value("${uds.url.users}")
  private String URL_USERS;
  @Value("${uds.url.user}")
  private String URL_USER;
  @Value("${uds.url.devices}")
  private String URL_DEVICES;
  @Value("${uds.url.device}")
  private String URL_DEVICE;

  private RestTemplate getRestTemplate() {
    try {
      SSLUtil.turnOffSslChecking();
    }
    catch (NoSuchAlgorithmException | KeyManagementException e) {
      throw new RestTemplateException(e);
    }
    HostnameVerifier verifier = new MyHostnameVerifier();
    MySimpleClientHttpRequestFactory factory = new MySimpleClientHttpRequestFactory(verifier);

    RestTemplate restTemplate = new RestTemplate();
    restTemplate.setRequestFactory(factory);

    return restTemplate;
  }

  private HttpEntity<String> getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    String auth = USERNAME + ":" + PASSWORD;
    byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
    String authHeader = "Basic " + new String(encodedAuth);
    headers.set("Authorization", authHeader);

    return new HttpEntity<>("parameters", headers);
  }

  @Override
  public List<UdsUser> getUsers() {
    RestTemplate restTemplate = getRestTemplate();
    ResponseEntity<UdsUserList> response = restTemplate.exchange(URL_USERS, HttpMethod.GET, getHeaders(), UdsUserList.class);
    UdsUserList user = response.getBody();
    return user.getUsers();
  }


  @Override
  public UdsUser getUser(String userId) {
    RestTemplate restTemplate = getRestTemplate();
    ResponseEntity<UdsUser> response = restTemplate.exchange(URL_USER, HttpMethod.GET, getHeaders(), UdsUser.class, userId);
    return response.getBody();
  }

  @Override
  public List<UdsDevice> getDevices(String userId) {
    RestTemplate restTemplate = getRestTemplate();
    ResponseEntity<UdsDevices> response = restTemplate.exchange(URL_DEVICES, HttpMethod.GET, getHeaders(), UdsDevices.class, userId);
    UdsDevices udsDevices = response.getBody();
    return udsDevices.getDevices();

  }

  @Override
  public UdsDevice getDevice(String userId, String deviceId) {
    RestTemplate restTemplate = getRestTemplate();
    ResponseEntity<UdsDevice> response = restTemplate.exchange(URL_DEVICE, HttpMethod.GET, getHeaders(), UdsDevice.class, userId, deviceId);
    return response.getBody();
  }
}
