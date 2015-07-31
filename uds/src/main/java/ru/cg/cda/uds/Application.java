package ru.cg.cda.uds;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import ru.cg.cda.uds.service.UdsService;

/**
 * @author Badamshin
 */
@Component // Main is a Spring-managed bean too, since it have @Autowired property
public class Application {


  public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
    ApplicationContext ctx = new ClassPathXmlApplicationContext("META-INF/spring/uds.xml");
    UdsService udsService = ctx.getBean("udsService", UdsService.class);
    System.out.println(udsService.getUsers());
    System.out.println("ssssssssssssssssssssssss");
    System.out.println(udsService.getUser("iivanov"));
    System.out.println("ssssssssssssssssssssssss");
    System.out.println(udsService.getDevices("iivanov"));
    System.out.println(udsService.getDevice("iivanov", "3fdc6c05-71b9-e69d-1763-d0a17b42acf0"));

  }
}
