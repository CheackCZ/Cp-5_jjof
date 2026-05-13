package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Task;
import cz.cvut.fel.dbs.TaskId;

import java.util.List;

public class TaskDaoImpl extends GenericDaoImpl<Task, TaskId> {

    public TaskDaoImpl() {
        super(Task.class);
    }

    /** Tasky sprintu */
    public List<Task> findBySprint(Integer sprintNumber, Integer projectId) {
        return em.createQuery(
                "SELECT t FROM Task t " +
                "WHERE t.id.sprintNumber = :sprint " +
                "AND t.id.projectId = :project", Task.class)
                .setParameter("sprint", sprintNumber)
                .setParameter("project", projectId)
                .getResultList();
    }

    /** Tasky přiřazené zaměstnanci */
    public List<Task> findByEmployee(Integer employeeId) {
        return em.createQuery(
                "SELECT t FROM Task t " +
                "WHERE t.employee.id = :employeeId", Task.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    /** Tasky podle stavu v projektu */
    public List<Task> findByStatusInProject(String status, Integer projectId) {
        return em.createQuery(
                "SELECT t FROM Task t " +
                "WHERE t.status = :status " +
                "AND t.id.projectId = :projectId", Task.class)
                .setParameter("status", status)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    /** Nepřiřazené tasky ve sprintu */
    public List<Task> findUnassignedInSprint(Integer sprintNumber, Integer projectId) {
        return em.createQuery(
                "SELECT t FROM Task t " +
                "WHERE t.employee IS NULL " +
                "AND t.id.sprintNumber = :sprint " +
                "AND t.id.projectId = :project", Task.class)
                .setParameter("sprint", sprintNumber)
                .setParameter("project", projectId)
                .getResultList();
    }
}
