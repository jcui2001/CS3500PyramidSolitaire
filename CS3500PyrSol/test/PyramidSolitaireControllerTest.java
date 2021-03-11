import cs3500.pyramidsolitaire.controller.PyramidSolitaireController;
import cs3500.pyramidsolitaire.controller.PyramidSolitaireTextualController;
import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test for the controller.
 */
public class PyramidSolitaireControllerTest {

  /**
   * initializes data to help with testing.
   */
  private void initData() {
    PyramidSolitaireModel<Card> testModel = new BasicPyramidSolitaire();
    String diamond = "♦";
    String heart = "♥";
    String spade = "♠";
    String club = "♣";

    List<Card> tempDeck = new ArrayList<Card>();

    List<ArrayList<Card>> pyramid;

    List<Card> drawCards;

    Card aceDiamonds = new Card(1, diamond, "A");
    Card aceSpades = new Card(1, spade, "A");
    Card aceClubs = new Card(1, club, "A");
    Card aceHearts = new Card(1, heart, "A");

    Card twoSpades = new Card(2, spade, "2");
    Card twoHearts = new Card(2, heart, "2");
    Card twoClubs = new Card(2, club, " 2");
    Card twoDiamonds;

    Card threeHearts = new Card(3, heart, "3");
    Card threeClubs = new Card(3, club, "3");
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
    Card sevenClubs;

    Card eightClubs = new Card(8, club, "8");
    Card eightSpades = new Card(8, spade, "8");
    Card eightDiamonds = new Card(8, diamond, "8");
    Card eightHearts = new Card(8, heart, "8");

    Card nineClubs;
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
  }

  @Test
  public void renderBeginStateTest() throws IOException {
    PyramidSolitaireModel<Card> testModel1 = new BasicPyramidSolitaire();
    Readable rd = new StringReader("");
    Appendable ap = new StringBuilder();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(testModel1, testModel1.getDeck(), false, 7, 3);
    assertEquals("            A♣  \n"
                        + "          A♦  A♥  \n"
                        + "        A♠  2♣  2♦  \n"
                        + "      2♥  2♠  3♣  3♦  \n"
                        + "    3♥  3♠  4♣  4♦  4♥  \n"
                        + "  4♠  5♣  5♦  5♥  5♠  6♣  \n"
                        + "6♦  6♥  6♠  7♣  7♦  7♥  7♠  \n"
                        + "Draw: 8♣, 8♦, 8♥\n"
                        + "Score: 112", ap.toString());
    System.out.println(ap.toString());
  }

  @Test
  public void renderRemoveTwoTest() throws IOException {
    PyramidSolitaireModel<Card> testModel2 = new BasicPyramidSolitaire();
    Readable rd = new StringReader("rm2 7 2 7 5 rm2 7 3 7 4");
    Appendable ap = new StringBuilder();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(testModel2, testModel2.getDeck(), false, 7, 3);
    assertEquals("            A♣  \n"
            + "          A♦  A♥  \n"
            + "        A♠  2♣  2♦  \n"
            + "      2♥  2♠  3♣  3♦  \n"
            + "    3♥  3♠  4♣  4♦  4♥  \n"
            + "  4♠  5♣  5♦  5♥  5♠  6♣  \n"
            + "6♦  6♥  6♠  7♣  7♦  7♥  7♠  \n"
            + "Draw: 8♣, 8♦, 8♥\n" + "Score: 112\n"
            + "            A♣  \n"
            + "          A♦  A♥  \n"
            + "        A♠  2♣  2♦  \n"
            + "      2♥  2♠  3♣  3♦  \n"
            + "    3♥  3♠  4♣  4♦  4♥  \n"
            + "  4♠  5♣  5♦  5♥  5♠  6♣  \n"
            + "6♦  .   6♠  7♣  .   7♥  7♠  \n"
            + "Draw: 8♣, 8♦, 8♥\n" + "Score: 99\n"
            + "            A♣  \n"
            + "          A♦  A♥  \n"
            + "        A♠  2♣  2♦  \n"
            + "      2♥  2♠  3♣  3♦  \n"
            + "    3♥  3♠  4♣  4♦  4♥  \n"
            + "  4♠  5♣  5♦  5♥  5♠  6♣  \n"
            + "6♦  .   .   .   .   7♥  7♠  \n"
            + "Draw: 8♣, 8♦, 8♥\n" + "Score: 86\n", ap.toString());
    System.out.println(ap.toString());
  }

  @Test
  public void renderRemoveOneGameOverTest() throws IOException {

    PyramidSolitaireModel<Card> testModel = new BasicPyramidSolitaire();
    String diamond = "♦";
    String heart = "♥";
    String spade = "♠";
    String club = "♣";

    List<Card> tempDeck = new ArrayList<Card>();

    List<ArrayList<Card>> pyramid;

    List<Card> drawCards;

    Card aceDiamonds = new Card(1, diamond, "A");
    Card aceSpades = new Card(1, spade, "A");
    Card aceClubs = new Card(1, club, "A");
    Card aceHearts = new Card(1, heart, "A");

    Card twoSpades = new Card(2, spade, "2");
    Card twoHearts = new Card(2, heart, "2");
    Card twoClubs = new Card(2, club, " 2");
    Card twoDiamonds;

    Card threeHearts = new Card(3, heart, "3");
    Card threeClubs = new Card(3, club, "3");
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
    Card sevenClubs;

    Card eightClubs = new Card(8, club, "8");
    Card eightSpades = new Card(8, spade, "8");
    Card eightDiamonds = new Card(8, diamond, "8");
    Card eightHearts = new Card(8, heart, "8");

    Card nineClubs;
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

    tempDeck.add(kingDiamonds);
    tempDeck.add(aceDiamonds);
    tempDeck.add(kingHearts);
    tempDeck.add(kingSpades);
    tempDeck.add(fiveSpades);
    tempDeck.add(eightSpades);
    tempDeck.add(threeClubs);

    Readable rd = new StringReader("rm1 3 1 rm2 3 3 3 2 rm1 2 2");
    Appendable ap = new StringBuilder();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(testModel, testModel.getDeck(), false, 3, 1);
    assertEquals("    K♦\n"
            + "  A♦  K♥\n"
            + "K♠  5♠  8♠\n"
            + "Draw: 3♣"
            + "    K♦\n"
            + "  A♦  K♥\n"
            + " .  5♠  8♠\n"
            + "Draw: 3♣"
            + "    K♦\n"
            + "  A♦  K♥\n"
            + " .   .   .\n"
           + "Draw: 3♣", ap.toString());
    System.out.println(ap.toString());
  }

  @Test
  public void renderRemoveOneTest() throws IOException {

    PyramidSolitaireModel<Card> testModel = new BasicPyramidSolitaire();
    String diamond = "♦";
    String heart = "♥";
    String spade = "♠";
    String club = "♣";

    List<Card> tempDeck = new ArrayList<Card>();

    List<ArrayList<Card>> pyramid;

    List<Card> drawCards;

    Card aceDiamonds = new Card(1, diamond, "A");
    Card aceSpades = new Card(1, spade, "A");
    Card aceClubs = new Card(1, club, "A");
    Card aceHearts = new Card(1, heart, "A");

    Card twoSpades = new Card(2, spade, "2");
    Card twoHearts = new Card(2, heart, "2");
    Card twoClubs = new Card(2, club, " 2");
    Card twoDiamonds;

    Card threeHearts = new Card(3, heart, "3");
    Card threeClubs = new Card(3, club, "3");
    Card threeDiamonds = new Card(3, diamond, "3");
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
    Card sevenClubs;

    Card eightClubs = new Card(8, club, "8");
    Card eightSpades = new Card(8, spade, "8");
    Card eightDiamonds = new Card(8, diamond, "8");
    Card eightHearts = new Card(8, heart, "8");

    Card nineClubs;
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

    tempDeck.add(kingDiamonds);
    tempDeck.add(aceDiamonds);
    tempDeck.add(kingHearts);
    tempDeck.add(kingSpades);
    tempDeck.add(eightClubs);
    tempDeck.add(fiveDiamonds);
    tempDeck.add(threeDiamonds);

    Readable rd = new StringReader("rm1 3 1 rm2 3 2 3 3");
    Appendable ap = new StringBuilder();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(testModel, tempDeck, false, 3, 1);
    assertEquals("    K♦  \n"
            + "  A♦  K♥  \n"
            + "K♠  8♣  5♦  \n"
            + "Draw: 3♦\n"
            + "    K♦  \n"
            + "  A♦  K♥  \n"
            + ".   8♣  5♦  \n"
            + "Draw: 3♦\n"
            + "    K♦  \n"
            + "  A♦  K♥  \n"
            + ".   .   .   \n"
            + "Draw: 3♦\n", ap.toString());
    System.out.println(ap.toString());
  }

  @Test
  public void relaxedRemoveOneTest() throws IOException {
    PyramidSolitaireModel<Card> testModel = new BasicPyramidSolitaire();
    String diamond = "♦";
    String heart = "♥";
    String spade = "♠";
    String club = "♣";
    List<Card> tempDeck = new ArrayList<Card>();
    Readable rd = new StringReader("rm1 3 1 rm2 3 2 3 3");
    Appendable ap = new StringBuilder();
    PyramidSolitaireController controller = new PyramidSolitaireTextualController(rd, ap);
    controller.playGame(testModel, tempDeck, false, 3, 1);
    assertEquals("    K♦  \n"
            + "  A♦  K♥  \n"
            + "K♠  8♣  5♦  \n"
            + "Draw: 3♦\n"
            + "    K♦  \n"
            + "  A♦  K♥  \n"
            + ".   8♣  5♦  \n"
            + "Draw: 3♦\n"
            + "    K♦  \n"
            + "  A♦  K♥  \n"
            + ".   .   .   \n"
            + "Draw: 3♦\n", ap.toString());
    System.out.println(ap.toString());
  }

}
