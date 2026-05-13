package cz.cvut.fel.dbs.dao;

import cz.cvut.fel.dbs.Client;


import java.util.List;
import java.util.Optional;

public class ClientDaoImpl extends GenericDaoImpl<Client, Integer> {

    public ClientDaoImpl() {
        super(Client.class);
    }

    /** Klient podle user_id */
    public Optional<Client> findByUserId(Integer userId) {
        return em.createQuery(
                "SELECT c FROM Client c WHERE c.id = :userId", Client.class)
                .setParameter("userId", userId)
                .getResultStream()
                .findFirst();
    }

    /** Klienti s business name (nejsou fyzické osoby) */
    public List<Client> findAllWithBusinessName() {
        return em.createQuery(
                "SELECT c FROM Client c WHERE c.businessName IS NOT NULL", Client.class)
                .getResultList();
    }

    /** Klient i s projekty (JOIN FETCH) */
//    public Optional<Client> findByIdWithProjects(Integer clientId) {
//        return em.createQuery(
//                "SELECT c FROM Client c " +
//                "LEFT JOIN FETCH c.projects " +
//                "WHERE c.clientId = :id", Client.class)
//                .setParameter("id", clientId)
//                .getResultStream()
//                .findFirst();
//    }
}
