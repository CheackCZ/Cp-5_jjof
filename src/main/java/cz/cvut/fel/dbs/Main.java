package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.dao.*;
import cz.cvut.fel.dbs.service.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();






        try {

            // TimeEntryService log time and activate task (set to in progress) if still set to new
//            var timeEntryDao = new TimeEntryDaoImpl();
//            var taskDao = new TaskDaoImpl();
//
//            timeEntryDao.setEntityManager(em);
//            taskDao.setEntityManager(em);
//
//            var timeEntryService = new TimeEntryService(timeEntryDao, taskDao);
//            runInTransaction(em, () -> {
//                timeEntryService.logTimeAndActivateTask(3,4,4, 1, 25, "lorem");
//
//            });

            //closeSprintAndRolloverTasks - creates a new sprint and moves over all tasks which aren't done

//
//            var sprintDao = new SprintDaoImpl();
//            var taskDao = new TaskDaoImpl();
//
//            sprintDao.setEntityManager(em);
//            taskDao.setEntityManager(em);
//
//            var sprintService = new SprintService(sprintDao, taskDao);
//            runInTransaction(em, () -> {
//                sprintService.closeSprintAndRolloverTasks(1,1,20, LocalDate.now(), LocalDate.of(2026,5,28));
//
//            });



            //
            // 1. TaskService: Create task and if no sprint create new sprint
//            ProjectDaoImpl projectDao = new ProjectDaoImpl();
//            SprintDaoImpl sprintDao = new SprintDaoImpl();
//            TaskDaoImpl taskDao = new TaskDaoImpl();
//
//            projectDao.setEntityManager(em);
//            sprintDao.setEntityManager(em);
//            taskDao.setEntityManager(em);
//
//            TaskService taskService = new TaskService(projectDao, sprintDao, taskDao);
//            runInTransaction(em, () -> {
//                taskService.createTaskAndCreateSprintIfMissing(20, 1, "Test-task", "new");
//            });

            // 2. TaskService: Create task and if no sprint create new sprint
//            UserDaoImpl userDao = new UserDaoImpl();
//            UserDetailDaoImpl userDetailDao = new UserDetailDaoImpl();
//            LastNameDaoImpl lastNameDao = new LastNameDaoImpl();
//
//            userDao.setEntityManager(em);
//            userDetailDao.setEntityManager(em);
//            lastNameDao.setEntityManager(em);
//
//            UserService userService = new UserService(userDao, userDetailDao, lastNameDao);
//
//            runInTransaction(em, () -> {
//                try {
//                    userService.createUserWithDetails(
//                            "testuser_002",
//                            "testpassword",
//                            "test002@test.com",
//                            "123456780",
//                            "Second street 421",
//                            "Prague",
//                            "19800",
//                            "Petr",
//                            "Svoboda",
//                            "Novak"
//                    );
//                } catch (Exception e) {
//                    throw new RuntimeException(e);
//                }
//            });

            // 3. Count employees delayed - CP4 vulnerable transaction
//            EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();
//            employeeDao.setEntityManager(em);
//
//            EmployeeService employeeService = new EmployeeService(employeeDao);
//            runInTransaction(em, () -> {
//                employeeService.countEmployeesDelayed();
//            });

        } finally {
            em.close();
            emf.close();
        }
    }

    private static void runInTransaction(EntityManager em, Runnable action) {
        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();
            action.run();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

}