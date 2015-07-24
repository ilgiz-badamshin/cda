package ru.cg.cda.rest.service;

import java.util.List;

import ru.cg.cda.database.bean.History;
import ru.cg.cda.rest.dto.HistoryDTO;

/**
 * @author Badamshin
 */
public interface HistoryService {

  Long getHistoryCount();

  List<HistoryDTO> getHistories(int from, int count);

  Long getHistoryCountByCaller(Long callerId);

  List<HistoryDTO> getHistoriesByCaller(Long callerId, int from, int count);

  void addHistory(HistoryDTO historyDTO);

  HistoryDTO convertHistory(History history);

  List<HistoryDTO> convertHistories(List<History> histories);
}
