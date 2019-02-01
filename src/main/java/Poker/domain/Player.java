package Poker.domain;

import Poker.domainLogic.Chip;
import Poker.domainLogic.Hand;

import java.util.Set;

public class Player {
    private User user;
    private int id;
    private String nickName;
    private Set<Chip> chipSet;
    private Hand hand;


    public Player() {
        this.id = 0;
        this.nickName = "";

    }

    public Player(int id, String nickName, Set<Chip> chipSet, Hand hand) {
        this.id = id;
        this.nickName = nickName;
        this.chipSet = chipSet;
        this.hand = hand;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Set<Chip> getChipSet() {
        return chipSet;
    }

    public void setChipSet(Set<Chip> chipSet) {
        this.chipSet = chipSet;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", nickName='" + nickName + '\'' +
                ", chipSet=" + chipSet +
                ", hand=" + hand +
                '}';
    }
}
