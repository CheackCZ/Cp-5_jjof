package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.User;

import java.util.Optional;

public class UserDaoImpl extends GenericDaoImpl<User, Integer> {

    public UserDaoImpl() {
        super(User.class);
    }

    /** Parametrizovaný dotaz – hledání podle username */
    public Optional<User> findByUsername(String username) {
        return em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class)
                .setParameter("username", username)
                .getResultStream()
                .findFirst();
    }
}
