package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.Sprint;
import cz.cvut.fel.dbs.SprintId;
import cz.cvut.fel.dbs.Task;
import cz.cvut.fel.dbs.TaskId;
import cz.cvut.fel.dbs.dao.SprintDaoImpl;
import cz.cvut.fel.dbs.dao.TaskDaoImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class SprintService {

    private SprintDaoImpl sprintDao;
    private TaskDaoImpl taskDao;


    public SprintService(SprintDaoImpl sprintDao, TaskDaoImpl taskDao) {this.sprintDao = sprintDao; this.taskDao = taskDao;}

    public SprintService() {}


    // 3. Uzavření sprintu — nedokončené úkoly přesune do nového sprintu
    public Sprint closeSprintAndRolloverTasks(
            Integer projectId,
            Integer sprintNumber,
            Integer newSprintNumber,
            LocalDate newSprintStart,
            LocalDate newSprintEnd
    ) {


        var sprintId = new SprintId();
        sprintId.setProjectId(projectId);
        sprintId.setSprintNumber(sprintNumber);

        Sprint sprint = sprintDao.findById(sprintId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Sprint " + sprintNumber + " in project " + projectId + " does not exist."
                ));


        List<Task> incompleteTasks = taskDao.getAllBySprintAndExcludeStatus(
                sprintNumber, projectId, "done"
        );


        Sprint newSprint = new Sprint();

        SprintId newSprintId = new SprintId();
        newSprintId.setSprintNumber(newSprintNumber);
        newSprintId.setProjectId(projectId);
        newSprint.setProject(sprint.getProject());
        newSprint.setId(newSprintId);

        newSprint.setStartDate(newSprintStart);
        newSprint.setEndDate(newSprintEnd);

        sprintDao.save(newSprint);
        sprintDao.flush();

        if (incompleteTasks.isEmpty()) {return newSprint;}

        for (Task task : incompleteTasks) {
            taskDao.reassignTaskToNewSprint(task, newSprintNumber);
        }

        return newSprint;
    }
}
