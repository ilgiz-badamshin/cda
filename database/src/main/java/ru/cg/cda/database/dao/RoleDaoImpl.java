package ru.cg.cda.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.Role;

/**
 * @author Badamshin
 */
@Repository
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {
  @Override
  @SuppressWarnings("unchecked")
  public List<Long> visibleGroupIds(Long userId) {
    Criteria criteria = create();
    criteria.createCriteria("linkUserRoles", "linkUserRoles");
    criteria.createCriteria("accessToGroups", "accessToGroups");
    criteria.add(Restrictions.eq("linkUserRoles.userId", userId));
    criteria.setProjection(Projections.property("accessToGroups.groupId"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<Long>) criteria.list();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<Long> visibleUserIds(Long userId) {
    Criteria criteria = create();
    criteria.createCriteria("linkUserRoles", "linkUserRoles");
    criteria.createCriteria("groups", "groups");
    criteria.createCriteria("groups.users", "users");
    criteria.add(Restrictions.eq("linkUserRoles.userId", userId));
    criteria.setProjection(Projections.property("users.id"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<Long>) criteria.list();
  }
}
