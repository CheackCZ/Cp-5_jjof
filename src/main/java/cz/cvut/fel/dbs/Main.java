package cz.cvut.fel.dbs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {

            transaction.begin();

            Project project = entityManager.find(Project.class, 1);
            Employee employee = entityManager.find(Employee.class, 1);

            project.getEmployees().add(employee);
            employee.getProjects().add(project);

            entityManager.merge(project);
            entityManager.merge(employee);

            transaction.commit();

            System.out.println("Employee assigned to project successfully.");

        } catch (Exception e) {

            e.printStackTrace();

            if (transaction.isActive()) {
                transaction.rollback();
            }

        } finally {

            entityManager.close();
            entityManagerFactory.close();
        }
    }
}