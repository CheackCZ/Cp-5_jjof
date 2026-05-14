package cz.cvut.fel.dbs;

import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import cz.cvut.fel.dbs.dao.ProjectDaoImpl;
import cz.cvut.fel.dbs.service.ProjectService;
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

            ProjectDaoImpl projectDao = new ProjectDaoImpl();
            EmployeeDaoImpl employeeDao = new EmployeeDaoImpl();

            projectDao.setEntityManager(em);
            employeeDao.setEntityManager(em);

            Project project = em.find(Project.class, 1);

            var projectService = new ProjectService(projectDao, employeeDao);
            projectService.assignEmployeeToProject(1, 28033);

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