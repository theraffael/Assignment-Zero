import model.Card;
import model.Deck;

public class RunGame {
    public static void main(String[] args) {
        Deck deck = new Deck();
        System.out.println(deck.draw().getString());
        System.out.println(deck.draw().getString());
    }
}
