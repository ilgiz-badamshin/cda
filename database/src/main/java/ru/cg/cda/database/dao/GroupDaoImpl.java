package ru.cg.cda.database.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.Group;

/**
 * @author Badamshin
 */
@Repository
public class GroupDaoImpl extends BaseDaoImpl<Group> implements GroupDao {
  @Autowired
  RoleDao roleDao;

  @Override
  @SuppressWarnings("unchecked")
  public List<Group> visibleGroups(Long userId, Date updatedAt) {
    List<Long> visibleIds = roleDao.visibleGroupIds(userId);
    Criteria criteria = create();
    criteria.add(Restrictions.in("id", visibleIds));
    if (updatedAt != null) {
      criteria.add(Restrictions.ge("updatedAt", updatedAt));
    }
    return criteria.list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Long> getInvisibleIds(Long userId, Date updatedAt) {
    List<Long> visibleIds = roleDao.visibleGroupIds(userId);
    Criteria criteria = create();
    criteria.add(Restrictions.not(Restrictions.in("id", visibleIds)));
    if (updatedAt != null) {
      criteria.add(Restrictions.ge("updatedAt", updatedAt));
    }
    criteria.setProjection(Projections.property("id"));
    return criteria.list();
  }
}
