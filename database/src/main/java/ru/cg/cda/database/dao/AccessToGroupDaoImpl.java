package ru.cg.cda.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.AccessToGroup;

/**
 * @author Badamshin
 */
@Repository
public class AccessToGroupDaoImpl extends BaseDaoImpl<AccessToGroup> implements AccessToGroupDao {

  @Override
  @SuppressWarnings("unchecked")
  public List<Long> getGroupIds(Long roleId) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("roleId", roleId));
    criteria.setProjection(Projections.property("groupId"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<Long>) criteria.list();
  }

  @Override
  public AccessToGroup byRoleAndGroup(Long roleId, Long groupId) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("roleId", roleId));
    criteria.add(Restrictions.eq("groupId", groupId));
    return (AccessToGroup) criteria.uniqueResult();
  }


  @Override
  @SuppressWarnings("unchecked")
  public void deleteAll(Long roleId) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("roleId", roleId));
    List<AccessToGroup> accessToGroups = criteria.list();
    for (AccessToGroup accessToGroup : accessToGroups) {
      delete(accessToGroup);
    }
  }

  @Override
  @SuppressWarnings("unchecked")
  public void deleteAllExcept(Long roleId, List<Long> groupIds) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("roleId", roleId));
    criteria.add(Restrictions.not(Restrictions.in("groupId", groupIds)));
    List<AccessToGroup> accessToGroups = criteria.list();
    for (AccessToGroup accessToGroup : accessToGroups) {
      delete(accessToGroup);
    }
  }
}
