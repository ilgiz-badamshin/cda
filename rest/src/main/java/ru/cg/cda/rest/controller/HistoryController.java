package ru.cg.cda.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ru.cg.cda.rest.dto.HistoryDTO;
import ru.cg.cda.rest.service.HistoryService;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/history")
public class HistoryController {

  @Autowired
  HistoryService historyService;

  /**
   * Возвращает общее кол-во записей в истории звонков
   *
   * @return Long
   */
  @RequestMapping(value = "/count", method = RequestMethod.GET)
  public Long getHistoryCount() {
    return historyService.getHistoryCount();
  }

  /**
   * Возвращает общую историю звонков с указанными limit и offset
   *
   * @param from  Integer
   * @param count Integer
   * @return List<HistoryDTO>
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<HistoryDTO> getHistories(Integer from, Integer count) {
    if (from == null) {
      from = 0;
    }
    if (count == null) {
      count = 100;
    }
    return historyService.getHistories(from, count);
  }

  /**
   * Возвращает  кол-во записаей в истории звонков по пользователю
   *
   * @param callerId Long
   * @return Long
   */
  @RequestMapping(value = "/byUser/{callerId}/count", method = RequestMethod.GET)
  public Long getHistoryCountByUser(@PathVariable Long callerId) {
    return historyService.getHistoryCountByCaller(callerId);
  }

  /**
   * Возвращает историю по пользователю с указанными limit и offset
   *
   * @param callerId Long
   * @param from     Integer
   * @param count    Integer
   * @return List<HistoryDTO>
   */
  @RequestMapping(value = "/byUser/{callerId}", method = RequestMethod.GET)
  public List<HistoryDTO> getHistoriesByUser(@PathVariable Long callerId, Integer from, Integer count) {
    if (from == null) {
      from = 0;
    }
    if (count == null) {
      count = 100;
    }
    return historyService.getHistoriesByCaller(callerId, from, count);
  }

  /**
   * Добавляет новую запись  в историю звонков
   *
   * @param historyDTO HistoryDTO
   */
  @RequestMapping(value = "", method = RequestMethod.POST)
  public void addHistory(@RequestBody HistoryDTO historyDTO) {
    historyService.addHistory(historyDTO);
  }
}
