package ru.cg.cda.database.dao;

import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import ru.cg.cda.database.bean.Favorite;

/**
 * @author Badamshin
 */
@Repository
public class FavoriteDaoImpl extends BaseDaoImpl<Favorite> implements FavoriteDao {

  public Boolean isFavorite(Long userId, Long favoriteId) {
    Number count = (Number) create()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("favoriteId", favoriteId))
        .setProjection(Projections.rowCount()).uniqueResult();
    return count.longValue() > 0;
  }

  public Favorite get(Long userId, Long favoriteId) {
    return (Favorite) create()
        .add(Restrictions.eq("userId", userId))
        .add(Restrictions.eq("favoriteId", favoriteId))
        .uniqueResult();
  }
}
