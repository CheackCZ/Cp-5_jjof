package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.Client;
import cz.cvut.fel.dbs.Employee;
import cz.cvut.fel.dbs.Project;
import cz.cvut.fel.dbs.User;
import cz.cvut.fel.dbs.dao.ClientDaoImpl;
import cz.cvut.fel.dbs.dao.EmployeeDaoImpl;
import cz.cvut.fel.dbs.dao.ProjectDaoImpl;

import java.util.List;
import java.util.Optional;

import cz.cvut.fel.dbs.dao.UserDaoImpl;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ProjectService {

    @Inject
    private ProjectDaoImpl projectDao;
    @Inject
    private EmployeeDaoImpl employeeDao;
    @Inject
    private UserDaoImpl userDao;

    public ProjectService(ProjectDaoImpl projectDao, EmployeeDaoImpl employeeDao) {
        this.projectDao = projectDao;
        this.employeeDao = employeeDao;
    }

    public ProjectService() {}

    @Transactional
    public void assignEmployeeToProject(Integer projectId, Integer userId) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project with id " + projectId + " does not exist."
                ));

        Employee employee = employeeDao.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Employee with user_id " + userId + " does not exist."
                ));

        project.getEmployees().add(employee);
        employee.getProjects().add(project);

        projectDao.update(project);
    }

    @Transactional
    public void assignClientToProjectByUsername(Integer projectId, String client_username) {
        Project project = projectDao.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Project with id " + projectId + " does not exist."
                ));

        User user = userDao.findByUsername(client_username)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Client with username " + client_username + " does not exist."
                ));
        Client client = (Client) user;

        project.setClient(client);
        client.getProjects().add(project);

        projectDao.update(project);
        userDao.update(client);
    }
}