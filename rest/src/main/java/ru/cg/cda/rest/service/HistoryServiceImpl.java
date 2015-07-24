package ru.cg.cda.rest.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.cg.cda.database.bean.History;
import ru.cg.cda.database.dao.HistoryDao;
import ru.cg.cda.rest.dto.HistoryDTO;
import ru.cg.cda.rest.storage.RestParamStorage;

/**
 * @author Badamshin
 */
@Transactional
@Service
public class HistoryServiceImpl implements HistoryService {

  @Autowired
  private HistoryDao historyDao;

  public Long getHistoryCount() {
    return historyDao.count("userId", RestParamStorage.getCurrrentUserId()).longValue();
  }

  public List<HistoryDTO> getHistories( int from, int count) {
    return convertHistories(historyDao.listByField("userId", RestParamStorage.getCurrrentUserId(), from, count));
  }

  public Long getHistoryCountByCaller(Long toUserId) {
    return historyDao.countByCaller(RestParamStorage.getCurrrentUserId(), toUserId).longValue();
  }

  public List<HistoryDTO> getHistoriesByCaller(Long toUserId, int from, int count) {
    return convertHistories(historyDao.listByCaller(RestParamStorage.getCurrrentUserId(), toUserId, from, count));
  }

  public void addHistory(HistoryDTO historyDTO) {
    History history = convertHistoryDto(historyDTO);
    historyDao.saveOrUpdate(history);
  }

  public HistoryDTO convertHistory(History history) {
    HistoryDTO historyDTO = new HistoryDTO();
    historyDTO.setId(history.getId());
    historyDTO.setUserId(history.getUserId());
    historyDTO.setStartOn(history.getStartOn());
    historyDTO.setCallerId(history.getCallerId());
    historyDTO.setEndOn(history.getEndOn());
    historyDTO.setCallType(history.getCallType());
    historyDTO.setPhoneType(history.getPhoneType());
    return historyDTO;
  }

  public List<HistoryDTO> convertHistories(List<History> histories) {
    List<HistoryDTO> result = new ArrayList<>();
    for (History history : histories) {
      result.add(convertHistory(history));
    }
    return result;
  }

  public History convertHistoryDto(HistoryDTO historyDTO) {
    History history = new History();
    history.setId(historyDTO.getId());
    history.setUserId(historyDTO.getUserId());
    history.setStartOn(historyDTO.getStartOn());
    history.setCallerId(historyDTO.getCallerId());
    history.setEndOn(historyDTO.getEndOn());
    history.setCallType(historyDTO.getCallType());
    history.setPhoneType(historyDTO.getPhoneType());
    return history;
  }
}
