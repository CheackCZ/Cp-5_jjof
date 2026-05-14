package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.*;
import cz.cvut.fel.dbs.dao.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class TimeEntryService {

    @Inject
    TimeEntryDaoImpl timeEntryDao;

    @Inject
    TaskDaoImpl taskDao;

    public TimeEntryService(TimeEntryDaoImpl timeEntryDao, TaskDaoImpl taskDao) {
        this.timeEntryDao = timeEntryDao;
        this.taskDao = taskDao;
    }

    public TimeEntryService () {}

    // 2. Přidání časového záznamu k úkolu — pokud úkol ještě nemá status "in progress", aktualizuje ho
    // nutné aby prošel trigger, dát pro jistotu project assign předtím
    @Transactional
    public TimeEntry logTimeAndActivateTask(
            Integer projectId,
            Integer sprintNumber,
            Integer taskNumber,
            Integer entryNumber,
            Integer minutes,
            String note
    ) {


        var taskId = new TaskId();
        taskId.setTaskNumber(taskNumber);
        taskId.setProjectId(projectId);
        taskId.setSprintNumber(sprintNumber);

        Task task = taskDao.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Task " + taskNumber + " in sprint " + sprintNumber
                                + " of project " + projectId + " does not exist."
                ));

        if ("new".equals(task.getStatus())) {
            task.setStatus("in progress");
            taskDao.update(task);
        }

        TimeEntry entry = new TimeEntry();

        TimeEntryId entryId = new TimeEntryId();
        entryId.setEntryNumber(entryNumber);
        entryId.setTaskNumber(taskNumber);
        entryId.setSprintNumber(sprintNumber);
        entryId.setProjectId(projectId);

        entry.setId(entryId);
        entry.setTask(task);
        entry.setMinutes(minutes);
        entry.setLoggedAt(Instant.now());
        entry.setNote(note);

        timeEntryDao.save(entry);

        return entry;
    }
}
