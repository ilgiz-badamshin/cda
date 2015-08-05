package ru.cg.cda.axl.service;

import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.ws.BindingProvider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ru.cg.cda.axl.SSLUtil;
import ru.cg.cda.axl.beans.AxlDevice;
import ru.cg.cda.axl.beans.AxlUser;
import ru.cg.cda.axl.exception.AxlException;
import ru.cg.cda.axl.generated.*;

/**
 * @author Badamshin
 */
@Component
public class AxlServiceImpl implements AxlService {
  @Value("${axl.username}")
  private String USERNAME;
  @Value("${axl.password}")
  private String PASSWORD;
  @Value("${axl.endpoint}")
  private String ENDPOINT;

  @Override
  public List<AxlDevice> getDevices() {
    ListPhoneReq listPhoneReq = new ListPhoneReq();
    ListPhoneReq.SearchCriteria searchCriteria = new ListPhoneReq.SearchCriteria();
    searchCriteria.setName("SEP%");
    listPhoneReq.setSearchCriteria(searchCriteria);

    try {
      //@TODO подумать над решением этой проблемы
      SSLUtil.turnOffSslChecking();

      URL url = getClass().getClassLoader().getResource("ru/cg/cda/axl/AXLAPI.wsdl");
      AXLAPIService service = new AXLAPIService(url);
      AXLPort axlPort = service.getAXLPort();
      BindingProvider prov = (BindingProvider) axlPort;
      prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USERNAME);
      prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
      prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);

      ListPhoneRes listPhoneRes = axlPort.listPhone(listPhoneReq);
      List<AxlDevice> axlDevices = new ArrayList<>();
      if (listPhoneRes.getReturn() != null) {
        List<LPhone> phones = listPhoneRes.getReturn().getPhone();
        for (LPhone lPhone : phones) {
          AxlDevice axlDevice = new AxlDevice();
          axlDevice.setId(lPhone.getUuid().replace("{", "").replace("}", ""));
          if (lPhone.getOwnerUserName().getUuid() != null) {
            axlDevice.setUserId(lPhone.getOwnerUserName().getUuid().replace("{", "").replace("}", ""));
          }
          axlDevice.setName(lPhone.getName());
          axlDevice.setDescription(lPhone.getDescription());
          axlDevice.setModel(lPhone.getModel());
          axlDevices.add(axlDevice);
        }
      }
      return axlDevices;
    }
    catch (AXLError_Exception | NoSuchAlgorithmException | KeyManagementException e) {
      throw new AxlException(e);
    }
  }

  @Override
  public List<AxlUser> getUsers() {
    ListUserReq listUserReq = new ListUserReq();
    ListUserReq.SearchCriteria searchCriteria = new ListUserReq.SearchCriteria();
    searchCriteria.setFirstName("%%");
    listUserReq.setSearchCriteria(searchCriteria);
    LUser returnTags = new LUser();
    returnTags.setUuid("");
    returnTags.setUserid("");
    returnTags.setFirstName("");
    returnTags.setMiddleName("");
    returnTags.setLastName("");
    returnTags.setTelephoneNumber("");
    returnTags.setMobileNumber("");
    returnTags.setHomeNumber("");
    listUserReq.setReturnedTags(returnTags);

    try {
      //@TODO подумать над решением этой проблемы
      SSLUtil.turnOffSslChecking();

      URL url = getClass().getClassLoader().getResource("ru/cg/cda/axl/AXLAPI.wsdl");
      AXLAPIService service = new AXLAPIService(url);
      AXLPort axlPort = service.getAXLPort();
      BindingProvider prov = (BindingProvider) axlPort;
      prov.getRequestContext().put(BindingProvider.USERNAME_PROPERTY, USERNAME);
      prov.getRequestContext().put(BindingProvider.PASSWORD_PROPERTY, PASSWORD);
      prov.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, ENDPOINT);

      ListUserRes listUserRes = axlPort.listUser(listUserReq);
      List<AxlUser> axlUsers = new ArrayList<>();
      if (listUserRes.getReturn() != null) {
        List<LUser> users = listUserRes.getReturn().getUser();
        for (LUser user : users) {
          AxlUser axlUser = new AxlUser();
          axlUser.setId(user.getUuid().replace("{", "").replace("}", ""));
          axlUser.setUserName(user.getUserid());
          axlUser.setFirstName(user.getFirstName());
          axlUser.setLastName(user.getLastName());
          axlUser.setMiddleName(user.getMiddleName());
          axlUser.setHomeNumber(user.getHomeNumber());
          axlUser.setMobileNumber(user.getMobileNumber());
          axlUser.setTelephoneNumber(user.getTelephoneNumber());
          axlUsers.add(axlUser);
        }
      }
      return axlUsers;
    }
    catch (AXLError_Exception | NoSuchAlgorithmException | KeyManagementException e) {
      throw new AxlException(e);
    }
  }
}
