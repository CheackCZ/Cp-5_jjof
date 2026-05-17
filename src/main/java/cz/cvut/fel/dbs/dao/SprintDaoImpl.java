package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Sprint;
import cz.cvut.fel.dbs.SprintId;

import java.util.Optional;

public class SprintDaoImpl extends GenericDaoImpl<Sprint, SprintId> {

    public SprintDaoImpl() {
        super(Sprint.class);
    }

    public Optional<Sprint> findFirstByProject(Integer projectId) {
        return em.createQuery(
                        "SELECT s FROM Sprint s " +
                                "WHERE s.id.projectId = :projectId " +
                                "ORDER BY s.id.sprintNumber ASC",
                        Sprint.class
                )
                .setParameter("projectId", projectId)
                .setMaxResults(1)
                .getResultStream()
                .findFirst();
    }

    public Integer getNextSprintNumber(Integer projectId) {
        Integer maxSprintNumber = em.createQuery(
                        "SELECT MAX(s.id.sprintNumber) FROM Sprint s " +
                                "WHERE s.id.projectId = :projectId",
                        Integer.class
                )
                .setParameter("projectId", projectId)
                .getSingleResult();

        if (maxSprintNumber == null) {
            return 1;
        }

        return maxSprintNumber + 1;
    }
}
