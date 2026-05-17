package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.Employee;
import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import java.math.BigDecimal;

public class EmployeeService {

    private EmployeeDaoImpl employeeDao;

    public EmployeeService(EmployeeDaoImpl employeeDao) {
        this.employeeDao = employeeDao;
    }

    public void countEmployeesDelayed() {
        //employeeDao set isolation level Serializable to prevent phantom reads

        Long countBefore = employeeDao.countEmployees();
        System.out.println("Employee count before sleep: " + countBefore);

        employeeDao.sleep(10);

        Long countAfter = employeeDao.countEmployees();
        System.out.println("Employee count after sleep: " + countAfter);
    }

    // run parallel to countEmployeesDelayed
    public void addEmployee(Employee employee) {
        employeeDao.save(employee);
    }




}