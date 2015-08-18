package ru.cg.cda.axl;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ru.cg.cda.axl.beans.AxlUser;
import ru.cg.cda.axl.service.AxlService;

/**
 * @author Badamshin
 */
public class Application {

  public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/axl.xml");
    AxlService axlService = ctx.getBean("axlService", AxlService.class);
//    List<AxlDevice> devices = axlService.getDevices();
    List<AxlUser> users = axlService.getUsers();
    System.out.append("");
  }
}
