package ru.cg.cda.rest.service;

import java.util.Date;

import ru.cg.cda.rest.dto.ChangesDto;

/**
 * @author Badamshin
 */
public interface ChangesService {
  ChangesDto getChanges(Date updatedAt);
}
