package Poker.dao;

import Poker.domain.Player;
import Poker.domain.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbstractDaoTest {
    @Test
    public void create() {
        Player player = new Player();
        player.setNickName("Winner");
        AbstractDao<Player> playerAbstractDao = new PlayerDao();
        playerAbstractDao.create(player);


    }

    @Test
    public void read() {
        AbstractDao<Player> playerAbstractDao = new PlayerDao();
        Player player = playerAbstractDao.read(1);
        System.out.println(player);
        assertNotNull(player);
    }

    @Test
    public void delete() {
        AbstractDao<Player> pl = new PlayerDao();
        pl.delete(5);
        assertNotNull(pl);

    }

    @Test
    public void update() {

        AbstractDao<Player> pl = new PlayerDao();
        Player player = pl.read(8);
        player.setNickName("BIbiKa");
        pl.update(player);
    }

    @Test
    public void getAll() {
        AbstractDao<User> userAbstractDao = new UserDao();
        System.out.println(userAbstractDao.getAll());

        AbstractDao<Player> pl = new PlayerDao();
        System.out.println(pl.getAll());
    }
}