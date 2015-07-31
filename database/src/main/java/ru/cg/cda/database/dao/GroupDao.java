package ru.cg.cda.database.dao;

import java.util.Date;
import java.util.List;

import ru.cg.cda.database.bean.Group;

/**
 * @author Badamshin
 */
public interface GroupDao extends BaseDao<Group> {

  List<Group> visibleGroups(Long userId, Date updatedAt);

  List<Long> getInvisibleIds(Long userId, Date updatedAt);
}
