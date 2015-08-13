package ru.cg.cda.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.admin.dto.RoleDTO;
import ru.cg.cda.admin.service.RoleService;


/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private RoleService roleService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<RoleDTO> getRoles() {
    return roleService.getRoles();
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public RoleDTO saveGroup(@RequestBody RoleDTO roleDTO) {
    return roleService.saveRole(roleDTO);
  }

  @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
  public RoleDTO getGroup(@PathVariable Long roleId) {
    return roleService.getRole(roleId);
  }

}
