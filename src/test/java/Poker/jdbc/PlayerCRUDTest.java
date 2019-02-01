package Poker.jdbc;

import Poker.domain.Player;
import org.junit.Before;
import org.junit.Test;

public class PlayerCRUDTest {
    private PlayerCRUD pcrud;
    Player player = new Player();

    @Before
    public void getPCRUD() {
        pcrud = new PlayerCRUD();
    }


    @Test
    public void createPlayer() {
        player.setNickName("Do now ");

        int idPlayer = pcrud.createPlayer(player);

        Player playerDB = pcrud.getById(idPlayer);


    }

    @Test
    public void deletePlayer() {

        deletePlayer();


    }

    @Test
    public void updatePlayer() {
        player.setNickName("honey");

}
}