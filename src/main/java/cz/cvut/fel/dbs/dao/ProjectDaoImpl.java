package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Employee;
import cz.cvut.fel.dbs.Project;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> {

    public ProjectDaoImpl() {
        super(Project.class);
    }

    /** Projekty podle stavu */
    public List<Project> findByStatus(String status) {
        return em.createQuery(
                "SELECT p FROM Project p WHERE p.status = :status", Project.class)
                .setParameter("status", status)
                .getResultList();
    }

    /** Projekty podle priority a stavu */
    public List<Project> findByPriorityAndStatus(String priority, String status) {
        return em.createQuery(
                "SELECT p FROM Project p " +
                "WHERE p.priority = :priority AND p.status = :status " +
                "ORDER BY p.startDate ASC", Project.class)
                .setParameter("priority", priority)
                .setParameter("status", status)
                .getResultList();
    }

    /** Projekty klienta */
    public List<Project> findByClient(Integer clientId) {
        return em.createQuery(
                "SELECT p FROM Project p " +
                "WHERE p.client.id = :clientId", Project.class)
                .setParameter("clientId", clientId)
                .getResultList();
    }

    /** Projekt s načtenými sprinty (JOIN FETCH) */
//    public Optional<Project> findByIdWithSprints(Integer projectId) {
//        return em.createQuery(
//                "SELECT p FROM Project p " +
//                "LEFT JOIN FETCH p.sprints " +
//                "WHERE p.projectId = :id", Project.class)
//                .setParameter("id", projectId)
//                .getResultStream()
//                .findFirst();
//    }

    /** Projekty aktivní v zadaném datumovém rozsahu */
    public List<Project> findActiveInRange(LocalDate from, LocalDate to) {
        return em.createQuery(
                "SELECT p FROM Project p " +
                "WHERE p.startDate <= :to " +
                "AND (p.endDate IS NULL OR p.endDate >= :from)", Project.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }
}
