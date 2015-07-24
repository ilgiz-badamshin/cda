package ru.cg.cda.database.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.History;

/**
 * @author Badamshin
 */
@Repository
public class HistoryDaoImpl extends BaseDaoImpl<History> implements HistoryDao {

  public Number countByCaller(Long userId, Long callerId) {
    Criteria c = create()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("callerId", callerId));
    return (Number) c.setProjection(Projections.rowCount()).uniqueResult();
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<History> listByCaller(Long userId, Long callerId, final int from, final int count) {
    return create()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("callerId", callerId))
        .setFirstResult(from)
        .setMaxResults(count)
        .list();
  }
}
