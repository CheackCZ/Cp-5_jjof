package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import cz.cvut.fel.dbs.dao.ProjectDaoImpl;
import cz.cvut.fel.dbs.dao.SprintDaoImpl;
import cz.cvut.fel.dbs.dao.TaskDaoImpl;
import cz.cvut.fel.dbs.service.ProjectService;
import cz.cvut.fel.dbs.service.TaskService;
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

            transaction.begin();

            // TaskService: Create task and if no sprint create new sprint
            ProjectDaoImpl projectDao = new ProjectDaoImpl();
            SprintDaoImpl sprintDao = new SprintDaoImpl();
            TaskDaoImpl taskDao = new TaskDaoImpl();

            projectDao.setEntityManager(em);
            sprintDao.setEntityManager(em);
            taskDao.setEntityManager(em);

            TaskService taskService = new TaskService(projectDao, sprintDao, taskDao);
            taskService.createTaskAndCreateSprintIfMissing(20, 1, "Test-task", "new");

            transaction.commit();

        } catch (Exception e) {

            e.printStackTrace();

            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {

            em.close();
            emf.close();
        }
    }
}