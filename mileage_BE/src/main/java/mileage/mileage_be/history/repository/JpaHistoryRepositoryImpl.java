package mileage.mileage_be.history.repository;

import lombok.RequiredArgsConstructor;
import mileage.mileage_be.history.domain.History;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaHistoryRepositoryImpl implements HistoryRepository{

    private final EntityManager em;

    @Override
    public History save(History history) {
        em.persist(history);
        return history;
    }

    @Override
    public List<History> findAll() {
        return em.createQuery("select h from History h", History.class).getResultList();
    }

    @Override
    public List<History> findAllByUserId(String userId) {
        return em.createQuery("select h from History h where h.userId=:userId order by h.createdAt", History.class).setParameter("userId",userId).getResultList();
    }
}
