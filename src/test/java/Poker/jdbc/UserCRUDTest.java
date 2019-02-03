package Poker.jdbc;

import Poker.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCRUDTest {
    User user = new User();
    UserCRUD userCRUD = new UserCRUD();
    @Test
    public void createUser() {
        User user = new User("Hi","123asd","sada@mail.ru");
        UserCRUD userCRUD = new UserCRUD();

        userCRUD.createUser(user);
        assertNotEquals(0,user.getId());


    }

    @Test
    public void readUser() {

        userCRUD.readUser(1);

    }

    @Test
    public void deleteUser() {
        userCRUD.deleteUser(8);
    }

    @Test
    public void updateUser() {
        user.setLogin("New");
        user.setPassword("difficult123");
        user.setEmail("george@rambler.ru");
        userCRUD.updateUser(2);
    }
}