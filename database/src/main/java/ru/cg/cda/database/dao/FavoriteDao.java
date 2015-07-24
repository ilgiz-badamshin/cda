package ru.cg.cda.database.dao;

import ru.cg.cda.database.bean.Favorite;

/**
 * @author Badamshin
 */
public interface FavoriteDao extends BaseDao<Favorite> {
  Boolean isFavorite(Long userId, Long favorited);
  Favorite get(Long userId, Long favoriteId);
}
