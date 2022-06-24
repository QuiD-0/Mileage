package mileage.mileage_be.history.service;

import mileage.mileage_be.history.domain.History;

import java.util.List;

public interface HistoryService {
    History saveHistory(History history);

    List<History> findAllHistory();

    List<History> findAllHistoryByUserId(String userId);
}
