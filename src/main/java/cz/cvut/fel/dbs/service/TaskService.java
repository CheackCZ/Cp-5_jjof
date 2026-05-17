package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.Project;
import cz.cvut.fel.dbs.Sprint;
import cz.cvut.fel.dbs.SprintId;
import cz.cvut.fel.dbs.Task;
import cz.cvut.fel.dbs.TaskId;
import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import cz.cvut.fel.dbs.dao.ProjectDaoImpl;
import cz.cvut.fel.dbs.dao.SprintDaoImpl;
import cz.cvut.fel.dbs.dao.TaskDaoImpl;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;

public class TaskService {

    private TaskDaoImpl taskDao;
    private SprintDaoImpl sprintDao;
    private ProjectDaoImpl projectDao;

    public TaskService(ProjectDaoImpl projectDao, SprintDaoImpl sprintDao, TaskDaoImpl taskDao) {
        this.projectDao = projectDao;
        this.sprintDao = sprintDao;
        this.taskDao = taskDao;
    }

    public TaskService() {}

    public Task createTaskAndCreateSprintIfMissing(
            Integer projectId,
            Integer taskNumber,
            String title,
            String status
    ) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project with id " + projectId + " does not exist."
                ));

        Sprint sprint = sprintDao.findFirstByProject(projectId)
                .orElseGet(() -> createDefaultSprint(project));

        Task task = new Task();

        TaskId taskId = new TaskId();
        taskId.setProjectId(projectId);
        taskId.setSprintNumber(sprint.getId().getSprintNumber());
        taskId.setTaskNumber(taskNumber);

        task.setId(taskId);
        task.setSprint(sprint);
        task.setTitle(title);
        task.setStatus(status);

        taskDao.save(task);

        return task;
    }

    private Sprint createDefaultSprint(Project project) {
        Integer projectId = project.getProjectId();

        Integer sprintNumber = sprintDao.getNextSprintNumber(projectId);

        SprintId sprintId = new SprintId();
        sprintId.setProjectId(projectId);
        sprintId.setSprintNumber(sprintNumber);

        Sprint sprint = new Sprint();
        sprint.setId(sprintId);
        sprint.setProject(project);
        sprint.setStartDate(LocalDate.now());
        sprint.setEndDate(LocalDate.now().plusDays(14));

        sprintDao.save(sprint);

        return sprint;
    }
}