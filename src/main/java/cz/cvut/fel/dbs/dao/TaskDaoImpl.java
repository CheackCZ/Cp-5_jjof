package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Task;
import cz.cvut.fel.dbs.TaskId;
import cz.cvut.fel.dbs.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class TaskDaoImpl extends GenericDaoImpl<Task, TaskId> {

    public TaskDaoImpl() {
        super(Task.class);
    }


    public List<Task> getAllBySprintAndExcludeStatus(int sprintNumber, int projectId, String status) {
        return em.createQuery(
                        "SELECT t FROM Task t WHERE t.id.sprintNumber = :sprintNumber AND t.id.projectId = :projectId AND t.status != :status", Task.class)
                .setParameter("sprintNumber", sprintNumber)
                .setParameter("projectId", projectId)
                .setParameter("status", status)
                .getResultStream()
                .toList();
    }

    public void reassignTaskToNewSprint(Task task, int newSprintNumber ) {
        em.createQuery(
                        "UPDATE Task t SET t.id.sprintNumber = :newSprint " +
                                "WHERE t.id.taskNumber = :taskNumber " +
                                "AND t.id.sprintNumber = :oldSprint " +
                                "AND t.id.projectId = :projectId"
                )
                .setParameter("newSprint", newSprintNumber)
                .setParameter("taskNumber", task.getId().getTaskNumber())
                .setParameter("oldSprint", task.getId().getSprintNumber())
                .setParameter("projectId", task.getId().getProjectId())
                .executeUpdate();
    }

}
