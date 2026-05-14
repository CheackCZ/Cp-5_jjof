package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.LastName;
import cz.cvut.fel.dbs.LastNameId;

public class LastNameDaoImpl extends GenericDaoImpl<LastName, LastNameId> {

    public LastNameDaoImpl() {
        super(LastName.class);
    }
}