package ru.cg.cda.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.admin.dto.RoleDTO;
import ru.cg.cda.admin.service.AccessToGroupService;
import ru.cg.cda.admin.service.RoleService;


/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/role")
public class RoleController {

  @Autowired
  private RoleService roleService;
  @Autowired
  private AccessToGroupService accessToGroupService;

  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<RoleDTO> getRoles() {
    return roleService.getRoles();
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public RoleDTO saveGroup(@RequestBody RoleDTO roleDTO) {
    RoleDTO result = roleService.saveRole(roleDTO);
    roleService.saveRoleGroups(result.getId(), roleDTO.getGroupIds());
    return result;
  }

  @RequestMapping(value = "/{roleId}", method = RequestMethod.GET)
  public RoleDTO getGroup(@PathVariable Long roleId, @RequestParam boolean withGroupIds) {
    return roleService.getRole(roleId, withGroupIds);
  }

}
