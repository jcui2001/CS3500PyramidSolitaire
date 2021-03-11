import cs3500.pyramidsolitaire.model.hw02.BasicPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.Card;
import cs3500.pyramidsolitaire.model.hw04.MultiPyramidSolitaire;
import cs3500.pyramidsolitaire.model.hw02.PyramidSolitaireModel;
import cs3500.pyramidsolitaire.model.hw04.RelaxedPyramidSolitaire;
import cs3500.pyramidsolitaire.view.PyramidSolitaireTextualView;
import cs3500.pyramidsolitaire.view.PyramidSolitaireView;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * View test for the Pyramid solitaire.
 */
public class PyramidSolitaireViewTest {

  @Test
  public void viewBasicTest1() {
    // initializes BasicPyramidSolitaire model
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    // initializes view to be given model
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    // calls startGame
    model.startGame(model.getDeck(), false, 7, 3);
    assertEquals("            A♣\n"
            + "          A♦  A♥\n"
            + "        A♠  2♣  2♦\n"
            + "      2♥  2♠  3♣  3♦\n"
            + "    3♥  3♠  4♣  4♦  4♥\n"
            + "  4♠  5♣  5♦  5♥  5♠  6♣\n"
            + "6♦  6♥  6♠  7♣  7♦  7♥  7♠\n"
            + "Draw: 8♣, 8♦, 8♥", view.toString());
  }

  @Test
  public void viewBasicTest2() {
    // initializes BasicPyramidSolitaire model
    PyramidSolitaireModel<Card> model = new BasicPyramidSolitaire();
    // initializes view to be given model
    PyramidSolitaireView view = new PyramidSolitaireTextualView(model);
    // calls startGame
    model.startGame(model.getDeck(), false, 7, 3);
    assertEquals("            A♣\n"
            + "          A♦  A♥\n"
            + "        A♠  2♣  2♦\n"
            + "      2♥  2♠  3♣  3♦\n"
            + "    3♥  3♠  4♣  4♦  4♥\n"
            + "  4♠  5♣  5♦  5♥  5♠  6♣\n"
            + "6♦  6♥  6♠  7♣  7♦  7♥  7♠\n"
            + "Draw: 8♣, 8♦, 8♥", view.toString());
  }

  @Test
  public void viewMultiTest1() {
    // initializes MultiPyramidSolitaire model
    PyramidSolitaireModel<Card> multiModel = new MultiPyramidSolitaire();
    // initializes view to be given model
    PyramidSolitaireView view = new PyramidSolitaireTextualView(multiModel);

    // calls startGame
    multiModel.startGame(multiModel.getDeck(), true, 7, 3);
    assertEquals("            4♣  .   .   7♣  .   .   J♠\n" +
            "          4♣  9♠  .   8♣  3♥  .   Q♦  7♣\n" +
            "        A♥  Q♥  10♥ J♠  7♥  4♦  5♣  8♠  K♦\n" +
            "      10♣ 5♥  10♠ 7♠  9♣  K♣  9♠  Q♣  8♣  3♣\n" +
            "    8♦  Q♠  2♥  10♠ 9♣  Q♣  6♥  6♠  J♣  10♦ 8♦\n" +
            "  A♠  5♠  4♦  3♣  5♦  2♠  9♦  6♦  2♣  5♥  A♦  9♦\n" +
            "2♦  K♥  K♠  4♠  7♦  4♥  5♠  7♦  10♥ 6♠  A♠  8♠  9♥\n" +
            "Draw: 10♣, 2♣, J♦", view.toString());
  }

  @Test
  public void viewMultiTest2() {
    // initializes MultiPyramidSolitaire model
    PyramidSolitaireModel<Card> multiModel = new MultiPyramidSolitaire();
    // initializes view to be given model
    PyramidSolitaireView view = new PyramidSolitaireTextualView(multiModel);

    // calls startGame
    multiModel.startGame(multiModel.getDeck(), false, 4, 3);
    assertEquals("      A♣  .   A♣  .   A♦\n" +
            "    A♦  A♥  A♥  A♠  A♠  2♣\n" +
            "  2♣  2♦  2♦  2♥  2♥  2♠  2♠\n" +
            "3♣  3♣  3♦  3♦  3♥  3♥  3♠  3♠\n" +
            "Draw: 4♣, 4♣, 4♦", view.toString());
  }

  @Test
  public void viewRelaxedTest() {
    // initializes MultiPyramidSolitaire model
    PyramidSolitaireModel<Card> relaxedModel = new RelaxedPyramidSolitaire();
    // initializes view to be given model
    PyramidSolitaireView view = new PyramidSolitaireTextualView(relaxedModel);

    // calls startGame
    relaxedModel.startGame(relaxedModel.getDeck(), false, 4, 3);
    assertEquals("", view.toString());
  }
}
