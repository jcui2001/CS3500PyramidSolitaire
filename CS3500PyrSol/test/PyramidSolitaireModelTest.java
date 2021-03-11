import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the PyramidSolitaireModel.
 */
public class PyramidSolitaireModelTest {

  String diamond = "♦";
  String heart = "♥";
  String spade = "♠";
  String club = "♣";

  List<Card> stock = new ArrayList<Card>();

  List<ArrayList<Card>> pyramid;

  List<Card> drawCards;

  Integer numRows;

  ArrayList<Card> row1;

  ArrayList<Card> row2;

  ArrayList<Card> row3;

  ArrayList<Card> row4;

  ArrayList<Card> row5;

  Card aceDiamonds = new Card(1, diamond, "A");
  Card aceSpades = new Card(1, spade, "A");
  Card aceClubs = new Card(1, club, "A");
  Card aceHearts = new Card(1, heart, "A");

  Card twoSpades = new Card(2, spade, "2");
  Card twoHearts = new Card(2, heart, "2");
  Card twoClubs = new Card(2, club, " 2");
  Card twoDiamonds;

  Card threeHearts = new Card(3, heart, "3");
  Card threeClubs;
  Card threeDiamonds;
  Card threeSpades = new Card(3, spade, "3");

  Card fourClubs = new Card(4, club, "4");
  Card fourSpades = new Card(4, spade, "4");
  Card fourDiamonds = new Card(4, diamond, "4");
  Card fourHearts = new Card(4, heart, "4");

  Card fiveDiamonds = new Card(5, diamond, "5");
  Card fiveHearts = new Card(5, heart, "5");
  Card fiveClubs = new Card(5, club, "5");
  Card fiveSpades = new Card(5, spade, "5");

  Card sixSpades = new Card(6, spade, "6");
  Card sixClubs = new Card(6, club, "6");
  Card sixHearts = new Card(6, heart, "6");
  Card sixDiamonds = new Card(6, diamond, "6");

  Card sevenDiamonds = new Card(7, diamond, "7");
  Card sevenHearts = new Card(7, heart, "7");
  Card sevenSpades = new Card(7, spade, "7");
  Card sevenClubs = new Card(7, club, "7");

  Card eightClubs = new Card(8, club, "8");
  Card eightSpades = new Card(8, spade, "8");
  Card eightDiamonds = new Card(8, diamond, "8");
  Card eightHearts = new Card(8, heart, "8");

  Card nineClubs = new Card(9, club, "9");
  Card nineHearts = new Card(9, heart, "9");
  Card nineSpades = new Card(9, spade, "9");
  Card nineDiamonds;

  Card tenClubs = new Card(10, club, "10");
  Card tenSpades = new Card(10, spade, "10");
  Card tenDiamonds = new Card(10, diamond, "10");
  Card tenHearts = new Card(10, heart, "10");

  Card jackDiamonds;
  Card jackHearts = new Card(11, heart, "J");
  Card jackSpades = new Card(11, spade, "J");
  Card jackClubs;

  Card queenDiamonds = new Card(12, diamond, "Q");
  Card queenClubs;
  Card queenSpades = new Card(12, spade, "Q");
  Card queenHearts = new Card(12, heart, "Q");

  Card kingSpades = new Card(13, spade, "K");
  Card kingDiamonds = new Card(13, diamond, "K");
  Card kingClubs = new Card(13, club, "K");
  Card kingHearts = new Card(13, heart, "K");

  @Test
  public void basicGetDeckTest() {
    //initData();
    assertEquals(stock.isEmpty(), true);
    stock = new BasicPyramidSolitaire().getDeck();
    assertEquals(stock.isEmpty(), false);
    assertEquals(stock.size(), 52);
    assertEquals(stock.get(0), aceClubs);
    assertEquals(stock.get(1), aceDiamonds);
    assertEquals(stock.get(2), aceHearts);
  }

  @Test
  public void basicStartGameTest() {
    PyramidSolitaireModel<Card> gameStartObject = new BasicPyramidSolitaire();
    try {
      gameStartObject.remove(1, 2);
    }
    catch (Exception e) {
      System.out.println("Game has not started.");
    }
    try {
      gameStartObject.startGame(gameStartObject.getDeck(), false, 10, 2);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    stock = new BasicPyramidSolitaire().getDeck();
    gameStartObject.startGame(stock, false, 3, 1);

    // checks that the draw cards have been filled
    assertEquals(gameStartObject.getNumDraw(), 1);
    // checks that the pyramid has been filled
    assertEquals(gameStartObject.getNumRows(), 3);
  }

  @Test
  public void basicRemoveTwoCardTest() {
    PyramidSolitaireModel<Card> gameTest1 = new BasicPyramidSolitaire();
    List<Card> deckTest1 = new ArrayList<Card>();
    deckTest1.add(aceHearts);
    deckTest1.add(fourClubs);
    deckTest1.add(fiveDiamonds);
    deckTest1.add(nineSpades);
    deckTest1.add(sixClubs);
    deckTest1.add(sevenSpades);
    deckTest1.add(threeHearts);

    gameTest1.startGame(deckTest1, false, 3, 1);
    assertEquals(gameTest1.getCardAt(0, 0), aceHearts);
    assertEquals(gameTest1.getCardAt(1, 0), fourClubs);
    assertEquals(gameTest1.getCardAt(1, 1), fiveDiamonds);
    assertEquals(gameTest1.getCardAt(2, 0), nineSpades);
    assertEquals(gameTest1.getCardAt(2, 1), sixClubs);
    gameTest1.remove(2,1, 2, 2);
    assertEquals(gameTest1.getCardAt(2, 1), null);
    assertEquals(gameTest1.getCardAt(2, 2), null);
    try {
      gameTest1.remove(2, 0, 2, 1);
    }
    catch (Exception e) {
      System.out.println("Cannot remove selected cards.");
    }
  }

  @Test
  public void basicRemoveOneCardTest() {
    // sets up test pyramid
    PyramidSolitaireModel<Card> gameTest1 = new BasicPyramidSolitaire();
    // creates test deck to construct pyramid
    List<Card> tempDeck = new ArrayList<Card>();

    tempDeck.add(kingDiamonds);
    tempDeck.add(aceClubs);
    tempDeck.add(kingSpades);
    tempDeck.add(kingClubs);
    tempDeck.add(fiveDiamonds);
    tempDeck.add(eightClubs);

    gameTest1.startGame(tempDeck, false, 3, 0);
    try {
      // throws invalid row error
      gameTest1.remove(6, 2);
    }
    catch (Exception e) {
      System.out.println("Invalid row number.");
    }
    // tries to catch invalid card position
    try {
      gameTest1.remove(1, 5);
    }
    catch (Exception e) {
      System.out.println("Invalid card position.");
    }
    try {
      gameTest1.remove(2, 0, 2, 1);
    }
    catch (Exception e) {
      System.out.println("Cannot remove selected cards.");
    }

    assertEquals(gameTest1.getCardAt(2, 0), kingClubs);
    assertEquals(gameTest1.getCardAt(2, 1), fiveDiamonds);
    assertEquals(gameTest1.getCardAt(2, 2), eightClubs);
    gameTest1.remove(2, 0);
    assertEquals(gameTest1.getCardAt(2, 0), null);
    gameTest1.remove(2, 1, 2, 2);
    assertEquals(gameTest1.getCardAt(2, 1), null);
    assertEquals(gameTest1.getCardAt(2, 2), null);

  }

  @Test
  public void basicRemoveCardAndDrawTest() {
    // sets up test pyramid
    PyramidSolitaireModel<Card> gameTest1 = new BasicPyramidSolitaire();
    // creates test deck to construct pyramid
    List<Card> deckTest1 = new ArrayList<Card>();

    // builds the deck manually to be tested on
    deckTest1.add(aceHearts);
    deckTest1.add(fourClubs);
    deckTest1.add(fiveDiamonds);
    deckTest1.add(nineSpades);
    deckTest1.add(tenDiamonds);
    deckTest1.add(sevenSpades);
    deckTest1.add(threeHearts);
    // starts the game to be tested on
    gameTest1.startGame(deckTest1, false, 3, 1);
    try {
      // throws invalid row error
      gameTest1.remove(6, 2);
    }
    catch (Exception e) {
      System.out.println("Invalid row number.");
    }
    // tries to catch invalid card position
    try {
      gameTest1.remove(1, 5);
    }
    catch (Exception e) {
      System.out.println("Invalid card position.");
    }
    try {
      gameTest1.remove(2, 0, 2, 1);
    }
    catch (Exception e) {
      System.out.println("Cannot remove selected cards.");
    }

    assertEquals(gameTest1.getCardAt(2, 0), nineSpades);
    assertEquals(gameTest1.getCardAt(2, 1), tenDiamonds);
    assertEquals(gameTest1.getCardAt(2, 2), sevenSpades);
    assertEquals(gameTest1.getDrawCards(), new ArrayList<Card>(Arrays.asList((threeHearts))));
    gameTest1.removeUsingDraw(0,2, 1);
    assertEquals(gameTest1.getCardAt(2, 1), null);
    assertEquals(gameTest1.getDrawCards(), new ArrayList<Card>());
  }

  @Test
  public void basicDiscardDrawTest() {
    // sets up test pyramid
    PyramidSolitaireModel<Card> gameTest1 = new BasicPyramidSolitaire();
    // creates test deck to construct pyramid
    List<Card> deckTest1 = new ArrayList<Card>();

    // builds the deck manually to be tested on
    deckTest1.add(aceHearts);
    deckTest1.add(fourClubs);
    deckTest1.add(fiveDiamonds);
    deckTest1.add(kingClubs);
    deckTest1.add(sixHearts);
    deckTest1.add(sevenDiamonds);
    deckTest1.add(twoSpades);
    deckTest1.add(fiveClubs);
    deckTest1.add(tenSpades);
    deckTest1.add(eightHearts);
    deckTest1.add(aceClubs);
    // starts the game to be tested on
    gameTest1.startGame(deckTest1, false, 3, 3);
    try {
      // throws invalid row error
      gameTest1.remove(6, 2);
    }
    catch (Exception e) {
      System.out.println("Invalid row number.");
    }

    assertEquals(gameTest1.getCardAt(1, 0), fourClubs);
    assertEquals(gameTest1.getCardAt(1, 1), fiveDiamonds);
    assertEquals(gameTest1.getCardAt(2, 2), sevenDiamonds);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>(Arrays.asList(twoSpades, fiveClubs, tenSpades)));
    gameTest1.discardDraw(0);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>(Arrays.asList(eightHearts, fiveClubs, tenSpades)));
    gameTest1.discardDraw(2);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>(Arrays.asList(eightHearts, fiveClubs, aceClubs)));
    gameTest1.discardDraw(1);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>(Arrays.asList(eightHearts, aceClubs)));
    gameTest1.discardDraw(0);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>(Arrays.asList(aceClubs)));
    gameTest1.discardDraw(0);
    assertEquals(gameTest1.getDrawCards(),
            new ArrayList<Card>());
  }

  @Test
  public void basicIsGameOver() {
    PyramidSolitaireModel<Card> gameTest1 = new BasicPyramidSolitaire();
    List<Card> deckTest1 = new ArrayList<Card>();
    deckTest1.add(kingSpades);
    deckTest1.add(queenSpades);
    deckTest1.add(aceSpades);
    deckTest1.add(tenSpades);
    deckTest1.add(threeSpades);
    deckTest1.add(eightSpades);
    deckTest1.add(fiveSpades);
    deckTest1.add(sixSpades);
    deckTest1.add(sevenSpades);
    deckTest1.add(fourSpades);
    deckTest1.add(nineSpades);
    deckTest1.add(twoSpades);
    deckTest1.add(jackSpades);
    deckTest1.add(kingHearts);
    deckTest1.add(queenHearts);
    deckTest1.add(aceHearts);
    deckTest1.add(tenHearts);
    deckTest1.add(twoHearts);
    deckTest1.add(eightHearts);
    deckTest1.add(fourHearts);
    deckTest1.add(sixHearts);
    deckTest1.add(sevenHearts);
    deckTest1.add(fiveHearts);
    deckTest1.add(nineHearts);
    deckTest1.add(threeHearts);
    deckTest1.add(kingDiamonds);
    deckTest1.add(jackHearts);
    deckTest1.add(queenDiamonds);
    deckTest1.add(aceDiamonds);

    gameTest1.startGame(deckTest1, false, 7, 1);
    assertEquals(gameTest1.getCardAt(0, 0), kingSpades);
    assertEquals(gameTest1.getCardAt(1, 0), queenSpades);
    assertEquals(gameTest1.getCardAt(2, 0), tenSpades);
    assertEquals(gameTest1.getCardAt(3, 0), fiveSpades);
    assertEquals(gameTest1.getCardAt(4, 0), nineSpades);
    assertEquals(gameTest1.isGameOver(), false);
  }

  @Test
  public void multiGetDeckTest() {
    //initData();
    assertEquals(stock.isEmpty(), true);
    stock = new MultiPyramidSolitaire().getDeck();
    assertEquals(stock.isEmpty(), false);
    assertEquals(stock.size(), 104);
    assertEquals(stock.get(0), aceClubs);
    assertEquals(stock.get(1), aceClubs);
    assertEquals(stock.get(2), aceDiamonds);
    assertEquals(stock.get(3), aceDiamonds);
    assertEquals(stock.get(30), fourSpades);
  }

  @Test
  public void multiStartGameTest() {
    PyramidSolitaireModel<Card> gameStartObject = new MultiPyramidSolitaire();
    try {
      gameStartObject.remove(1, 2);
    }
    catch (Exception e) {
      System.out.println("Game has not started.");
    }
    try {
      gameStartObject.startGame(gameStartObject.getDeck(), false, 10, 2);
    } catch (Exception e) {
      System.out.println(e.toString());
    }
    stock = new MultiPyramidSolitaire().getDeck();
    gameStartObject.startGame(stock, false, 2, 1);

    // checks that the draw cards have been filled
    assertEquals(gameStartObject.getNumDraw(), 1);
    // checks that the pyramid has been filled
    assertEquals(gameStartObject.getNumRows(), 2);
    assertEquals(gameStartObject.getRowWidth(0), 3);
  }

  @Test
  public void relaxedRemoveTwoTest() {
    PyramidSolitaireModel<Card> gameObject = new RelaxedPyramidSolitaire();
    try {
      gameObject.remove(3, 2, 1, 2);
    }
    catch (Exception e) {
      System.out.println("Game has not started.");
    }
    stock = new ArrayList<Card>(Arrays.asList());
    stock.add(eightDiamonds);
    stock.add(aceSpades);
    stock.add(sevenClubs);
    stock.add(twoHearts);
    stock.add(fourDiamonds);
    stock.add(eightClubs);
    stock.add(tenSpades);
    stock.add(nineClubs);
    stock.add(threeHearts);
    stock.add(fiveDiamonds);
    gameObject.startGame(stock, false, 4, 0);
    assertEquals(gameObject.getCardAt(3, 0), tenSpades);

    try {
      assertEquals(gameObject.getCardAt(4, 0), nineHearts);
    } catch (IllegalArgumentException e) {
      System.out.println("Invalid row.");
    }
    assertEquals(gameObject.getCardAt(1, 0), aceSpades);
    assertEquals(gameObject.getCardAt(2, 2), eightClubs);
    gameObject.remove(3, 0, 3, 2);
    gameObject.remove(3, 1, 2, 1);
    gameObject.remove(2, 2, 3, 3);
    assertEquals(gameObject.isGameOver(), true);
  }

}
