package Poker.dao;

import Poker.domain.Player;
import Poker.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractDaoTest {
    @Test
    public void create() {
        User user = new User("LoginTest","passwordTest","emailTest");
        user.setBalance(1111);
        AbstractDao<User> userDao = new UserDao();
       userDao.create(user);


    }

    @Test
    public void read() {

        AbstractDao<User> userDao = new UserDao();
        User user =userDao.read(12);
        assertNotNull(user);
        System.out.println(user);
    }

    @Test
    public void delete() {

        User user = new User();
        AbstractDao<User> userDao = new UserDao();
        userDao.delete(9);
    }

    @Test
    public void update() {
        AbstractDao<User> userDao = new UserDao();
        User user =userDao.read(12);
        user.setBalance(300);
        userDao.update(user);
    }
}