package ru.cg.cda.rest.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import ru.cg.cda.rest.dto.HistoryDTO;

/**
 * @author Badamshin
 */
@RestController
@RequestMapping("/rest/history")
public class HistoryController {

  /**
   * Возвращает общее кол-во записей в истории звонков
   *
   * @return Long
   */
  @RequestMapping(value = "/count", method = RequestMethod.GET)
  public Long getHistoryCount() {
    return null;
  }

  /**
   * Возвращает общую историю звонков с указанными limit и offset
   *
   * @param limit  Long
   * @param offset Long
   * @return List<HistoryDTO>
   */
  @RequestMapping(value = "", method = RequestMethod.GET)
  public List<HistoryDTO> getHistories(Long limit, Long offset) {
    return null;
  }

  /**
   * Возвращает  кол-во записаей в истории звонков по пользователю
   *
   * @param userId Long
   * @return Long
   */
  @RequestMapping(value = "/byUser/{userId}/count", method = RequestMethod.GET)
  public Long getHistoryCountByUser(@PathVariable Long userId) {
    return null;
  }

  /**
   * Возвращает историю по пользователю с указанными limit и offset
   *
   * @param userId Long
   * @param limit  Long
   * @param offset Long
   * @return List<HistoryDTO>
   */
  @RequestMapping(value = "/byUser/{userId}", method = RequestMethod.GET)
  public List<HistoryDTO> getHistoriesByUser(@PathVariable Long userId, Long limit, Long offset) {
    return null;
  }

  /**
   * Добавляет новую запись  в историю звонков
   *
   * @param historyDTO HistoryDTO
   * @return Boolean
   */
  @RequestMapping(value = "/", method = RequestMethod.POST)
  public Boolean addHistory(@RequestBody HistoryDTO historyDTO) {
    return null;
  }
}
