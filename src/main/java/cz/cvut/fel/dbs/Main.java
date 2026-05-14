package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.dao.*;
import cz.cvut.fel.dbs.service.EmployeeService;
import cz.cvut.fel.dbs.service.ProjectService;
import cz.cvut.fel.dbs.service.TaskService;
import cz.cvut.fel.dbs.service.UserService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        try {
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