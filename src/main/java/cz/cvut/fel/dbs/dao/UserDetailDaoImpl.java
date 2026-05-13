package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.User;
import cz.cvut.fel.dbs.UserDetail;

public class UserDetailDaoImpl extends GenericDaoImpl<UserDetail, Integer>{
    public UserDetailDaoImpl() {
        super(UserDetail.class);
    }
}
