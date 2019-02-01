package Poker.domain;

import Poker.domainLogic.Chip;
import Poker.domainLogic.Deck;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class Game {
    private Set<Chip> bank;
    private List<Player> players;
    private Deck deck;
    private LocalDateTime timeStamp;
    private int idGame;
}
