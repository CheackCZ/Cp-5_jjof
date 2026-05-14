package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.LastName;
import cz.cvut.fel.dbs.Project;

public class LastNameDao extends GenericDaoImpl<LastName, Integer>  {
    protected LastNameDao() {
        super(LastName.class);
    }
}
