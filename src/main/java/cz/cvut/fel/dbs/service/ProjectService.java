package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.Employee;
import cz.cvut.fel.dbs.Project;
import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import cz.cvut.fel.dbs.dao.ProjectDaoImpl;

import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProjectService {

    @Inject
    private ProjectDaoImpl projectDao;
    @Inject
    private EmployeeDaoImpl employeeDao;

    @Transactional
    public void assignEmployeeToProject(Integer projectId, Integer employeeId) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project with id " + projectId + " does not exist."
                ));

        Employee employee = employeeDao.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee with id " + employeeId + " does not exist."
                ));

        project.getEmployees().add(employee);
        employee.getProjects().add(project);

        projectDao.update(project);
        employeeDao.update(employee);
    }
}