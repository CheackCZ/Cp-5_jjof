package cz.cvut.fel.dbs.dao;


import cz.cvut.fel.dbs.Employee;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public class EmployeeDaoImpl extends GenericDaoImpl<Employee, Integer> {

    public EmployeeDaoImpl() {
        super(Employee.class);
    }

    /** Hledání podle SSN */
    public Optional<Employee> findBySsn(String ssn) {
        return em.createQuery(
                "SELECT e FROM Employee e WHERE e.ssn = :ssn", Employee.class)
                .setParameter("ssn", ssn)
                .getResultStream()
                .findFirst();
    }

    /** Zaměstnanci přiřazení na projekt */
    public List<Employee> findByProject(Integer projectId) {
        return em.createQuery(
                "SELECT e FROM Employee e " +
                "JOIN e.projects p " +
                "WHERE p.id = :projectId", Employee.class)
                .setParameter("projectId", projectId)
                .getResultList();
    }

    /** Zaměstnanci s platem v rozsahu */
    public List<Employee> findBySalaryRange(BigDecimal min, BigDecimal max) {
        return em.createQuery(
                "SELECT e FROM Employee e " +
                "WHERE e.salary BETWEEN :min AND :max " +
                "ORDER BY e.salary DESC", Employee.class)
                .setParameter("min", min)
                .setParameter("max", max)
                .getResultList();
    }

    /** Zaměstnanec i s přiřazenými projekty (JOIN FETCH) */
    public Optional<Employee> findByIdWithProjects(Integer employeeId) {
        return em.createQuery(
                "SELECT e FROM Employee e " +
                "LEFT JOIN FETCH e.projects " +
                "WHERE e.employeeId = :id", Employee.class)
                .setParameter("id", employeeId)
                .getResultStream()
                .findFirst();
    }
}
