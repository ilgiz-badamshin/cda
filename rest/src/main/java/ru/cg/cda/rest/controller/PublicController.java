package ru.cg.cda.rest.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ru.cg.cda.rest.service.UserService;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/public")
public class PublicController {

  @Autowired
  private UserService userService;

  /**
   * Возвращает аватарку по идентификатору пользователя
   */
  @RequestMapping(value = "/avatar/{userId}", method = RequestMethod.GET)
  public void getAvatar(@PathVariable("userId") Long userId, HttpServletResponse response) {
    try {
      InputStream inputStream = new FileInputStream(userService.getAvatar(userId));
      IOUtils.copy(inputStream, response.getOutputStream());
      response.flushBuffer();
    }
    catch (IOException ex) {
      throw new RuntimeException("IOError writing file to output stream");
    }
  }
}
