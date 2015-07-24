package ru.cg.cda.database.dao;

import java.util.List;

import ru.cg.cda.database.bean.History;

/**
 * @author Badamshin
 */
public interface HistoryDao extends BaseDao<History> {
  Number countByCaller(Long callerId, Long toUserId);

  List<History> listByCaller(Long userId, Long callerId, final int from, final int count);
}
