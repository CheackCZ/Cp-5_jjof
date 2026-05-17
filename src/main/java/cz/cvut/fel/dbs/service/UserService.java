package cz.cvut.fel.dbs.service;

import cz.cvut.fel.dbs.*;
import cz.cvut.fel.dbs.dao.*;
import cz.cvut.fel.dbs.utils.PasswordUtils;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

public class UserService {

    private UserDaoImpl userDao;

    private UserDetailDaoImpl userDetailDao;

    private LastNameDaoImpl lastNameDao;

    public UserService(UserDaoImpl userDao, UserDetailDaoImpl userDetailDao, LastNameDaoImpl lastNameDao) {
        this.userDao = userDao;
        this.userDetailDao = userDetailDao;
        this.lastNameDao = lastNameDao;
    }

    public void createUserWithDetails(String username, String password, String email, String phoneNumber, String street, String city, String postalCode, String firstname, String... lastnames ) throws Exception {

        // create and save user
        var user = new User();
        user.setUsername(username);

        var hash = PasswordUtils.hashPassword(password);
        user.setPassword(hash);

        userDao.save(user);

        // create and save user details
        var userDetails = new UserDetail();
        userDetails.setUser(user);

        userDetails.setPhoneNumber(phoneNumber);
        userDetails.setEmail(email);

        userDetails.setPostalCode(postalCode);
        userDetails.setStreet(street);
        userDetails.setCity(city);

        userDetails.setFirstName(firstname);

        userDetailDao.save(userDetails);

        // create and save last names
        for (var lastname : lastnames) {

            var lastNameId = new LastNameId();
            lastNameId.setLastName(lastname);
            lastNameId.setUserdetailsId(userDetails.getId());

            var lastName = new LastName();
            lastName.setId(lastNameId);

            lastNameDao.save(lastName);

        }
    }
}
