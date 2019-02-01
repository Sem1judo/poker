package Poker.jdbc;

import Poker.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserCRUDTest {

    @Test
    public void createUser() {
        User user = new User("Hi","123asd","sada@mail.ru");
        UserCRUD userCRUD = new UserCRUD();

        userCRUD.createUser(user);
        assertNotEquals(0,user.getId());


    }
}