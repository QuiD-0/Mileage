package mileage.mileage_be.history.service;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.history.domain.History;
import mileage.mileage_be.history.repository.HistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public History saveHistory(History history) {
        return historyRepository.save(history);
    }

    @Override
    public List<History> findAllHistory() {
        return historyRepository.findAll();
    }

    @Override
    public List<History> findAllHistoryByUserId(String userId) {
        return historyRepository.findAllByUserId(userId);
    }
}

