package Poker.domainLogic;

public class Card {
    private Suit suit;
    private int valueRank;

    public Card(Suit suit, int valueRank) {
        this.suit = suit;
        this.valueRank = valueRank;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getValueRank() {
        return valueRank;
    }

    public void setValueRank(int valueRank) {
        this.valueRank = valueRank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", valueRank=" + valueRank +
                '}';
    }
}


