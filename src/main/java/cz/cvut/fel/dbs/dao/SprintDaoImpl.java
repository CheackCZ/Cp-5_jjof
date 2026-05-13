package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Sprint;
import cz.cvut.fel.dbs.SprintId;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SprintDaoImpl extends GenericDaoImpl<Sprint, SprintId> {

    public SprintDaoImpl() {
        super(Sprint.class);
    }

    /** Sprinty projektu */
    public List<Sprint> findByProject(Integer projectId) {
        return em.createQuery(
                "SELECT s FROM Sprint s WHERE s.id.projectId = :projectId " +
                "ORDER BY s.id.sprintNumber ASC", Sprint.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    /** Aktuálně běžící sprint pro daný projekt */
    public Optional<Sprint> findActiveSprint(Integer projectId, LocalDate today) {
        return em.createQuery(
                "SELECT s FROM Sprint s " +
                "WHERE s.id.projectId = :projectId " +
                "AND s.startDate <= :today AND s.endDate >= :today", Sprint.class)
                .setParameter("projectId", projectId)
                .setParameter("today", today)
                .getResultStream()
                .findFirst();
    }

    /** Sprint s načtenými tasky (JOIN FETCH) */
    public Optional<Sprint> findByIdWithTasks(Integer sprintNumber, Integer projectId) {
        return em.createQuery(
                "SELECT s FROM Sprint s " +
                "LEFT JOIN FETCH s.tasks " +
                "WHERE s.id.sprintNumber = :sprint AND s.id.projectId = :project", Sprint.class)
                .setParameter("sprint", sprintNumber)
                .setParameter("project", projectId)
                .getResultStream()
                .findFirst();
    }
}
