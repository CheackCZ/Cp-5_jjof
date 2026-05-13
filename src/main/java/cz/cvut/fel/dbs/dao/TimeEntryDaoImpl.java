package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.TimeEntry;
import cz.cvut.fel.dbs.TimeEntryId;

import java.time.LocalDateTime;
import java.util.List;

public class TimeEntryDaoImpl extends GenericDaoImpl<TimeEntry, TimeEntryId> {

    public TimeEntryDaoImpl() {
        super(TimeEntry.class);
    }

    /** Celkový počet minut na task */
    public int sumMinutesByTask(Integer taskNumber, Integer sprintNumber, Integer projectId) {
        Long result = em.createQuery(
                "SELECT SUM(te.minutes) FROM TimeEntry te " +
                "WHERE te.id.taskNumber = :task " +
                "AND te.id.sprintNumber = :sprint " +
                "AND te.id.projectId = :project", Long.class)
                .setParameter("task", taskNumber)
                .setParameter("sprint", sprintNumber)
                .setParameter("project", projectId)
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }

    /** Záznamy v časovém rozsahu */
    public List<TimeEntry> findByDateRange(LocalDateTime from, LocalDateTime to) {
        return em.createQuery(
                "SELECT te FROM TimeEntry te " +
                "WHERE te.loggedAt BETWEEN :from AND :to " +
                "ORDER BY te.loggedAt DESC", TimeEntry.class)
                .setParameter("from", from)
                .setParameter("to", to)
                .getResultList();
    }

    /** Záznamy konkrétního zaměstnance přes tasky */
    public List<TimeEntry> findByEmployee(Integer employeeId) {
        return em.createQuery(
                "SELECT te FROM TimeEntry te " +
                "JOIN te.task t " +
                "WHERE t.employee.id = :employeeId " +
                "ORDER BY te.loggedAt DESC", TimeEntry.class)
                .setParameter("employeeId", employeeId)
                .getResultList();
    }

    /** Celkový počet minut zaměstnance na projektu */
    public int sumMinutesByEmployeeAndProject(Integer employeeId, Integer projectId) {
        Long result = em.createQuery(
                "SELECT SUM(te.minutes) FROM TimeEntry te " +
                "JOIN te.task t " +
                "WHERE t.employee.id = :employeeId " +
                "AND te.id.projectId = :projectId", Long.class)
                .setParameter("employeeId", employeeId)
                .setParameter("projectId", projectId)
                .getSingleResult();
        return result != null ? result.intValue() : 0;
    }
}
