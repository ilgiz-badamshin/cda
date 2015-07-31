package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.Group;

/**
 * @author Badamshin
 */
public interface GroupDao extends BaseDao<Group> {

  List<Group> visibleGroups(Long userId);

  List<Long> visibleGroupIds(Long userId);

  List<Long> invisibleIds(Long userId);
}
