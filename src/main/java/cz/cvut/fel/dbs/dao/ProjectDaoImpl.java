package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Project;

public class ProjectDaoImpl extends GenericDaoImpl<Project, Integer> {

    public ProjectDaoImpl() {
        super(Project.class);
    }
}
