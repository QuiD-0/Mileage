package mileage.mileage_be.history.repository;

import mileage.mileage_be.history.domain.History;

import java.util.List;

public interface HistoryRepository {
    History save(History history);

    List<History> findAll();

    List<History> findAllByUserId(String userId);
}
