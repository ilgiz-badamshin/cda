package ru.cg.cda.admin.service;

import java.util.List;

import ru.cg.cda.admin.dto.HistoryDTO;
import ru.cg.cda.database.bean.History;

/**
 * @author Badamshin
 */
public interface HistoryService {

  Long getHistoryCount(Long userId);

  List<HistoryDTO> getHistories(Long userId, int from, int count);

  Long getHistoryCountByCaller(Long userId, Long callerId);

  List<HistoryDTO> getHistoriesByCaller(Long userId, Long callerId, int from, int count);

  void addHistory(HistoryDTO historyDTO);

  HistoryDTO convertHistory(History history);

  List<HistoryDTO> convertHistories(List<History> histories);
}
