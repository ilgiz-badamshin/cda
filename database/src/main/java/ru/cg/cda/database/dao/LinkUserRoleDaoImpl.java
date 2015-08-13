package ru.cg.cda.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.LinkUserRole;

/**
 * @author Badamshin
 */
@Repository
public class LinkUserRoleDaoImpl extends BaseDaoImpl<LinkUserRole> implements LinkUserRoleDao {

  @Override
  @SuppressWarnings("unchecked")
  public List<Long> getRoleIds(Long userId) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("userId", userId));
    criteria.setProjection(Projections.property("roleId"));
    criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
    return (List<Long>) criteria.list();
  }

  @Override
  public LinkUserRole byUserAndRole(Long userId, Long roleId) {
    Criteria criteria = create();
    criteria.add(Restrictions.eq("userId", userId));
    criteria.add(Restrictions.eq("roleId", roleId));
    return (LinkUserRole) criteria.uniqueResult();
  }
}
